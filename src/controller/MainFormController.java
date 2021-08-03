package controller;

import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Part;
import model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 *
 * @author Mariya.Trenkina
 */
public class MainFormController implements Initializable{
    
    Stage stage;
    Parent scene;
    Part selectedItem;
    Product selectedProduct;
    String selectedItemString;
    
    @FXML
    private Button deleteIntoryPartsBtn;

    @FXML
    private TableView<Part> partsTableView;
    
    @FXML
    private TableColumn<Part, Integer> partIdColumn;

    @FXML
    private TableColumn<Part, String> partNameColumn;

    @FXML
    private TableColumn<Part, Integer> partInventoryLevelColumn;

    @FXML
    private TableColumn<Part, Double> partPriceColumn;

    @FXML
    private Button modifyInventoryPartsBtn;

    @FXML
    private Button addInventroyPartsBtn;

    @FXML
    private Label partsInventoryLabel;

    @FXML
    private TextField searchPartsTxt;

    @FXML
    private Button deleteInventoryProductsBtn;

    @FXML
    private TableView<Product> productsTableView;
    
     @FXML
    private TableColumn<Product, Integer> productIdColumn;

    @FXML
    private TableColumn<Product, String> productNameColumn;

    @FXML
    private TableColumn<Product, Integer> productInventoryLevelColumn;

    @FXML
    private TableColumn<Product, Double> productPriceColumn;


    @FXML
    private Button modifyInventoryProductsBtn;

    @FXML
    private Button addInventoryProductBtn;

    @FXML
    private Label productsInventoryLabel;

    @FXML
    private TextField searchProductsTxt;

    @FXML
    private Button exitInventoryBtn;

    
//opens add part form 
    @FXML
    private void onActionAddParts(javafx.event.ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddPart.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
//opens modify part form    

    /**
     *
     * @param event action when clicking parts "modify" button
     * @throws IOException
     */
    @FXML
    public void onActionModifyParts(javafx.event.ActionEvent event) throws IOException {
//selectedItem gets the selected item from the parts table view       
        selectedItem = partsTableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null){             
//communication from main to modfy form
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ModifyPart.fxml"));
        loader.load();
        ModifyPartController modifyPartController = loader.getController();
        modifyPartController.importPartInfo(selectedItem);
        
//switches from main form to modify part form
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
//pop message for modify part if item is not selected
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Error Message");
           alert.setHeaderText("Item not selected");
           alert.setContentText("Please select an item from parts table");
           alert.showAndWait().ifPresent(rs -> {
                 if (rs == ButtonType.OK) {
                       System.out.println("Pressed OK.");
    }});}}

//opens add products form
    @FXML
    private void onActionAddProducts(javafx.event.ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddProduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
   
    }
//opens modify products form  
    @FXML
    private void onActionModifyProducts(javafx.event.ActionEvent event) throws IOException {
//selectedProduct gets the selected item from the product table view 
        selectedProduct = productsTableView.getSelectionModel().getSelectedItem();      
        if (selectedProduct != null){             
//communication from main to modfy form
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ModifyProduct.fxml"));
        loader.load();
        ModifyProductController modifyProductController = loader.getController();
        modifyProductController.importProductInfo(selectedProduct);
        
//switches from main form to modify product form
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
//pop message for modify product if item is not selected
        } else {
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Error Message");
           alert.setHeaderText("Item not selected");
           alert.setContentText("Please select an item from product table");
           alert.showAndWait().ifPresent(rs -> {
                 if (rs == ButtonType.OK) {
                       System.out.println("Pressed OK.");
    }});}
    }
    
//exit button   
    @FXML
    private void onActionExit(javafx.event.ActionEvent event) {       
        System.exit(0);
    }

//on form load up
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        partsTableView.setItems(Inventory.getAllParts());
        partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        partInventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<>("stock")); 
        
        productsTableView.setItems(Inventory.getAllProducts());
        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("stock")); 
    }
    
//delete part button    
      @FXML
    void onActionDeletePartRowBtn(ActionEvent event) {
        if(partsTableView.getSelectionModel().getSelectedItem() != null){
        
        Alert alert = new Alert(AlertType.CONFIRMATION, "Delete " + partsTableView.getSelectionModel().getSelectedItem().getName() + " ?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
        partsTableView.getItems().removeAll(partsTableView.getSelectionModel().getSelectedItem());
       }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Error Message");
           alert.setHeaderText("Item not selected");
           alert.setContentText("Please select an item from parts table");
           alert.showAndWait().ifPresent(rs -> {
                 if (rs == ButtonType.OK) {
                       System.out.println("Pressed OK.");
    }});} 
    }

//delete products button
    @FXML
    void onActionDeleteProductsRowBtn(ActionEvent event) {
        ObservableList<Part> selectedProductAssociatedList = productsTableView.getSelectionModel().getSelectedItem().getAssociatedParts();
        if(productsTableView.getSelectionModel().getSelectedItem() != null){
        if(selectedProductAssociatedList != null && selectedProductAssociatedList.isEmpty()){
        
        Alert alert = new Alert(AlertType.CONFIRMATION, "Delete " + productsTableView.getSelectionModel().getSelectedItem().getName() + " ?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
        productsTableView.getItems().removeAll(productsTableView.getSelectionModel().getSelectedItem());
       }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Error Message");
           alert.setHeaderText("Product has parts associated with it.");
           alert.setContentText("Product has parts associated with it. Please remove all associated parts before deletion.");
           alert.showAndWait().ifPresent(rs -> {
                 if (rs == ButtonType.OK) {
                       System.out.println("Pressed OK.");
                 
    }});}
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Error Message");
           alert.setHeaderText("Item not selected");
           alert.setContentText("Please select an item from products table");
           alert.showAndWait().ifPresent(rs -> {
                 if (rs == ButtonType.OK) {
                       System.out.println("Pressed OK.");
                 
    }});}
          

        
        }
//delete selected row in parts table view

    /**
     *
     * @param part the selected part from parts table
     */
    public void deleteSelectedPartRowItem(Part part){
    partsTableView.getItems().removeAll(part);
    }
    //delete selected row in parts table view

    /**
     *
     * @param product the selected product from the parts table
     */
    public void deleteSelectedProductRowItem(Product product){
    productsTableView.getItems().removeAll(product);
    }

//create filter for part search    

    /**
     *
     * @param keyword the characters typed in the search box
     * @return the AllFilteredParts list
     */
    public ObservableList<Part> filterParts (String keyword)
    {       
      if(!(Inventory.getAllFilteredParts().isEmpty()))
      {
          Inventory.getAllFilteredParts().clear();
      }
//filter by part ID or name 
      for( Part part : Inventory.getAllParts())
      {
          String partID =  Integer.toString(part.getId()) ;
       
      if(part.getName().contains(keyword) || partID.contains(keyword)){
          Inventory.getAllFilteredParts().add(part);
      }            }
      return Inventory.getAllFilteredParts();
      }  
    
//Rubric C.1. Search part method      
    @FXML
    void onActionPartsSearchKeyType(KeyEvent event) {
        
        if (searchPartsTxt.getText().isEmpty()){
            partsTableView.setItems(Inventory.getAllParts());
        }
        else{
            partsTableView.setItems(filterParts(searchPartsTxt.getText()));
        }
    }

//Search product method   
    @FXML
    void onActionProductsSearchKeyType(KeyEvent event) {
         if (searchProductsTxt.getText().isEmpty()){
            productsTableView.setItems(Inventory.getAllProducts());
        }
        else{
            productsTableView.setItems(filterProducts(searchProductsTxt.getText()));
        }
    }
//create filter for product search    

    /**
     *
     * @param keyword the characters typed in the search box
     * @return the AllFilteredProducts list
     */
    public ObservableList<Product> filterProducts (String keyword)
    {       
      if(!(Inventory.getAllFilteredProducts().isEmpty()))
      {
          Inventory.getAllFilteredProducts().clear();
      }
//filter by product ID or name 
      for( Product product : Inventory.getAllProducts())
      {
          String productID =  Integer.toString(product.getId()) ;
       
      if(product.getName().contains(keyword) || productID.contains(keyword)){
          Inventory.getAllFilteredProducts().add(product);
      }            }
      return Inventory.getAllFilteredProducts();
      } 

}
