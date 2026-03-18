function requireLogin() {

    const user = JSON.parse(localStorage.getItem("user"));

    if (!user || !user.token) {
        window.location.href = "../pages/signin.html";
    }

}


document.addEventListener("DOMContentLoaded", requireLogin);