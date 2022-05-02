/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cardealership.dao;

import cardealership.dto.Special;
import java.util.List;

/**
 *
 * @author Nicole
 */
public interface SpecialDao {

    public Special createSpecial(Special special);

    public List<Special> getAllSpecial();

    public void deleteSpecialById(int id);

}
