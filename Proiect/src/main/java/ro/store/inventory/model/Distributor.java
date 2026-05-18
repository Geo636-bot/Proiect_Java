package ro.store.inventory.model;

/**
 * Represents a supplier/distributor providing products to the store.
 */
public class Distributor {
    private int id;
    private String companyName;
    private String contactEmail;

    public Distributor(int id, String companyName, String contactEmail) {
        this.id = id;
        this.companyName = companyName;
        this.contactEmail = contactEmail;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getContactEmail() { return contactEmail; }
    public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }

    @Override
    public String toString() {
        return "Distributor{" + "companyName='" + companyName + '\'' + '}';
    }
}