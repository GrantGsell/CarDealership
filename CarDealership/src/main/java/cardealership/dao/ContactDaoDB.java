/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cardealership.dao;

import cardealership.dto.Contact;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jeonghoon
 */
@Repository
public class ContactDaoDB implements ContactDao {

    @Autowired
    JdbcTemplate jdbc;

    public static final class ContactMapper implements RowMapper<Contact> {

        @Override
        public Contact mapRow(ResultSet rs, int index) throws SQLException {
            Contact contact = new Contact();
            contact.setContactId(rs.getInt(rs.getInt("contactId")));
            contact.setName(rs.getString("contactName"));
            contact.setEmail(rs.getString("email"));
            contact.setPhone(rs.getString("phone"));
            contact.setMessage(rs.getString("message"));

            return contact;
        }
    }

    @Override
    public Contact createContact(Contact contact) {
        final String INSERT_CONTACT = "INSERT INTO contact(contactName, email, phone, message) "
                + "VALUES(?,?,?,?)";
        jdbc.update(INSERT_CONTACT,
                contact.getName(),
                contact.getEmail(),
                contact.getPhone(),
                contact.getMessage()
        );

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        contact.setContactId(newId);
        return contact;
    }

    @Override
    public List<Contact> getAllContacts() {
        final String GET_ALL_CONTACTS = "SELECT * FROM contact";
        return jdbc.query(GET_ALL_CONTACTS, new ContactMapper());
    }

    @Override
    public Contact getContactById(int id) {
        try {
            final String GET_CONTACT_BY_ID = "SELECT * FROM contact WHERE id = ?";
            return jdbc.queryForObject(GET_CONTACT_BY_ID, new ContactMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public void deleteContactById(int id) {
        final String DELETE_CONTACT = "DELETE FROM contact WHERE contactId = ?";
        jdbc.update(DELETE_CONTACT, id);
    }

    @Override
    public void updateContactById(Contact contact) {
        final String UPDATE_CONTACT = "UPDATE contact SET "
                + "contactName = ?, email = ?, "
                + "phone = ?, message = ? "
                + "WHERE id = ?";
        jdbc.update(UPDATE_CONTACT,
                contact.getName(),
                contact.getEmail(),
                contact.getPhone(),
                contact.getMessage(),
                contact.getContactId()
        );
    }

}
