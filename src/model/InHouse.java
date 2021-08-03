package model;

import model.Part;

/**
 *
 * @author Mariya.Trenkina
 */
public class InHouse extends Part {

    private int machineId;

    /**
     *
     * @param id
     * @param name
     * @param price
     * @param stock
     * @param min
     * @param max
     * @param machineId
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /**
     *
     * @return the machineId
     */
    public int getMachineId() {
        return machineId;
    }

    /**
     *
     * @param machineId set the machineId
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}