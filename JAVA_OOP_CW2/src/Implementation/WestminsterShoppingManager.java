package Implementation;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;


public class WestminsterShoppingManager implements ShoppingManager{

    private static int count = 0;

    public static ArrayList<Product> AllProducts = new ArrayList<Product>();

    @Override
    public void addProduct() {
        Scanner input = new Scanner(System.in);


        System.out.println("What is the product type? (Electronics or Clothing)");
        String pType = input.nextLine();

        System.out.println("Enter Product ID");
        String pID = input.nextLine();

        System.out.println("Enter Product Name");
        String pName = input.nextLine();

        System.out.println("Enter Product available Items");
        int pAvailableItems = input.nextInt();

        System.out.println("Enter Product Price");
        double pPrice = input.nextDouble();

        input.nextLine();

        if (pType.equalsIgnoreCase("Electronics")) {
            System.out.println("Enter Electronic Brand");
            String brand = input.nextLine();

            System.out.println("Enter Electronic Warranty Period");
            int warranty = input.nextInt();
            input.nextLine(); // Consume the leftover newline
            count++;

            Electronics e = new Electronics(pType, pID, pName, pAvailableItems, pPrice, brand, warranty);
            AllProducts.add(e);

            System.out.println("Product added successfully.");
        } else if (pType.equalsIgnoreCase("Clothing")) {
            System.out.println("Enter cloth color");
            String color = input.nextLine();

            System.out.println("Enter cloth size (S/ M/ L/ XL/ XXL)");
            String size = input.nextLine();
            count++;

            Clothing c = new Clothing(pType, pID, pName, pAvailableItems, pPrice, color, size);
            AllProducts.add(c);

            System.out.println("Product added successfully.");
        } else {
            System.out.println("Unknown Product Type.. Try Again");
        }

        if (count == 50) {
            System.out.println("Products are full! Already 50 products.");
        } else {
            System.out.println("Product count: " + count);
        }
    }

    @Override
    public void deleteProduct() {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter Product ID");
        String delProductId = input.nextLine();

        boolean found = false;
        for (int i = 0; i < AllProducts.size(); i++) {
            if (AllProducts.get(i).getProductID().equals(delProductId)) {
                AllProducts.remove(i);
                count--;
                found = true;
                System.out.println("Product removed successfully.");
                break;
            }
        }
        if (!found) {
            System.out.println("Product not found.");
        }
    }

    @Override
    public void printProduct() {
        if (AllProducts.isEmpty()) {
            System.out.println("Products are empty");
        } else {

            // Sort products by product ID
            Collections.sort(AllProducts, Comparator.comparing(Product::getProductID));

            for (Product p : AllProducts) {
                if (p instanceof Electronics) {
                    Electronics e = (Electronics) p;
                    System.out.println("Electronics:");
                    System.out.println("  Product ID: " + e.getProductID());
                    System.out.println("  Product Name: " + e.getProductName());
                    System.out.println("  Available Items: " + e.getAvailableItems());
                    System.out.println("  Price: $" + e.getProductPrice());
                    System.out.println("  Brand: " + e.getBrand());
                    System.out.println("  Warranty Period: " + e.getWarrantyPeriod() + " months");
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                } else if (p instanceof Clothing) {
                    Clothing c = (Clothing) p;
                    System.out.println("Clothing:");
                    System.out.println("  Product ID: " + c.getProductID());
                    System.out.println("  Product Name: " + c.getProductName());
                    System.out.println("  Available Items: " + c.getAvailableItems());
                    System.out.println("  Price: $" + c.getProductPrice());
                    System.out.println("  Color: " + c.getColor());
                    System.out.println("  Size: " + c.getSize());
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                }
            }
        }
    }

    @Override
    public void saveProductsToFile() {



    }

}