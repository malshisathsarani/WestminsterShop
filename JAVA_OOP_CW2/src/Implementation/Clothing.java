package Implementation;

import java.io.Serializable;

public class Clothing extends Product implements Serializable {

    public String color;
    public  String size;

    public Clothing(String productType, String productID, String productName, int availableItems, double productPrice, String color, String size) {
        super(productType, productID, productName, availableItems, productPrice);
        this.color = color;
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Clothing{" +
                "color='" + color + '\'' +
                ", size='" + size + '\'' +
                "} " + super.toString();
    }
}
