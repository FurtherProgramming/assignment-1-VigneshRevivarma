// package com.mycompany.app
import java.util.ArrayList; //Packages
import java.util.Scanner;

public class BookStore extends Book {

    public BookStore(String bName, String bAuthor, int pCopies, boolean eBook) {
        super(bName, bAuthor, pCopies, eBook); // Super constructor.
    }
    
    private static ArrayList<Book> books = new ArrayList<>();
    private static ArrayList<Book> shoppingCart = new ArrayList<>();
    private static ArrayList<Boolean> isEbook = new ArrayList<>();  // Creating ArrayLists.

    public static void initializingValues() { // Making Books as Array Objects.
        
        Book book = new Book("Absolute Java", "Savitch", 5, true);
        books.add(book);

        book = new Book("JAVA: How to Program", "Deitel and Deitel", 0 , true);
        books.add(book);

        book = new Book("Computing Concepts with JAVA 8 Essentials", "Horstman", 5, false);
        books.add(book);

        book = new Book("Java Software Solutions", "Lewis and Loftus", 5, false);
        books.add(book);

        book = new Book("Java Program Design", "Cohoon and Davidson", 1, true);
        books.add(book);

    }

    public static void addBook() { // Used to Add Book(s) to the cart.

        Scanner scan = new Scanner(System.in);
        System.out.println("\n\nEnter title to search for: ");
        String option = scan.next();

        ArrayList<Book> foundBooks = new ArrayList<>();
        int j = 1;

        for (int i = 0; i<books.size() ; i++ ) { // Loop used to find book(s) according to the user's input.
            Book currentBook = books.get(i);
            if (currentBook.getBookName().toUpperCase().startsWith(option.toUpperCase())){
                if (j == 1) {
                    System.out.println("The following title(s) is a match: ");
                }
                System.out.println(j + ". " + currentBook.getBookName() + " -- " + currentBook.getAuthor());
                foundBooks.add(currentBook);
                j++;
            }
        }

        if (foundBooks.isEmpty()) {
            System.out.println("There is no title starting with that.\n\n");
            return;
        }
        else {
            System.out.println("0. cancel");
        }

        System.out.println("Which number item do you wish to purchase: ");
        String selection = scan.next();

        if (selection == "0") {
            System.out.println("Cancelled.\n\n");
        }
        else {
            int foundIndex = Integer.parseInt(selection) - 1;

            if (foundIndex >= foundBooks.size()) {
                System.out.println("Invalid  option.\n\n");
                return;
            }

            Book purchasing = foundBooks.get(foundIndex); // Storing the purchased book as an object.
            
            System.out.println("Purchasing:" + purchasing.getBookName());

            System.out.println("Do you want to buy this as an ebook: ");
            String ebook = scan.next();
            if (ebook.toLowerCase().equals("yes")) { // Making sure whether the book bought is an E-Book or Physical copy.
                if (purchasing.getECopies() == false) {
                    System.out.println("There are no ebooks avaiable for that book.\n\n");
                    return;
                }
                else {
                    shoppingCart.add(purchasing);
                    isEbook.add(true);
                    System.out.println("Item added to cart.\n\n");
                    return;
                }
            }
            else {
                if (purchasing.getPCopies() == 0) {
                    System.out.println("There are no physical copies avaiable for that book.\n\n");
                    return;
                }
                else {
                    shoppingCart.add(purchasing);
                    isEbook.add(false);
                    System.out.println("Item added to cart.\n\n"); // Removes 1 Physical Copy of the book chosen.
                    for (int i = 0; i < books.size(); i++) {
                        if (purchasing == books.get(i)) {
                            books.remove(i);
                            Book updatedBook = new Book(purchasing.getBookName(), purchasing.getAuthor(), purchasing.getPCopies()-1, purchasing.getECopies());
                            books.add(i, updatedBook);
                        }
                    }
                    return;
                }
            }
        }      
    }
    
    public static void viewCart() { // Displays the user's cart.
        if (shoppingCart.isEmpty()) {
            System.out.println("\n\nYour Shopping Cart is empty.\n\n");
        }
        else {
            int j = 1;
            System.out.println("\n\nYour Shopping Cart contains the following:");
            for (int i = 0; i < shoppingCart.size(); i++) { // Loop used to list user's book choices.
                System.out.println(j + ". " + shoppingCart.get(i).getBookName());
                j++;
           }
           System.out.println("\n\n");
        }
    }
    
    public static void removeBook() { // Removes book(s).
        if (shoppingCart.isEmpty()) {
            System.out.println("\n\nThere is no book to be removed.\n\n");
            return;
        }
        Scanner scan = new Scanner(System.in);
        System.out.println("\n\nWhich number item do you wish to remove: "); 
        int j = 1;
        for (int i = 0; i < shoppingCart.size(); i++) {
            System.out.println(j + ". " + shoppingCart.get(i).getBookName());
            j++;
        }

        System.out.println("0. cancel.\n");

        String option = scan.next();
        
        if (option == "0") {
            System.out.println("Cancelled.\n\n");
        }
        else {
            int foundIndex = Integer.parseInt(option) - 1;

            if (foundIndex >= shoppingCart.size()) {
                System.out.println("Invalid  option.\n\n");
                return;
            }
            Book removedBook = shoppingCart.get(foundIndex);
            shoppingCart.remove(foundIndex);
            System.out.println("Your item has been removed.\n\n"); // This is used to update the physical copy of the book which was removed earlier and now added back. 
            if (!isEbook.get(foundIndex)) {
                for (int i = 0; i < books.size(); i++) {
                    if (removedBook == books.get(i)) {
                        books.remove(i);
                        Book updatedBook = new Book(removedBook.getBookName(), removedBook.getAuthor(), removedBook.getPCopies()+1, removedBook.getECopies());
                        books.add(i, updatedBook);
                }
            }
        }
    }
}

    public static void checkOut() { // Checks out the books chosen by the user with the total price at the end.

        double totalPrice = 0.0;
        if (shoppingCart.isEmpty()) {
            System.out.println("\n\nYour cart is empty.\n\n");
        }
        else {
            for (int i = 0; i < shoppingCart.size(); i++) {
                if (isEbook.get(i) == true){
                    totalPrice = totalPrice + 8.0;
                }
                else {
                    totalPrice = totalPrice + 30.0;
                }
            }
            System.out.println("You have purchased items to the total value of $" + totalPrice);
            System.out.println("Thanks for shopping with Daintree!\n\n");
        }
    }

    public static void listBooks() { // Lists all the available books and the available copies (E-Book and Physical).
        System.out.println("\n\nThe following titles are available:");
        for (int i = 0; i < books.size(); i++) {
            Book currentBook = books.get(i);
            if (currentBook.getPCopies() == 0 && currentBook.getECopies() == false) {
                continue;
            }
            System.out.print(i + ". " + currentBook.getBookName() + " -- " + currentBook.getAuthor() + 
                            ", " + currentBook.getPCopies() + ", ");
            if (currentBook.getECopies() == true) {
                System.out.println("ebook available");
            }
            else {
                System.out.println("no ebook");
            }
        }
        System.out.println("\n\n");   
    }

    public static void main(String[] args) { // Main Method.
        System.out.println("Welcome to Daintree!");
        initializingValues();
    boolean exit = false;
    while(exit == false) { // User Options.
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

        switch (selection) { // User Inputs lead them to the selected option.
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


