/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cardealership.dao;

import cardealership.dto.Special;
import cardealership.dto.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author nicolemagpantay
 */
@Repository
public class SpecialDaoDB implements SpecialDao {

    @Autowired
    JdbcTemplate jdbc;

    public static final class SpecialMapper implements RowMapper<Special> {

        @Override
        public Special mapRow(ResultSet rs, int index) throws SQLException {
            Special special = new Special();
            special.setSpecialId(rs.getInt("specialId"));
            special.setTitle(rs.getString("title"));
            special.setDescription(rs.getString("specialDescription"));

            return special;
        }
    }

    @Override
    public Special createSpecial(Special special) {
        final String INSERT_SPECIAL = "INSERT INTO special(title, specialDescription, userId) "
                + "VALUES(?,?,?)";
        jdbc.update(INSERT_SPECIAL,
                special.getTitle(),
                special.getDescription(),
                special.getUser().getUserId()
        );
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        special.setSpecialId(newId);
        return special;

    }

    @Override
    public List<Special> getAllSpecial() {
        final String SELECT_ALL_SPECIAL = "SELECT * FROM special";
        return jdbc.query(SELECT_ALL_SPECIAL, new SpecialMapper());
    }

    @Override
    public void deleteSpecialById(int id) {
        final String DELETE_SPECIAL = "DELETE FROM special WHERE specialId = ?";
        jdbc.update(DELETE_SPECIAL, id);
    }

}
