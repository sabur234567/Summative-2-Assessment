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
import java.sql.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BookDaoJdbcTemplateImplTest {

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
    public void addGetDeleteBook() {
        author newAuthor = new author();
        newAuthor.setFirst_name("sabur");
        newAuthor.setLast_name("khan");
        newAuthor.setStreet("123 congress av.");
        newAuthor.setPostal_code("78728");
        newAuthor.setCity("Austin");
        newAuthor.setState("TX");
        newAuthor.setPhone("5123780062");
        newAuthor.setEmail("sabur@gmail.com");

        authorDao.addAuthor(newAuthor);

        publisher newPublisher = new publisher();
        newPublisher.setName("Hasan");
        newPublisher.setStreet("2421 ruby red dr");
        newPublisher.setCity("Houston");
        newPublisher.setState("TX");
        newPublisher.setPostal_code("78665");
        newPublisher.setPhone("512512512");
        newPublisher.setEmail("h123@gmail.com");

        publisherDao.addPublisher(newPublisher);

        book newBook = new book();
        newBook.setIsbn("215125");
        String str="2015-03-31";
        Date date= Date.valueOf(str);//converting string into sql date
        newBook.setPublish_date(date);
        newBook.setAuthor_id(newAuthor.getAuthor_id());
        newBook.setTitle("My autobiography");
        newBook.setPublisher_id(newPublisher.getPublisher_id());
        newBook.setPrice(26.50);

        //add and get
        newBook = bookDao.addBook(newBook);

        book book2 = bookDao.getBook(newBook.getBook_id());

        assertEquals(book2, newBook);

        //delete
        bookDao.deleteBook(newBook.getBook_id());

        book2 = bookDao.getBook(newBook.getBook_id());

        assertNull(book2);
    }
    @Test
    public void updateBook() {
        author newAuthor = new author();
        newAuthor.setFirst_name("sabur");
        newAuthor.setLast_name("khan");
        newAuthor.setStreet("123 congress av.");
        newAuthor.setPostal_code("78728");
        newAuthor.setCity("Austin");
        newAuthor.setState("TX");
        newAuthor.setPhone("5123780062");
        newAuthor.setEmail("sabur@gmail.com");

        authorDao.addAuthor(newAuthor);

        publisher newPublisher = new publisher();
        newPublisher.setName("Hasan");
        newPublisher.setStreet("2421 ruby red dr");
        newPublisher.setCity("Houston");
        newPublisher.setState("TX");
        newPublisher.setPostal_code("78665");
        newPublisher.setPhone("512512512");
        newPublisher.setEmail("h123@gmail.com");

        publisherDao.addPublisher(newPublisher);

        book newBook = new book();
        newBook.setIsbn("215125");
        String str="2015-03-31";
        Date date= Date.valueOf(str);//converting string into sql date
        newBook.setPublish_date(date);
        newBook.setAuthor_id(newAuthor.getAuthor_id());
        newBook.setTitle("My autobiography");
        newBook.setPublisher_id(newPublisher.getPublisher_id());
        newBook.setPrice(26.50);

        //add the book
        newBook = bookDao.addBook(newBook);

        //update
        newBook.setTitle("UPDATED");
        newBook.setPrice(15.50);

        bookDao.updateBook(newBook);

        book book2 = bookDao.getBook(newBook.getBook_id());

        assertEquals(book2, newBook);
    }
    @Test
    public void getAllBooks(){

        author newAuthor = new author();
        newAuthor.setFirst_name("sam");
        newAuthor.setLast_name("king");
        newAuthor.setStreet("123 ruby red dr");
        newAuthor.setPostal_code("78215");
        newAuthor.setCity("Austin");
        newAuthor.setState("TX");
        newAuthor.setPhone("21512231");
        newAuthor.setEmail("sam@gmail.com");

        authorDao.addAuthor(newAuthor);

        publisher newPublisher = new publisher();
        newPublisher.setName("Hasan");
        newPublisher.setStreet("2421 ruby red dr");
        newPublisher.setCity("Houston");
        newPublisher.setState("TX");
        newPublisher.setPostal_code("78665");
        newPublisher.setPhone("512512512");
        newPublisher.setEmail("h123@gmail.com");

        publisherDao.addPublisher(newPublisher);


        book newBook = new book();
        newBook.setIsbn("215125");
        String str="2014-02-21";
        Date date= Date.valueOf(str);//converting string into sql date
        newBook.setPublish_date(date);
        newBook.setAuthor_id(newAuthor.getAuthor_id());
        newBook.setTitle("Kite runner");
        newBook.setPublisher_id(newPublisher.getPublisher_id());
        newBook.setPrice(26.50);

        bookDao.addBook(newBook);

        book book2 = new book();
        book2.setIsbn("125123");
        String str2 ="2017-12-25";
        Date date2 = Date.valueOf(str2);//converting string into sql date
        book2.setPublish_date(date2);
        book2.setAuthor_id(newAuthor.getAuthor_id());
        book2.setTitle("How to ride a bike");
        book2.setPublisher_id(newPublisher.getPublisher_id());
        book2.setPrice(15.50);

        bookDao.addBook(book2);

        List<book> bookList = bookDao.getAllBooks();

        assertEquals(bookList.size(), 2);

    }
    @Test
    public void getBookByAuthor() {

        publisher newPublisher = new publisher();
        newPublisher.setName("Ruby");
        newPublisher.setStreet("2421 ruby red dr");
        newPublisher.setCity("Austin");
        newPublisher.setState("TX");
        newPublisher.setPostal_code("66215");
        newPublisher.setPhone("51251252");
        newPublisher.setEmail("ruby@gmail.com");

        publisherDao.addPublisher(newPublisher);

        author author1 = new author();
        author1.setFirst_name("Sam");
        author1.setLast_name("King");
        author1.setStreet("2349 ox wagon trail");
        author1.setPostal_code("78215");
        author1.setCity("Round Rock");
        author1.setState("TX");
        author1.setPhone("512624152");
        author1.setEmail("sam@gmail.com");

        authorDao.addAuthor(author1);

        author author2 = new author();
        author2.setFirst_name("Bryson");
        author2.setLast_name("King");
        author2.setStreet("242 Lake way creek");
        author2.setPostal_code("72242");
        author2.setCity("Austin");
        author2.setState("TX");
        author2.setPhone("2151252");
        author2.setEmail("bk@gmail.com");

        authorDao.addAuthor(author2);

        author author3 = new author();
        author3.setFirst_name("Aubrey");
        author3.setLast_name("drake");
        author3.setStreet("125 barton creek");
        author3.setPostal_code("84125");
        author3.setCity("Dallas");
        author3.setState("TX");
        author3.setPhone("12451252");
        author3.setEmail("AB@gmail.com");

        authorDao.addAuthor(author3);

        book book1 = new book();
        book1.setIsbn("125123");
        String createStringDate1 ="2010-5-25";
        Date createDate1 = Date.valueOf(createStringDate1);//converting string into sql date
        book1.setPublish_date(createDate1);
        book1.setAuthor_id(author1.getAuthor_id());
        book1.setTitle("How to eat properly");
        book1.setPublisher_id(newPublisher.getPublisher_id());
        book1.setPrice(11.99);

        bookDao.addBook(book1);

        book book2 = new book();
        book2.setIsbn("125236");
        String createStringDate2 ="2019-1-01";
        Date createDate2 = Date.valueOf(createStringDate2);//converting string into sql date
        book2.setPublish_date(createDate2);
        book2.setAuthor_id(author1.getAuthor_id());
        book2.setTitle("How to ride a motorcycle");
        book2.setPublisher_id(newPublisher.getPublisher_id());
        book2.setPrice(9.99);

        bookDao.addBook(book2);

        book book3 = new book();
        book3.setIsbn("712255");
        String createStringDate3 ="2012-6-06";
        Date createDate3 = Date.valueOf(createStringDate3);//converting string into sql date
        book3.setPublish_date(createDate3);
        book3.setAuthor_id(author2.getAuthor_id());
        book3.setTitle("How to drive a car");
        book3.setPublisher_id(newPublisher.getPublisher_id());
        book3.setPrice(6.99);

        bookDao.addBook(book3);

        List<book> bList = bookDao.getBooksByAuthor(author1.getAuthor_id());
        assertEquals(2, bList.size());

        List<book> bList2 = bookDao.getBooksByAuthor(author2.getAuthor_id());
        assertEquals(1, bList2.size());

        List<book> bList3 = bookDao.getBooksByAuthor(author3.getAuthor_id());
        assertEquals(0, bList3.size());
    }
}
