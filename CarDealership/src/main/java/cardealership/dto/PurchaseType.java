/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cardealership.dto;

import java.util.Objects;

/**
 *
 * @author Jeonghoon
 */
public class PurchaseType {
    private int purchaseTypeId;
    private String purchaseName;

    public int getPurchaseTypeId() {
        return purchaseTypeId;
    }

    public void setPurchaseTypeId(int purchaseTypeId) {
        this.purchaseTypeId = purchaseTypeId;
    }

    public String getPurchaseName() {
        return purchaseName;
    }

    public void setPurchaseName(String purchaseName) {
        this.purchaseName = purchaseName;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.purchaseTypeId;
        hash = 53 * hash + Objects.hashCode(this.purchaseName);
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
        final PurchaseType other = (PurchaseType) obj;
        if (this.purchaseTypeId != other.purchaseTypeId) {
            return false;
        }
        return Objects.equals(this.purchaseName, other.purchaseName);
    }
}
