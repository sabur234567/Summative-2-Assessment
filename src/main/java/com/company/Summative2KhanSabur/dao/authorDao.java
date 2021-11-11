package com.company.Summative2KhanSabur.dao;

import com.company.Summative2KhanSabur.model.author;

import java.util.List;

public interface authorDao {
    author addAuthor(author author);
    author getAuthor(int id);
    List<author> getAllAuthors();
    void updateAuthor(author author);
    void deleteAuthor(int id);

}
