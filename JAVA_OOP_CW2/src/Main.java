import Implementation.GUI.Gui;
import Implementation.ShoppingManager;
import Implementation.WestminsterShoppingManager;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;



public class Main {
    public static void main(String[] args) {


        WestminsterShoppingManager manager = new WestminsterShoppingManager();

        while (true) {
            menuList();
            System.out.print("Enter choice: ");
            Scanner scanner = new Scanner(System.in);
            int c = scanner.nextInt();
            if (c == 5) break;
            else consoleMenu(c, manager);
        }
    }

        public static void menuList () {
            System.out.println("                                                         ");
            System.out.println("       # WELCOME TO WESTMINSTER SHOPPING #      ");
            System.out.println("_________________________________________________________");
            System.out.println("                                                         ");
            System.out.println("1 -> Add a new product ");
            System.out.println("2 -> Delete a product");
            System.out.println("3 -> Print the list of the products");
            System.out.println("4 -> Save in a file");
            System.out.println("5 -> exit");
        }
        public static void consoleMenu(int choice, ShoppingManager manager){
            String menuList = "";
            switch (choice) {
                case 1:
                    menuList = "Add a new product ";
                    manager.addProduct();
                    break;

                case 2:
                    menuList = "Delete a product";
                    manager.deleteProduct();
                    break;

                case 3:
                    menuList = "Print the list of the products";
                    manager.printProduct();
                    break;

                case 4:
                    menuList = "Save in a file";
                    manager.saveProductsToFile();
                    break;
                case 5:
                    menuList = "exit";
                    System.out.println("You are exiting");
                    break;
                case 6:
                    menuList = "GUI";
                    SwingUtilities.invokeLater(() -> new Gui());
                    break;
                default:
                    System.out.println("Invalid input");
                    return;
            }
            System.out.println(menuList);
        }

}