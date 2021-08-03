package classes;
import model.Part;
import model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private static ObservableList<Part> filteredParts = FXCollections.observableArrayList();
    private static ObservableList<Part> filteredAvailableParts = FXCollections.observableArrayList();
    private static ObservableList<Product> filteredProducts = FXCollections.observableArrayList();
    
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }
    public static ObservableList<Part> getAllFilteredParts() {
        return filteredParts;
    }
    public static ObservableList<Part> getAllFilteredAvailableParts() {
        return filteredAvailableParts;
    }
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
    public static ObservableList<Product> getAllFilteredProducts() {
        return filteredProducts;
    }
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);

    }

//    public Part lookupPart(int partId) {
//
//
//    }

//    public Product lookupProduct(int product) {
//
//    }
//
//    public ObservableList<Part> lookupPart(String partName) {
//
//    }
//
//    public ObservableList<Product> lookupProduct(String productName){
//
//    }

    public void updatePart(int index, Part selectedPart){

    }

    public void updateProduct(int index, Product newProduct){

    }

//    public boolean deletePart(Part selectedPart){
//
//    }
//
//    public boolean deleteProduct(Product selectedProduct){
//
//    }

}
