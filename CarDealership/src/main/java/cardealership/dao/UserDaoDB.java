/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cardealership.dao;

import cardealership.dto.User;
import cardealership.dto.UserRole;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author nicolemagpantay
 */
@Repository
public class UserDaoDB implements UserDao {

    @Autowired
    JdbcTemplate jdbc;

    public static final class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int index) throws SQLException {
            User user = new User();
            user.setUserId(rs.getInt("userId"));
            user.setFirstName(rs.getString("firstName"));
            user.setLastName(rs.getString("lastName"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("userPassword"));

            return user;
        }
    }

    public static final class UserRoleMapper implements RowMapper<UserRole> {

        @Override
        public UserRole mapRow(ResultSet rs, int index) throws SQLException {
            UserRole role = new UserRole();
            role.setUserId(rs.getInt("userRoleId"));
            role.setName(rs.getString("name"));

            return role;
        }
    }

    @Override
    public User getUserById(int id) {
        try {
            final String SELECT_USER_BY_ID = "SELECT * FROM user WHERE id = ?";
            return jdbc.queryForObject(SELECT_USER_BY_ID, new UserMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
        final String SELECT_ALL_USERS = "SELECT * FROM user";
        return jdbc.query(SELECT_ALL_USERS, new UserMapper());
    }

    @Override
    public User createUser(User user) {
        final String INSERT_USER = "INSERT INTO user(lastName, firstName, email, password)";
        jdbc.update(INSERT_USER, user.getLastName(), user.getFirstName(), user.getEmail(), user.getPassword());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        user.setUserId(newId);
        return user;

    }

    @Override
    public void deleteUserById(int id) {

        final String DELETE_USER = "DELETE FROM user WHERE id = ?";
        jdbc.update(DELETE_USER, id);
    }

    @Override
    public void updateUser(User user) {
        final String UPDATE_USER = "UPDATE user SET "
                + "firstName = ?, lastName = ?, "
                + "email = ?, password = ? "
                + "WHERE userId = ?";
        jdbc.update(UPDATE_USER,
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getUserId()
        );
    }
}
