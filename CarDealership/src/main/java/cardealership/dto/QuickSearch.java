/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cardealership.dto;

import java.math.BigDecimal;

/**
 *
 * @author Jeonghoon
 */
public class QuickSearch {
    private String keyword;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private int minYear;
    private int maxYear;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public int getMinYear() {
        return minYear;
    }

    public void setMinYear(int minYear) {
        this.minYear = minYear;
    }

    public int getMaxYear() {
        return maxYear;
    }

    public void setMaxYear(int maxYear) {
        this.maxYear = maxYear;
    }

    @Override
    public String toString() {
        return "QuickSearch{" + "keyword=" + keyword + ", minPrice=" + minPrice + ", maxPrice=" + maxPrice + ", minYear=" + minYear + ", maxYear=" + maxYear + '}';
    }
}
