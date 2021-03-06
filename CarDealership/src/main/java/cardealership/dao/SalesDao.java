/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cardealership.dao;

import cardealership.dto.InventoryReport;
import cardealership.dto.PurchaseType;
import cardealership.dto.Sales;
import cardealership.dto.SalesReport;
import cardealership.dto.SalesReportSearchForm;
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

    List<Sales> getAllSalesByUser(int userId);

    Sales getSalesByVin(String vin);

    PurchaseType getPurchaseTypeById(int id);

    List<PurchaseType> getAllPurchaseType();

    State getStateById(int id);

    List<State> getAllState();

    List<SalesReport> getAllSalesReport(SalesReportSearchForm searchForm);

    List<InventoryReport> getInventoryReportForNewVehicles();

    List<InventoryReport> getInventoryReportForUsedVehicles();
}
