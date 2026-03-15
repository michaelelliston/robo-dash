const config = {
    baseUrl: 'http://localhost:8080'
};

// Automatically attach JWT token to every request
axios.interceptors.request.use(function (request) {

    const token = localStorage.getItem("token");

    if (token) {
        request.headers.Authorization = `Bearer ${token}`;
    }

    return request;

});