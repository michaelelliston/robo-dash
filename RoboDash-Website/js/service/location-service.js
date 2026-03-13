let locationService;

class LocationService {

    loadDropoffLocations() {

        const url = `${config.baseUrl}/locations`;

        axios.get(url)
            .then(response => {

                const locations = response.data
                    .filter(loc => loc.type === "DROPOFF");

                const select = document.getElementById("locationSelect");

                locations.forEach(loc => {

                    const option = document.createElement("option");
                    option.value = loc.locationId;
                    option.textContent = loc.name;

                    select.appendChild(option);

                });

            })
            .catch(error => {
                console.error("Failed to load locations", error);
            });
    }
}

document.addEventListener("DOMContentLoaded", () => {

    locationService = new LocationService();
    locationService.loadDropoffLocations();

});