/*!
* Start Bootstrap - Shop Homepage v5.0.6 (https://startbootstrap.com/template/shop-homepage)
* Copyright 2013-2023 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-shop-homepage/blob/master/LICENSE)
*/
// This file is intentionally blank
// Use this file to add JavaScript to your project
document.addEventListener("DOMContentLoaded", () => {
  const cartBtn = document.getElementById("cartBtn");
  if (cartBtn) {
    cartBtn.addEventListener("click", () => {
      const path = window.location.pathname;
      const isInPagesFolder = path.includes("/pages/");
      window.location.href = isInPagesFolder ? "cart.html" : "pages/cart.html";
    });
  }

  const authLink = document.getElementById("authLink");
  if (!authLink) return;

  const user = JSON.parse(localStorage.getItem("user"));

  if (user && user.token) {
    authLink.textContent = "Sign Out";
    authLink.href = "#";

    authLink.addEventListener("click", (e) => {
      e.preventDefault();
      localStorage.removeItem("user");

      const path = window.location.pathname;
      const isInPagesFolder = path.includes("/pages/");
      window.location.href = isInPagesFolder ? "../index.html" : "index.html";
    });
  } else {
    authLink.textContent = "Sign In";

    const path = window.location.pathname;
    const isInPagesFolder = path.includes("/pages/");
    authLink.href = isInPagesFolder ? "signin.html" : "pages/signin.html";
  }
});