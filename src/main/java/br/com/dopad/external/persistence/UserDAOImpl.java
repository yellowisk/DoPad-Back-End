package br.com.dopad.external.persistence;

import br.com.dopad.domain.entities.user.User;
import br.com.dopad.usecases.user.gateway.UserDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class UserDAOImpl implements UserDAO {
    private final JdbcTemplate jdbcTemplate;

    public UserDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Value("${queries.sql.user-dao.insert.user}")
    private String insertUserQuery;

    @Value("${queries.sql.user-dao.select.user-by-id}")
    private String selectUserByIdQuery;

    @Value("${queries.sql.user-dao.select.user-by-username}")
    private String selectUserByUserNameQuery;

    @Transactional
    @Override
    public User saveUser(User user) {
        UUID userId = UUID.randomUUID();
        jdbcTemplate.update(insertUserQuery, userId, user.getUsername(), user.getPassword());
        return user.getNewInstanceWithId(userId);
    }

    @Override
    public User findUserById(UUID id) {
        return jdbcTemplate.queryForObject(selectUserByIdQuery, this::mapperUserFromRs, id);
    }

    @Override
    public User findUserByUsername(String username) {
        return jdbcTemplate.queryForObject(selectUserByUserNameQuery, this::mapperUserFromRs, username);
    }

    public User mapperUserFromRs(ResultSet rs, int rowNum) throws SQLException {
        UUID userId = (UUID) rs.getObject("id");
        String name = rs.getString("username");
        String password = rs.getString("password");
        return User.createFull(userId, name, password);
    }

}
