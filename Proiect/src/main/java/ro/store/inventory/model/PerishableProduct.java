package ro.store.inventory.model;

import java.time.LocalDate;

/**
 * Inherits from AbstractProduct. Adds specific fields for perishable goods.
 */
public class PerishableProduct extends AbstractProduct {
    private LocalDate expirationDate;

    public PerishableProduct(int id, String name, double price, int stockQuantity, Category category, Distributor distributor, LocalDate expirationDate) {
        super(id, name, price, stockQuantity, category, distributor);
        this.expirationDate = expirationDate;
    }

    public LocalDate getExpirationDate() { return expirationDate; }
    public void setExpirationDate(LocalDate expirationDate) { this.expirationDate = expirationDate; }

    @Override
    public String getProductType() {
        return "Perishable";
    }

    @Override
    public String toString() {
        return super.toString() + " [Expires: " + expirationDate + "]";
    }
}