// package com.mycompany.app
public class Book {

    private String bName;
    private String bAuthor;
    private int pCopies;
    private boolean eBook; 

    public Book(String bName, String bAuthor, int pCopies, boolean eBook) {
        this.bName = bName;
        this.bAuthor = bAuthor;
        this.pCopies = pCopies;
        this.eBook = eBook;
    }

    public String getBookName() {
        return bName;
    }

    public String getAuthor() {
        return bAuthor;
    }

    public int getPCopies() {
        return pCopies;
    }

    public boolean getECopies() {
        return eBook;
    }
}