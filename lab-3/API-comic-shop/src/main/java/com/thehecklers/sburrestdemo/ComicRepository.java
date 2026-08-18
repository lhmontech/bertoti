package com.thehecklers.sburrestdemo;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ComicRepository {
    private final JdbcTemplate jdbcTemplate;

    public ComicRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Comic comic) {
        String sql = "INSERT INTO comics (id, name, publisher, price)\n" +
                     "VALUES (?, ?, ?, ?)\n";

        jdbcTemplate.update(
                sql,
                comic.getId(),
                comic.getName(),
                comic.getPublisher(),
                comic.getPrice()
        );
    }

    public List<Comic> findAllComics() {
        String sql = "SELECT * FROM comics";

        return jdbcTemplate.query(sql, (rs, rowNum) -> new Comic(
                rs.getString("id"),
                rs.getString("name"),
                rs.getString("publisher"),
                rs.getDouble("price")
        ));
    }

    public Optional<Comic> findComicById(String id) {
        String sql = "SELECT * FROM comics WHERE id = ?";

        try {
            Comic comic = jdbcTemplate.queryForObject(
                    sql,
                    new BeanPropertyRowMapper<>(Comic.class),
                    id
            );

            return Optional.ofNullable(comic);

        } catch (EmptyResultDataAccessException e) {

            return Optional.empty();
        }
    }

    public int update(Comic comic){
        String sql = "UPDATE comics SET name = ?, publisher = ?, price = ? WHERE id = ?";

        return jdbcTemplate.update(
                sql,
                comic.getName(),
                comic.getPublisher(),
                comic.getPrice(),
                comic.getId()
        );
    }

    public int delete(String id){
        String sql = "DELETE FROM comics WHERE id = ?";

        return jdbcTemplate.update(sql, id);
    }

}
