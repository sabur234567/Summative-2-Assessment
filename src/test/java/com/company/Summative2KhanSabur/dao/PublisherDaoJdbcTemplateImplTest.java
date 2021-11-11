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
public class PublisherDaoJdbcTemplateImplTest {

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

    //CRRUD testing
    @Test
    public void addGetDeletePublisher() {
        publisher newPublisher = new publisher();
        newPublisher.setName("sabur");
        newPublisher.setStreet("2151 congress av.");
        newPublisher.setCity("Austin");
        newPublisher.setState("TX");
        newPublisher.setPostal_code("22363");
        newPublisher.setPhone("52135162");
        newPublisher.setEmail("sabur@gmail.com");

        //add and get
        newPublisher = publisherDao.addPublisher(newPublisher);

        publisher publisher2 = publisherDao.getPublisher(newPublisher.getPublisher_id());

        assertEquals(newPublisher, publisher2);

        //delete and get
        publisherDao.deletePublisher(newPublisher.getPublisher_id());

        publisher2 = publisherDao.getPublisher(newPublisher.getPublisher_id());

        assertNull(publisher2);
    }

    @Test
    public void getAllAuthors(){
        publisher newPublisher = new publisher();
        newPublisher.setName("sabrina");
        newPublisher.setStreet("141 W campus");
        newPublisher.setCity("Austin");
        newPublisher.setState("TX");
        newPublisher.setPostal_code("78352");
        newPublisher.setPhone("52135162");
        newPublisher.setEmail("sabrina@gmail.com");

        publisherDao.addPublisher(newPublisher);

        publisher secondPublisher = new publisher();
        secondPublisher.setName("ahmed");
        secondPublisher.setStreet("1512 E campus");
        secondPublisher.setCity("Dallas");
        secondPublisher.setState("TX");
        secondPublisher.setPostal_code("32626");
        secondPublisher.setPhone("53257231");
        secondPublisher.setEmail("ahmed@gmail.com");

        publisherDao.addPublisher(secondPublisher);

        //Get All
        List<publisher> publisherList = publisherDao.getAllPublishers();

        assertEquals(publisherList.size(), 2);
    }
    @Test
    public void updatePublisher(){
        publisher newPublisher = new publisher();
        newPublisher.setName("Hasan");
        newPublisher.setStreet("2421 ruby red dr");
        newPublisher.setCity("Houston");
        newPublisher.setState("TX");
        newPublisher.setPostal_code("78665");
        newPublisher.setPhone("512512512");
        newPublisher.setEmail("h123@gmail.com");

        publisherDao.addPublisher(newPublisher);

        //make changes
        newPublisher.setName("Adam");
        newPublisher.setPhone("5127331885");

        //update
        publisherDao.updatePublisher(newPublisher);

        publisher publisher2 = publisherDao.getPublisher(newPublisher.getPublisher_id());

        //check
        assertEquals(publisher2, newPublisher);
    }
}
