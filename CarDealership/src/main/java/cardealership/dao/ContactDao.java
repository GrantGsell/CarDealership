/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cardealership.dao;

import cardealership.dto.Contact;
import java.util.List;

/**
 *
 * @author Jeonghoon
 */
public interface ContactDao {
    Contact createContact(Contact contact);

    List<Contact> getAllContacts();

    Contact getContactById(int id);

    void deleteContactById(int id);

    // TODO: we may not need this method.
    void updateContactById(Contact contact);
}
