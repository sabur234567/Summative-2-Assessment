package com.company.Summative2KhanSabur.dao;

import com.company.Summative2KhanSabur.model.publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PublisherDaoJdbcTemplateImpl implements publisherDao {

    // Prepared statement strings
    private static final String INSERT_PUBLISHER_SQL =
            "insert into publisher (name, street , city , state , postal_code , phone, email ) values (?, ?, ?, ?, ?, ?, ?)";

    private static final String SELECT_PUBLISHER_SQL =
            "select * from publisher where publisher_id = ?";

    private static final String SELECT_ALL_PUBLISHER_SQL =
            "select * from publisher";

    private static final String DELETE_PUBLISHER_SQL =
            "delete from publisher where publisher_id = ?";

    private static final String UPDATE_PUBLISHER_SQL =
            "update publisher set name = ?, street = ?, city = ?, state = ?, postal_code = ?, phone = ?, email = ? where publisher_id = ?";


    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PublisherDaoJdbcTemplateImpl (JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public publisher addPublisher(publisher publisher) {
        jdbcTemplate.update(
                INSERT_PUBLISHER_SQL,
                publisher.getName(),
                publisher.getStreet(),
                publisher.getCity(),
                publisher.getState(),
                publisher.getPostal_code(),
                publisher.getPhone(),
                publisher.getEmail());

        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);

        publisher.setPublisher_id(id);

        return publisher;
    }

    @Override
    public publisher getPublisher(int id) {
        try {

            return jdbcTemplate.queryForObject(SELECT_PUBLISHER_SQL, this::mapRowToPublisher, id);

        } catch (EmptyResultDataAccessException e) {
            // if nothing is returned just catch the exception and return null
            return null;
        }
    }

    @Override
    public List<publisher> getAllPublishers() {

        return jdbcTemplate.query(SELECT_ALL_PUBLISHER_SQL, this::mapRowToPublisher);
    }

    @Override
    public void updatePublisher(publisher publisher) {

        jdbcTemplate.update(
                UPDATE_PUBLISHER_SQL,
                publisher.getName(),
                publisher.getStreet(),
                publisher.getCity(),
                publisher.getState(),
                publisher.getPostal_code(),
                publisher.getPhone(),
                publisher.getEmail(),
                publisher.getPublisher_id());
    }

    @Override
    public void deletePublisher(int id) {
        jdbcTemplate.update(DELETE_PUBLISHER_SQL, id);

    }
    //Helper Method
    private publisher mapRowToPublisher (ResultSet rs, int rowNum) throws SQLException {
        publisher publisher =new publisher();

        publisher.setPublisher_id(rs.getInt("publisher_id"));
        publisher.setName(rs.getString("name"));
        publisher.setStreet(rs.getString("street"));
        publisher.setCity(rs.getString("city"));
        publisher.setState(rs.getString("state"));
        publisher.setPostal_code(rs.getString("postal_code"));
        publisher.setPhone(rs.getString("phone"));
        publisher.setEmail(rs.getString("email"));

        return publisher;
    }
}
