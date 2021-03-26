package com.mycompany.app;

import java.util.Scanner;

public class Daintree
{ 
    public static void addBook() {

    }

    public static void viewCart() {

    }

    public static void removeBook() {

    }

    public static void checkOut() {

    }

    public static void listBooks() {

    }


    public static void main(String[] args)
        {
            System.out.println("Welcome to Daintree!");
        boolean exit = false;
        while(exit == false) {
            System.out.println("Choose an option: ");
            System.out.println("1. Add a book to shopping cart");
            System.out.println("2. View shopping cart");
            System.out.println("3. Remove a book from shopping cart");
            System.out.println("4. Checkout");
            System.out.println("5. List all books");
            System.out.println("0. Quit");

            Scanner scan = new Scanner(System.in);
            System.out.println("Please make a selection: ");
            String selection = scan.next();

            switch (selection) {
                case "1":
                    addBook();
                    break;
                
                case "2":
                    viewCart();
                    break;
                
                case "3":
                    removeBook();
                    break;
                
                case "4":
                    checkOut();
                    break;
                    
                case "5":
                    listBooks();
                    break;

                case "0":
                    System.out.println("\n" + "Goodbye!");
                    scan.close();
                    exit = true;
                    break;

                default:
                    System.out.println("\n" + "Sorry, that is an invalid option!");
                    break;
            }
        }    
    }
}
