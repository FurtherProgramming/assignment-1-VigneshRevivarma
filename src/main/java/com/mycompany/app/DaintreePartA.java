// package com.mycompany.app
import java.util.ArrayList;
import java.util.Scanner;

public class DaintreePartA
{   
    private static ArrayList<String> bookName = new ArrayList<String>();
    private static ArrayList<Integer> pCopies = new ArrayList<Integer>();
    private static ArrayList<Boolean> eBook = new ArrayList<Boolean>();
    private static String cart = "";
    private static boolean isEbook = false; 
    
    private static void initializingValues(){
        
        bookName.add("Absolute Java (Savitch)");
        bookName.add("JAVA: How to Program (Deitel and Deitel)");
        bookName.add("Computing Concepts with JAVA 8 Essentials (Horstman)");
        bookName.add("Java Software Solutions (Lewis and Loftus)");
        bookName.add("Java Program Design (Cohoon and Davidson)");

        pCopies.add(5);
        pCopies.add(0);
        pCopies.add(5);
        pCopies.add(5);
        pCopies.add(1);

        eBook.add(true);
        eBook.add(true);
        eBook.add(false);
        eBook.add(false);
        eBook.add(true);

    }


    public static void addBook() {
        Scanner scan = new Scanner(System.in);
        System.out.println("\n\nEnter title to search for: ");
        String option = scan.next();
        String foundBook = null;
        int j = 0;

        for (int i = 0; i<bookName.size() ; i++) {
            if (bookName.get(i).toUpperCase().startsWith(option.toUpperCase())){
                System.out.println("The following title is a match: ");
                System.out.println("1. " + bookName.get(i)); 
                foundBook = bookName.get(i);
                j = i;
                System.out.println("0. cancel");
                break;           
            }
        }
        
        if (foundBook == null) {
            System.out.println("There is no title starting with that.\n\n");
            return;
        }

        System.out.println("What is your selection: ");
        String selection = scan.next();
        switch(selection) {
            case "1":
            if (eBook.get(j) == true){
                System.out.println("Do you want to buy this as an ebook: ");
                String ebook = scan.next();
                if (ebook.toLowerCase().equals("yes")) {
                    cart = foundBook;
                    isEbook = true;
                }
                else {
                    int numOfCopies = pCopies.get(j);
                    if (numOfCopies > 0) {
                        cart = foundBook;
                        pCopies.remove(j);
                        pCopies.add(j, numOfCopies-1);
                    }
                    else {
                        System.out.println("There are no physical copies of that book available!\n\n");
                        break;
                    }
                }
            }
            System.out.println(foundBook + " has been added to your cart.\n\n");
            break;

            case "0":
            System.out.println("Cancelled.\n\n");
            break;

            default:
            System.out.println("Invalid option.\n\n");
            break;
        }
    }

    public static void viewCart() {

        if(cart == "") {
            System.out.println("\n\nYour Shopping Cart is empty.\n\n");
        }
        else {
        System.out.println("\n\nYour Shopping Cart contains the following:");
        System.out.println("1. " + cart + "\n\n");
        }
    }

    public static void removeBook() {

        System.out.println("\n\nYour Shopping Cart contains the following:");
        System.out.println("1. " + cart);
        System.out.println("0. cancel");
        Scanner scan = new Scanner(System.in);
        System.out.println("What do you want to remove: ");
        String selection = scan.next();
        
        switch (selection) {
            case "1":
            cart = "";
            isEbook = false;
            System.out.println("The Book has been removed.\n\n");
            break;
            case "0":
            System.out.println("No changes.\n\n");
            break;
            default:
            System.out.println("Invalid option.\n\n");
            break;
        }
    }

    public static void checkOut() {
        if (cart == "") {
            System.out.println("\n\nYour cart is empty.\n\n");
        }
        else {
            if (isEbook == true){
            System.out.println("\n\nYou have purchased items to the total value of $8.00");
        }
            else {
                System.out.println("\n\nYou have purchased items to the total value of $30.00");
            }

            System.out.println("Thank you for shopping with Daintree!\n\n");
            cart = "";
            isEbook = false;
        }
    }

    public static void listBooks() {
        
        System.out.println("\n\nThe following titles are available:");
        for (int i = 0; i < bookName.size(); i++) {
            if (pCopies.get(i) == 0 && eBook.get(i) == false) {
                continue;
            }
            System.out.print(i + ". " + bookName.get(i) + ", " + pCopies.get(i) + ", ");
            if (eBook.get(i) == true) {
                System.out.println("ebook available");
            }
            else {
                System.out.println("no ebook");
            }
        }
        System.out.println("\n\n");   
    }


    public static void main(String[] args)
        {
            System.out.println("Welcome to Daintree!");
            initializingValues();
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
                    System.out.println("\n" + "Goodbye!\n\n");
                    scan.close();
                    exit = true;
                    break;

                default:
                    System.out.println("\n" + "Sorry, that is an invalid option!\n\n");
                    break;
            }
        }    
    }
}