package com.company.Summative2KhanSabur.model;

import java.util.Date;
import java.util.Objects;

public class book {

    private int book_id;
    private String isbn;
    private Date publish_date;
    private double price;
    private int author_id;
    private String title;
    private int publisher_id;

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Date getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(Date publish_date) {
        this.publish_date = publish_date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public int getPublisher_id() {
        return publisher_id;
    }

    public void setPublisher_id(int publisher_id) {
        this.publisher_id = publisher_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof book)) return false;
        book book = (book) o;
        return book_id == book.book_id && Double.compare(book.price, price) == 0 && author_id == book.author_id && publisher_id == book.publisher_id && Objects.equals(isbn, book.isbn) && Objects.equals(publish_date, book.publish_date) && Objects.equals(title, book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book_id, isbn, publish_date, price, author_id, title, publisher_id);
    }
}
