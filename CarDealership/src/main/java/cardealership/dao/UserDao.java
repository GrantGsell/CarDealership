/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cardealership.dao;

import cardealership.dto.User;
import java.util.List;

/**
 *
 * @author Jeonghoon
 */
public interface UserDao {
    public User createUser(User user);

    public List<User> getAllUsers();

    public User deleteUserById(int id);

    public boolean updateUser(User user);
}
