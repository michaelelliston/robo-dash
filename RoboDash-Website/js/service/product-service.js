let productService;

class ProductService {

    loadProducts() {

        const url = `${config.baseUrl}/products`;

        axios.get(url)
            .then(response => {

                const products = response.data;

                this.renderProducts(products);

            })
            .catch(error => {
                console.error("Failed to load products", error);
            });

    }

    renderProducts(products) {

        const container = document.getElementById("product-list");

        container.innerHTML = "";

        products.forEach(product => {

            const col = document.createElement("div");
            col.classList.add("col");

            col.setAttribute("data-menu-item", "");
            col.dataset.diet = product.diet;
            col.dataset.price = product.price;

            col.innerHTML = `
            <div class="card h-100 shadow-sm">

                <img class="card-img-top"
                     src="../assets/images/${product.imageUrl}"
                     alt="">

                <div class="card-body p-3 d-flex flex-column">

                    <div class="text-center">

                        <h5 class="fw-bold">${product.name}</h5>

                        <p class="text-muted small mt-2">
                            ${product.description}
                        </p>

                    </div>

                    <div class="mt-auto d-flex justify-content-between align-items-center pt-3">

                        <span class="fw-bold fs-5" style="color:#2C6E99;">
                            $${product.price}
                        </span>

                        <span class="badge bg-white border text-muted">
                            ${product.diet}
                        </span>

                    </div>

                </div>

                <div class="card-footer p-3 bg-transparent border-0 text-center">

                    <button class="btn btn-primary rounded-pill px-4 add-to-cart"
                            data-id="${product.productId}"
                            data-name="${product.name}"
                            data-price="${product.price}"
                            data-image="${product.imageUrl}">
                        Add to Cart
                    </button>

                </div>

            </div>
            `;

            container.appendChild(col);

        });

    }

}

document.addEventListener("DOMContentLoaded", () => {

    productService = new ProductService();

    productService.loadProducts();

});