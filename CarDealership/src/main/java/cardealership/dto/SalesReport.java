/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cardealership.dto;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author Jeonghoon
 */
public class SalesReport {

    private int userId;
    private String userName;
    private BigDecimal totalSalesAmount;
    private int totalCount;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public BigDecimal getTotalSalesAmount() {
        return totalSalesAmount;
    }

    public void setTotalSalesAmount(BigDecimal totalSalesAmount) {
        this.totalSalesAmount = totalSalesAmount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.userName);
        hash = 67 * hash + Objects.hashCode(this.totalSalesAmount);
        hash = 67 * hash + this.totalCount;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SalesReport other = (SalesReport) obj;
        if (this.totalCount != other.totalCount) {
            return false;
        }
        if (!Objects.equals(this.userName, other.userName)) {
            return false;
        }
        return Objects.equals(this.totalSalesAmount, other.totalSalesAmount);
    }

}
