/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cardealership.dao;

import cardealership.dto.PurchaseType;
import cardealership.dto.Sales;
import cardealership.dto.State;
import java.util.List;

/**
 *
 * @author Jeonghoon
 */
public interface SalesDao {

    Sales createSales(Sales sales);

    List<Sales> getAllSales();

    Sales getSalesById(int id);

    void deleteSalesById(int id);

    void updateSales(Sales sales);

    // TODO: we may need following methods
    List<Sales> getAllSalesByUser(int userId);

    Sales getSalesByVin(String vin);

    List<PurchaseType> getAllPurchaseType();

    List<State> getAllState();
}
