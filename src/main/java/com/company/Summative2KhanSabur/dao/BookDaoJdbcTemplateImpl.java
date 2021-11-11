package com.company.Summative2KhanSabur.dao;

import com.company.Summative2KhanSabur.model.book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BookDaoJdbcTemplateImpl implements bookDao {
    // Prepared statement strings
    private static final String INSERT_BOOK_SQL =
            "insert into book (isbn, publish_date , author_id , title , publisher_id , price ) values (?, ?, ?, ?, ?, ?)";

    private static final String SELECT_BOOK_SQL =
            "select * from book where book_id = ?";

    private static final String SELECT_ALL_BOOK_SQL =
            "select * from book";

    private static final String DELETE_BOOK_SQL =
            "delete from book where book_id = ?";

    private static final String UPDATE_BOOK_SQL =
            "update book set isbn = ?, publish_date = ?, author_id = ?, title = ?, publisher_id = ?, price = ? where book_id = ?";

    private static final String SELECT_BOOK_BY_AUTHOR_SQL =
            "select * from book where author_id = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDaoJdbcTemplateImpl (JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public book addBook(book book) {
        jdbcTemplate.update(
                INSERT_BOOK_SQL,
                book.getIsbn(),
                book.getPublish_date(),
                book.getAuthor_id(),
                book.getTitle(),
                book.getPublisher_id(),
                book.getPrice());

        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);

        book.setBook_id(id);

        return book;
    }

    @Override
    public book getBook(int id) {

        try {

            return jdbcTemplate.queryForObject(SELECT_BOOK_SQL, this::mapRowToBook, id);

        } catch (EmptyResultDataAccessException e) {
            // if nothing is returned just catch the exception and return null
            return null;
        }
    }

    @Override
    public List<book> getAllBooks() {
        return jdbcTemplate.query(SELECT_ALL_BOOK_SQL, this::mapRowToBook);
    }

    @Override
    public void updateBook(book book) {
        jdbcTemplate.update(
                UPDATE_BOOK_SQL,
                book.getIsbn(),
                book.getPublish_date(),
                book.getAuthor_id(),
                book.getTitle(),
                book.getPublisher_id(),
                book.getPrice(),
                book.getBook_id());
    }

    @Override
    public void deleteBook(int id) {
        jdbcTemplate.update(DELETE_BOOK_SQL, id);
    }

    @Override
    public List<book> getBooksByAuthor(int author_id) {
        return jdbcTemplate.query(SELECT_BOOK_BY_AUTHOR_SQL, this::mapRowToBook, author_id);
    }

    //Helper Method
    private book mapRowToBook (ResultSet rs, int rowNum) throws SQLException {
        book book =new book();

        book.setBook_id(rs.getInt("book_id"));
        book.setIsbn(rs.getString("isbn"));
        book.setPublish_date(rs.getDate("publish_date"));
        book.setAuthor_id(rs.getInt("author_id"));
        book.setTitle(rs.getString("title"));
        book.setPublisher_id(rs.getInt("publisher_id"));
        book.setPrice(rs.getDouble("price"));

        return book;
    }
}
