/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cardealership.dao;

import cardealership.dto.PurchaseType;
import cardealership.dto.Sales;
import cardealership.dto.State;
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
public class SalesDaoDB implements SalesDao {

    @Autowired
    JdbcTemplate jdbc;

    public static final class SalesMapper implements RowMapper<Sales> {

        @Override
        public Sales mapRow(ResultSet rs, int index) throws SQLException {
            Sales sales = new Sales();
            sales.setSalesId(rs.getInt(rs.getInt("salesId")));
            sales.setName(rs.getString("nameSales"));
            sales.setPhone(rs.getString("phone"));
            sales.setStreet1(rs.getString("street1"));
            sales.setStreet2(rs.getString("street2"));
            sales.setCity(rs.getString("city"));
            sales.setZipcode(rs.getString("zipcode"));
            sales.setPurchasePrice(rs.getBigDecimal("purchasePrice"));

            return sales;
        }
    }

    public static final class StateMapper implements RowMapper<State> {

        @Override
        public State mapRow(ResultSet rs, int index) throws SQLException {
            State state = new State();
            state.setStateId(rs.getInt("stateId"));
            state.setStateName(rs.getString("stateName"));
            state.setStateAbbrev(rs.getString("stateAbbrev"));

            return state;
        }
    }

    public static final class PurchaseTypeMapper implements RowMapper<PurchaseType> {

        @Override
        public PurchaseType mapRow(ResultSet rs, int index) throws SQLException {
            PurchaseType purchaseType = new PurchaseType();
            purchaseType.setPurchaseTypeId(rs.getInt("purchaseTypeId"));
            purchaseType.setPurchaseName(rs.getString("purchaseName"));

            return purchaseType;
        }
    }

    @Override
    public Sales createSales(Sales sales) {
        final String INSERT_SALES = "INSERT INTO sales(nameSales, phone, email, "
                + "street1, street2, city, stateId, zipcode, "
                + "purchasePrice, vin, purchaseTypeId, userId) "
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        jdbc.update(INSERT_SALES,
                sales.getName(),
                sales.getPhone(),
                sales.getEmail(),
                sales.getStreet1(),
                sales.getStreet2(),
                sales.getCity(),
                sales.getState().getStateId(),
                sales.getZipcode(),
                sales.getPurchasePrice(),
                sales.getVehicle().getVin(),
                sales.getPurchaseType().getPurchaseTypeId(),
                sales.getUser().getUserId()
        );

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        sales.setSalesId(newId);
        return sales;
    }

    @Override
    public List<Sales> getAllSales() {
        final String GET_ALL_SALES = "SELECT * FROM sales";
        List<Sales> salesList = jdbc.query(GET_ALL_SALES, new SalesMapper());

        associateOtherFieldsForSalesList(salesList);
        return salesList;
    }

    @Override
    public Sales getSalesById(int id) {
        try {
            final String SELECT_SALES_BY_ID = "SELECT * FROM salesInfo WHERE salseId = ?";

            Sales sales = jdbc.queryForObject(SELECT_SALES_BY_ID, new SalesMapper(), id);

            associateOtherFieldsForSales(sales);
            return sales;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public void deleteSalesById(int id) {
        final String DELETE_SALES = "DELETE FROM salesInfo WHERE id = ?";
        jdbc.update(DELETE_SALES, id);
    }

    @Override
    public void updateSales(Sales sales) {
        final String UPDATE_SALES = "UPDATE salesInfo SET "
                + "nameSales = ?, "
                + "phone = ?, "
                + "email = ?, "
                + "street1 = ?, "
                + "street2 = ?, "
                + "city = ?, "
                + "stateId = ?, "
                + "zipcode = ?, "
                + "purchasePrice = ?, "
                + "purchaseTypeId = ?, "
                + "userId = ? "
                + "WHERE id = ?";
        jdbc.update(UPDATE_SALES,
                sales.getName(),
                sales.getPhone(),
                sales.getEmail(),
                sales.getStreet1(),
                sales.getStreet2(),
                sales.getCity(),
                sales.getState().getStateId(),
                sales.getZipcode(),
                sales.getPurchasePrice(),
                sales.getVehicle().getVin(),
                sales.getPurchaseType().getPurchaseTypeId(),
                sales.getUser().getUserId()
        );
    }

    @Override
    public List<Sales> getAllSalesByUser(int userId) {
        final String GET_ALL_SALES = "SELECT * FROM sales WHERE userId = ?";
        List<Sales> salesList = jdbc.query(GET_ALL_SALES, new SalesMapper(), userId);

        associateOtherFieldsForSalesList(salesList);
        return salesList;
    }

    @Override
    public Sales getSalesByVin(String vin) {
        try {
            final String SELECT_SALES_BY_VIN = "SELECT * FROM salesInfo WHERE vin = ?";

            Sales sales = jdbc.queryForObject(SELECT_SALES_BY_VIN, new SalesMapper(), vin);

            associateOtherFieldsForSales(sales);
            return sales;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<PurchaseType> getAllPurchaseType() {
        final String GET_ALL_PURCHASE_TYPE = "SELECT * FROM purchaseType";
        List<PurchaseType> purchaseTypeList
                = jdbc.query(GET_ALL_PURCHASE_TYPE, new PurchaseTypeMapper());

        return purchaseTypeList;
    }

    @Override
    public List<State> getAllState() {
        final String GET_ALL_STATE = "SELECT * FROM UsState";
        List<State> stateList
                = jdbc.query(GET_ALL_STATE, new StateMapper());

        return stateList;
    }

    private void associateOtherFieldsForSales(Sales sales) {
        // TODO: remove comment after building methods in User and Vehicle class

        sales.setState(getStateForSales(sales.getSalesId()));
//        sales.setVehicle(getVehicleForSales(sales.getSalesId()));
        sales.setPurchaseType(getPurchaseTypeForSales(sales.getSalesId()));
//        sales.setUser(getUserForSales(sales.getSalesId()));
    }

    private void associateOtherFieldsForSalesList(List<Sales> salesList) {
        salesList.forEach(this::associateOtherFieldsForSales);
    }

    private State getStateForSales(int salesId) {
        final String SELECT_STATE_FOR_SALES = "SELECT u.* FROM UsState u "
                + "JOIN salesInfo s ON u.stateId = s.stateId WHERE s.salesId = ?";
        return jdbc.queryForObject(SELECT_STATE_FOR_SALES, new StateMapper(), salesId);
    }

    private PurchaseType getPurchaseTypeForSales(int salesId) {
        final String SELECT_PURCHASE_TYPE_FOR_SALES = "SELECT u.* FROM purchaseType p "
                + "JOIN salesInfo s ON p.purchaseTypeId = s.purchaseTypeId WHERE s.salesId = ?";
        return jdbc.queryForObject(SELECT_PURCHASE_TYPE_FOR_SALES, new PurchaseTypeMapper(), salesId);
    }

}
