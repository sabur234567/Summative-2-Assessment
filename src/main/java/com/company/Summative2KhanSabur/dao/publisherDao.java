package com.company.Summative2KhanSabur.dao;

import com.company.Summative2KhanSabur.model.publisher;

import java.util.List;


public interface publisherDao {
    publisher addPublisher(publisher publisher);
    publisher getPublisher(int id);
    List<publisher> getAllPublishers();
    void updatePublisher(publisher publisher);
    void deletePublisher (int id);
}
