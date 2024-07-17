package Implementation;

import java.io.Serializable;

public class Electronics extends Product implements Serializable {


    public String brand;
    public int warrantyPeriod;

    public Electronics(String productType, String productID, String productName, int availableItems, double productPrice, String brand, int warrantyPeriod) {
        super(productType, productID, productName, availableItems, productPrice);
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    @Override
    public String toString() {
        return "Electronics{" +
                "brand='" + brand + '\'' +
                ", warrantyPeriod=" + warrantyPeriod +
                "} " + super.toString();
    }
}
