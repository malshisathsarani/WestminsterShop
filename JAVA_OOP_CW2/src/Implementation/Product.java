package Implementation;

import java.io.Serializable;

public abstract class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    private String productType;
    private String productID;
    private String productName;
    private int availableItems;
    private double productPrice;

    public Product(String productType, String productID, String productName, int availableItems, double productPrice) {
        this.productType = productType;
        this.productID = productID;
        this.productName = productName;
        this.availableItems = availableItems;
        this.productPrice = productPrice;
    }


//Generate Getters and Setters

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getAvailableItems() {
        return availableItems;
    }

    public void setAvailableItems(int availableItems) {
        this.availableItems = availableItems;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", availableItems=" + availableItems +
                ", productPrice=" + productPrice +
                '}';
    }
}
