// Change this to wherever your Spring Boot API runs:
const API_BASE = "http://localhost:8080";
// Maximum speed allowed on each path type (meters/second)
// ⚠️ Change these values to match your backend
const PATH_TYPE_MAX_SPEED_MPS = {
  SIDEWALK: 6.7,
  ROAD: 13.4,
};
//const geo = await fetch("../data/ur-walk-network.geojson").then(r => r.json());
const statusEl = document.getElementById("status");
const etaEl = document.getElementById("eta");
const startBtn = document.getElementById("startBtn");

// Basic Leaflet setup (center will be corrected after we load route)
const map = L.map("map").setView([0, 0], 15);
L.tileLayer("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png", {
  maxZoom: 20,
  attribution: "&copy; OpenStreetMap contributors",
}).addTo(map);

let routeLine = null;
let robotMarker = null;

document.addEventListener("DOMContentLoaded", async () => {

    const params = new URLSearchParams(window.location.search);
    const orderId = params.get("orderId");

    if (!orderId) {
        statusEl.textContent = "No order selected";
        return;
    }

    await startDeliveryAnimation(orderId);
});


async function startDeliveryAnimation(orderId) {
    try {
        statusEl.textContent = "Fetching route...";
        etaEl.textContent = "—";

        const route = await fetchJson(`${API_BASE}/orders/${orderId}/route`);

        const locationIds = buildOrderedLocationIds(route);

        const points = [];
        for (const id of locationIds) {
            const loc = await fetchJson(`${API_BASE}/locations/${id}`);
            points.push([loc.latitude, loc.longitude]);
        }

        if (routeLine) routeLine.remove();
        routeLine = L.polyline(points, { weight: 6 }).addTo(map);
        map.fitBounds(routeLine.getBounds(), { padding: [30, 30] });

        if (robotMarker) robotMarker.remove();
        robotMarker = L.circleMarker(points[0], { radius: 10 }).addTo(map);

        statusEl.textContent = "Delivering...";
        animateAlong(points, route.paths, null, (remainingSec) => {
            etaEl.textContent = `${Math.ceil(remainingSec)}s`;
        }, async () => {
            statusEl.textContent = "Delivered ✅";
            etaEl.textContent = "0s";

            await fetch(`${API_BASE}/orders/${orderId}/status`, {
                method: "PUT",
                headers: {
                    "Content-Type": "text/plain",
                    "Authorization": `Bearer ${JSON.parse(localStorage.getItem("user"))?.token || ""}`
                },
                body: "COMPLETED"
            });
        });

    } catch (err) {
        console.error(err);
        statusEl.textContent = "Error loading route";
    }
}

function getAuthHeaders() {
  const user = JSON.parse(localStorage.getItem("user"));
  return user?.token
    ? { Authorization: `Bearer ${user.token}` }
    : {};
}

async function fetchJson(url) {
  const res = await fetch(url, {
    headers: {
      ...getAuthHeaders()
    }
  });

  if (!res.ok) throw new Error(`Request failed: ${res.status} ${url}`);
  return await res.json();
}

/**
 * Converts your route into an ordered list of location IDs.
 * Assumes route.paths are in travel order (as returned by calculateRoute()).
 */
function buildOrderedLocationIds(route) {
  const ids = [];
  // startingLocation has locationId
  ids.push(route.startingLocation.locationId);

  // each path leads to next location
  for (const p of route.paths) {
    ids.push(p.toLocationId);
  }
  return ids;
}

/**
 * Animate marker between waypoints using requestAnimationFrame.
 * Uses path distances (meters) so the timing matches your backend distances.
 */
function animateAlong(latlngs, paths, speedMps, onTickEta, onDone) {
  // Build per-segment durations using backend distance if available
  // paths.length should be latlngs.length - 1
  const segDur = [];
  let totalSec = 0;

  // Calculate duration for each path segment using its pathType speed
  for (let i = 0; i < latlngs.length - 1; i++) {

    const meters = paths[i]?.distance ?? approxMeters(latlngs[i], latlngs[i + 1]);

    // Example pathType values: "SIDEWALK", "CROSSWALK", etc.
    const pathType = paths[i]?.pathType;

    // Look up max speed for this path type
    const typeMaxSpeed = PATH_TYPE_MAX_SPEED_MPS[pathType] ?? 1.0; // fallback

    // Calculate time = distance / speed
    const sec = meters / typeMaxSpeed;

    segDur.push(sec);
    totalSec += sec;
  }

  const startTime = performance.now();
  let lastEta = totalSec;

  function frame(now) {
    const elapsedSec = (now - startTime) / 1000;
    const remaining = Math.max(0, totalSec - elapsedSec);

    // update ETA occasionally
    if (Math.abs(remaining - lastEta) > 0.2) {
      onTickEta(remaining);
      lastEta = remaining;
    }

    // Find which segment we’re on
    let t = elapsedSec;
    let segIndex = 0;
    while (segIndex < segDur.length && t > segDur[segIndex]) {
      t -= segDur[segIndex];
      segIndex++;
    }

    if (segIndex >= segDur.length) {
      // done
      robotMarker.setLatLng(latlngs[latlngs.length - 1]);
      onDone?.();
      return;
    }

    const segT = t / segDur[segIndex]; // 0..1
    const a = latlngs[segIndex];
    const b = latlngs[segIndex + 1];

    // linear interpolation in lat/lon
    const pos = [
      a[0] + (b[0] - a[0]) * segT,
      a[1] + (b[1] - a[1]) * segT,
    ];

    robotMarker.setLatLng(pos);
    requestAnimationFrame(frame);
  }

  requestAnimationFrame(frame);
}

// fallback distance if needed
function approxMeters(a, b) {
  // quick haversine approximation
  const R = 6371000;
  const toRad = (x) => (x * Math.PI) / 180;
  const dLat = toRad(b[0] - a[0]);
  const dLon = toRad(b[1] - a[1]);
  const lat1 = toRad(a[0]);
  const lat2 = toRad(b[0]);

  const s =
    Math.sin(dLat / 2) ** 2 +
    Math.cos(lat1) * Math.cos(lat2) * Math.sin(dLon / 2) ** 2;

  return 2 * R * Math.asin(Math.sqrt(s));
}