let cartService;

class ShoppingCartService {

    constructor() {
        this.cart = JSON.parse(localStorage.getItem("cart")) || [];
    }

    saveCart() {
        localStorage.setItem("cart", JSON.stringify(this.cart));
    }

    getCart() {
        return this.cart;
    }

    addToCart(product) {

        const existing = this.cart.find(item => item.productId === product.productId);

        if (existing) {
            existing.quantity++;
        } else {
            this.cart.push({
                productId: product.productId,
                name: product.name,
                price: product.price,
                imageUrl: product.imageUrl,
                quantity: 1
            });
        }

        this.saveCart();
        this.updateCartDisplay();
    }

    removeFromCart(productId) {

        this.cart = this.cart.filter(item => item.productId !== productId);

        this.saveCart();
        this.updateCartDisplay();
        this.loadCartPage();
    }

    clearCart() {

        this.cart = [];
        this.saveCart();
        this.updateCartDisplay();
        this.loadCartPage();
    }

    updateCartDisplay() {

        const cartControl = document.getElementById("cart-items");

        if (!cartControl) return;

        const totalItems = this.cart.reduce((sum, item) => sum + item.quantity, 0);
        cartControl.innerText = totalItems;
    }

    loadCartPage() {
        const main = document.getElementById("main");
        if (!main) return;

        main.innerHTML = "<h1>Shopping Cart</h1>";

        if (this.cart.length === 0) {
            main.innerHTML += "<p>Your cart is empty.</p>";
            return;
        }

        this.cart.forEach(item => {
            const div = document.createElement("div");
            div.classList.add("cart-item", "mb-4", "p-3", "border", "rounded");

            div.innerHTML = `
                <h3>${item.name}</h3>
                <img src="../Images/${item.imageUrl}" width="120">
                <p>Price: $${item.price}</p>
                <p>Quantity: ${item.quantity}</p>
                <button class="btn btn-danger">Remove</button>
            `;

            div.querySelector("button")
                .addEventListener("click", () => this.removeFromCart(item.productId));

            main.appendChild(div);
        });

        const checkoutSection = document.createElement("div");
        checkoutSection.classList.add("mt-4", "p-3", "border", "rounded");

        checkoutSection.innerHTML = `
            <h3>Checkout</h3>
            <div class="mb-3">
                <label for="deliveryLocationId" class="form-label">Delivery Location ID</label>
                <input type="number" id="deliveryLocationId" class="form-control" placeholder="Enter location ID">
            </div>
            <button id="checkoutBtn" class="btn btn-dark">Checkout</button>
        `;

        main.appendChild(checkoutSection);

        document.getElementById("checkoutBtn").addEventListener("click", () => {
            const deliveryLocationId = parseInt(document.getElementById("deliveryLocationId").value);

            if (!deliveryLocationId) {
                alert("Please enter a delivery location ID.");
                return;
            }

            this.checkout(deliveryLocationId);
        });
    }

    checkout(deliveryLocationId) {

        if (this.cart.length === 0) {
            alert("Cart is empty.");
            return;
        }

        const order = {
            deliveryLocationId: deliveryLocationId,
            items: this.cart.map(item => ({
                productId: item.productId,
                quantity: item.quantity
            }))
        };

        const url = `${config.baseUrl}/orders`;

        axios.post(url, order)
            .then(response => {
                const createdOrder = response.data;

                console.log("Order created:", createdOrder);

                this.cart = [];
                this.saveCart();
                this.updateCartDisplay();

                window.location.href = `delivery.html?orderId=${createdOrder.id}`;
            })
            .catch(error => {
                console.error("Checkout failed:", error);
                alert("Checkout failed.");
            });
    }
}

document.addEventListener('DOMContentLoaded', () => {

    cartService = new ShoppingCartService();
    cartService.updateCartDisplay();

});