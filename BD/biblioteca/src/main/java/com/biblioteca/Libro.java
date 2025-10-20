package com.biblioteca;

public class Libro {

    String title;
    String author;
    int yearOfP;
    String ISBN;
    public Libro(final String title, final String author, final int yearOfP, final String iSBN) {
        this.title = title;
        this.author = author;
        this.yearOfP = yearOfP;
        ISBN = iSBN;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public int getYearOfP() {
        return yearOfP;
    }
    public String getISBN() {
        return ISBN;
    }
    public void setTitle(final String title) {
        this.title = title;
    }
    public void setAuthor(final String author) {
        this.author = author;
    }
    public void setYearOfP(final int yearOfP) {
        this.yearOfP = yearOfP;
    }
    public void setISBN(final String iSBN) {
        ISBN = iSBN;
    }
    @Override
    public String toString() {
        return "Libro [getTitle()=" + getTitle() + ", getAuthor()=" + getAuthor() + ", getYearOfP()=" + getYearOfP()
                + ", getISBN()=" + getISBN() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
                + ", toString()=" + super.toString() + "]";
    }
}
