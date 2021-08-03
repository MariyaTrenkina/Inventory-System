package model;
import model.Part;
import model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Mariya.Trenkina
 */
public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private static ObservableList<Part> filteredParts = FXCollections.observableArrayList();
    private static ObservableList<Part> filteredAvailableParts = FXCollections.observableArrayList();
    private static ObservableList<Product> filteredProducts = FXCollections.observableArrayList();
    
    /**
     *
     * @return allParts list
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     *
     * @return allFilteredParts list
     */
    public static ObservableList<Part> getAllFilteredParts() {
        return filteredParts;
    }

    /**
     *
     * @return allFilteredAvailableParts list
     */
    public static ObservableList<Part> getAllFilteredAvailableParts() {
        return filteredAvailableParts;
    }

    /**
     *
     * @return allProducts list
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    /**
     *
     * @return allFilteredProducts list
     */
    public static ObservableList<Product> getAllFilteredProducts() {
        return filteredProducts;
    }

    /**
     *
     * @param newPart add part to allParts list
     */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /**
     *
     * @param newProduct add product to allProducts list
     */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);

    }


}
