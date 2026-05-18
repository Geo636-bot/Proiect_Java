package ro.store.inventory.model;

/**
 * Inherits from AbstractProduct. Adds specific fields for electronics.
 */
public class ElectronicsProduct extends AbstractProduct {
    private int warrantyMonths;

    public ElectronicsProduct(int id, String name, double price, int stockQuantity, Category category, Distributor distributor, int warrantyMonths) {
        super(id, name, price, stockQuantity, category, distributor);
        this.warrantyMonths = warrantyMonths;
    }

    public int getWarrantyMonths() { return warrantyMonths; }
    public void setWarrantyMonths(int warrantyMonths) { this.warrantyMonths = warrantyMonths; }

    @Override
    public String getProductType() {
        return "Electronics";
    }

    @Override
    public String toString() {
        return super.toString() + " [Warranty: " + warrantyMonths + " months]";
    }
}