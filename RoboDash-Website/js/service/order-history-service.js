let orderHistoryService;

class OrderHistoryService {
    loadOrderHistory() {
        const container = document.getElementById("order-history-list");
        if (!container) return;

        const url = `${config.baseUrl}/orders/my-orders`;

        axios.get(url)
            .then(response => {
                const orders = response.data;
                this.renderOrders(orders);
            })
            .catch(error => {
                console.error("Failed to load order history:", error);
                container.innerHTML = `
                    <div class="alert alert-danger">
                        Failed to load order history.
                    </div>
                `;
            });
    }

    renderOrders(orders) {
        const container = document.getElementById("order-history-list");
        if (!container) return;

        container.innerHTML = "";

        if (!orders || orders.length === 0) {
            container.innerHTML = `
                <div class="col-12 text-center">
                    <p class="text-muted">You have no previous orders yet.</p>
                </div>
            `;
            return;
        }

        orders.forEach(order => {
            const items = Object.values(order.items || {});
            const itemsHtml = items.map(item => `
                <li class="list-group-item d-flex justify-content-between align-items-center">
                    <div>
                        <strong>${item.product.name}</strong><br>
                        <small>Quantity: ${item.quantity}</small>
                    </div>
                    <span>$${(item.price * item.quantity).toFixed(2)}</span>
                </li>
            `).join("");

            const orderDate = order.orderDate
                ? new Date(order.orderDate).toLocaleString()
                : "Unknown date";

            const card = document.createElement("div");
            card.className = "col-12 mb-4";

            card.innerHTML = `
                <div class="card shadow-sm">
                    <div class="card-header d-flex justify-content-between align-items-center">
                        <div>
                            <strong>Order #${order.orderId}</strong><br>
                            <small>${orderDate}</small>
                        </div>
                        <span class="badge bg-primary">${order.status}</span>
                    </div>

                    <div class="card-body">
                        <p><strong>Total:</strong> $${Number(order.totalPrice || order.total || 0).toFixed(2)}</p>
                        <p><strong>Delivery Location ID:</strong> ${order.deliveryLocationId}</p>

                        <ul class="list-group">
                            ${itemsHtml}
                        </ul>
                    </div>
                </div>
            `;

            container.appendChild(card);
        });
    }
}

document.addEventListener("DOMContentLoaded", () => {
    orderHistoryService = new OrderHistoryService();
    orderHistoryService.loadOrderHistory();
});