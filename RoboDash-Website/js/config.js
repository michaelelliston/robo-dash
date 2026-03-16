const config = {
    baseUrl: 'http://localhost:8080'
};

// Automatically attach JWT token to every request
axios.interceptors.request.use(function (request) {

    const user = JSON.parse(localStorage.getItem("user"));

    if (user && user.token) {
        request.headers.Authorization = `Bearer ${user.token}`;
    }

    return request;

});