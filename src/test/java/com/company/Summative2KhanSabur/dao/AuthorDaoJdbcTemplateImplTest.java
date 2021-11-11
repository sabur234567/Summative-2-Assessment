package com.company.Summative2KhanSabur.dao;

import com.company.Summative2KhanSabur.model.author;
import com.company.Summative2KhanSabur.model.book;
import com.company.Summative2KhanSabur.model.publisher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AuthorDaoJdbcTemplateImplTest {

    @Autowired
    protected authorDao authorDao;

    @Autowired
    protected bookDao bookDao;

    @Autowired
    protected publisherDao publisherDao;

    @Before
    public void setUp() throws Exception {
        // clean out the test db

        List<book> bList = bookDao.getAllBooks();

        bList.stream()
                .forEach(book -> bookDao.deleteBook(book.getBook_id()));

        List<publisher> pList = publisherDao.getAllPublishers();

        pList.stream()
                .forEach(publisher -> publisherDao.deletePublisher(publisher.getPublisher_id()));

        List<author> aList = authorDao.getAllAuthors();

        aList.stream()
                .forEach(author -> authorDao.deleteAuthor(author.getAuthor_id()));

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void addGetDeleteAuthor() {
        author newAuthor = new author();
      //  newAuthor.setAuthor_id(12345);
        newAuthor.setFirst_name("sabur");
        newAuthor.setLast_name("khan");
        newAuthor.setStreet("123 congress av.");
        newAuthor.setPostal_code("78728");
        newAuthor.setCity("Austin");
        newAuthor.setState("TX");
        newAuthor.setPhone("5123780062");
        newAuthor.setEmail("sabur@gmail.com");

        //add and get
        newAuthor = authorDao.addAuthor(newAuthor);

        author author2 = authorDao.getAuthor(newAuthor.getAuthor_id());

        assertEquals(newAuthor, author2);

        //delete and get
        authorDao.deleteAuthor(newAuthor.getAuthor_id());

        author2 = authorDao.getAuthor(newAuthor.getAuthor_id());

        assertNull(author2);
    }
    @Test
    public void getAllAuthors(){
        author newAuthor = new author();
        newAuthor.setAuthor_id(12345);
        newAuthor.setFirst_name("sabur");
        newAuthor.setLast_name("khan");
        newAuthor.setStreet("123 congress av.");
        newAuthor.setPostal_code("78728");
        newAuthor.setCity("Austin");
        newAuthor.setState("TX");
        newAuthor.setPhone("5123780062");
        newAuthor.setEmail("sabur@gmail.com");

        authorDao.addAuthor(newAuthor);

        author secondAuthor = new author();
        secondAuthor.setAuthor_id(54321);
        secondAuthor.setFirst_name("sam");
        secondAuthor.setLast_name("k");
        secondAuthor.setStreet("533 congress av.");
        secondAuthor.setPostal_code("78665");
        secondAuthor.setCity("Round Rock");
        secondAuthor.setState("TX");
        secondAuthor.setPhone("512251925");
        secondAuthor.setEmail("sam@gmail.com");

        authorDao.addAuthor(secondAuthor);

        //Get All
        List<author> authorList = authorDao.getAllAuthors();

        assertEquals(authorList.size(), 2);
    }
    @Test
    public void updateAuthor(){
        author newAuthor = new author();
        newAuthor.setAuthor_id(12514);
        newAuthor.setFirst_name("Nathan");
        newAuthor.setLast_name("Drake");
        newAuthor.setStreet("2349 ox wagon trail");
        newAuthor.setPostal_code("78665");
        newAuthor.setCity("round rock");
        newAuthor.setState("TX");
        newAuthor.setPhone("21515612");
        newAuthor.setEmail("nathan@gmail.com");

        authorDao.addAuthor(newAuthor);

        //make changes
        newAuthor.setFirst_name("Adam");
        newAuthor.setPhone("51241252");

        //update
        authorDao.updateAuthor(newAuthor);

        author author2 = authorDao.getAuthor(newAuthor.getAuthor_id());

        //check
        assertEquals(author2, newAuthor);
    }

}
