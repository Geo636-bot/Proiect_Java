package ro.store.inventory.model;

import java.util.Objects;

/**
 * Abstract base class demonstrating Inheritance and Encapsulation.
 * Implements Comparable to allow natural sorting by price in Collections.
 */
public abstract class AbstractProduct implements Comparable<AbstractProduct> {
    protected int id;
    protected String name;
    protected double price; // Note: In enterprise apps, BigDecimal is preferred for currency.
    protected int stockQuantity;
    protected Category category;
    protected Distributor distributor;

    public AbstractProduct(int id, String name, double price, int stockQuantity, Category category, Distributor distributor) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.category = category;
        this.distributor = distributor;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    public Distributor getDistributor() { return distributor; }
    public void setDistributor(Distributor distributor) { this.distributor = distributor; }

    // Abstract method to be implemented by child classes
    public abstract String getProductType();

    // Natural sorting by price
    @Override
    public int compareTo(AbstractProduct other) {
        return Double.compare(this.price, other.price);
    }

    // Equals and HashCode are crucial for standard Java Collections (Sets, Maps)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractProduct that = (AbstractProduct) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Product{" + "name='" + name + '\'' + ", price=" + price + ", stock=" + stockQuantity + '}';
    }
}