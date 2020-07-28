package com.commercecloud.getOrderService.models;

public class OrderDetails {
    private String productName;
    private String quantity;
    private String orderTotal;

    public OrderDetails(String productName, String quantity, String orderTotal) {
        this.productName = productName;
        this.quantity = quantity;
        this.orderTotal = orderTotal;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(String orderTotal) {
        this.orderTotal = orderTotal;
    }


}
