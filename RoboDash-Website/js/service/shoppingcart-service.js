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
            div.classList.add("cart-item");

            div.innerHTML = `
                <h3>${item.name}</h3>
                <img src="/images/products/${item.imageUrl}" width="120">
                <p>Price: $${item.price}</p>
                <p>Quantity: ${item.quantity}</p>
                <button class="btn btn-danger">Remove</button>
            `;

            div.querySelector("button")
                .addEventListener("click", () => this.removeFromCart(item.productId));

            main.appendChild(div);
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

                console.log("Order created:", response.data);

                this.clearCart();

                alert("Order placed successfully!");

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