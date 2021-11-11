package com.company.Summative2KhanSabur.dao;

import com.company.Summative2KhanSabur.model.book;

import java.util.List;

public interface bookDao {
    book addBook (book book);
    book getBook(int id);
    List<book> getAllBooks();
    void updateBook(book book);
    void deleteBook(int id);
    List<book> getBooksByAuthor(int author_id);
}
