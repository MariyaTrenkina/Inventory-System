package model;

import model.Part;

/**
 *
 * @author Mariya.Trenkina
 */
public class Outsourced extends Part {

    private String companyName;

    /**
     *
     * @param id
     * @param name
     * @param price
     * @param stock
     * @param min
     * @param max
     * @param companyName
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     *
     * @return the company name
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     *
     * @param companyName set the company name
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}