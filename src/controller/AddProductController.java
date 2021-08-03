package controller;

import model.InHouse;
import model.Product;
import model.Inventory;
import model.Outsourced;
import model.Part;
import static controller.AddPartController.id;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.Initializable;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
public class AddProductController implements Initializable  {
    ObservableList<Part> associatedPartsList;
    
    @FXML
    private TableView<Part> AvailablePartsTableView;

    @FXML
    private TableColumn<Part, Integer> availablePartIdColumn;

    @FXML
    private TableColumn<Part, String> availablePartNameColumn;

    @FXML
    private TableColumn<Part, Integer> availableInventoryLevelColumn;

    @FXML
    private TableColumn<Part, Double> availablePriceColumn;

    @FXML
    private TableView<Part> selectedPartsTableView;

    @FXML
    private TableColumn<Part, Integer> selectedPartIdColumn;

    @FXML
    private TableColumn<Part, String> selectedPartNameColumn;

    @FXML
    private TableColumn<Part, Integer> selectedInventoryLevelColumn;

    @FXML
    private TableColumn<Part, Double> selectedPriceColumn;

    @FXML
    private TextField productIdTxt;

    @FXML
    private TextField invProductTxt;

    @FXML
    private TextField nameProductTxt;

    @FXML
    private TextField priceProductTxt;

    @FXML
    private TextField maxProductTxt;

    @FXML
    private TextField minProductTxt;

    @FXML
    private Label idAddProductLabel;

    @FXML
    private Label nameAddProductLabel;

    @FXML
    private Label invAddProductLabel;

    @FXML
    private Label priceAddProductLbael;

    @FXML
    private Label maxAddProductLabel;

    @FXML
    private Label minLabel;

    @FXML
    private Button addProductBtn;

    @FXML
    private Button cancelProductBtn;

    @FXML
    private Button saveProductBtn;

    @FXML
    private TextField searchAddProductTxt;

//product id variable
    static int id = 0;
 
//on form load up
        @Override
    public void initialize(URL location, ResourceBundle resources) {
        associatedPartsList = FXCollections.observableArrayList();
        //available parts table
        AvailablePartsTableView.setItems(Inventory.getAllParts());
        availablePartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        availablePartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        availableInventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        availablePriceColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        
//selected parts table
        
        selectedPartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        selectedPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        selectedInventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        selectedPriceColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
    }
    
//Cancel button switching back to main form
    @FXML
    void onActionAddProductDisplayMainMenu(ActionEvent event) throws IOException {
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Object scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setScene(new Scene((Parent) scene));
        stage.show();
    
    }

//save button
    @FXML
    void onActionSaveAddProduct(ActionEvent event) throws IOException {
        if (validateParts()){
//auto increment id variable     
        id++;
        String partName = nameProductTxt.getText();
        int inventoryLevel = Integer.parseInt(invProductTxt.getText());
        double price = Double.parseDouble(priceProductTxt.getText());
        int max = Integer.parseInt(maxProductTxt.getText());
        int min = Integer.parseInt(minProductTxt.getText());
//create product with variables from text boxes           
        Inventory.addProduct(new Product(id, partName, price, inventoryLevel, min, max, associatedPartsList));
//takes you back to the main form
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Object scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setScene(new Scene((Parent) scene));
        stage.show();
    }
       else{
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Input Validation Error");
           alert.setHeaderText("Product Not Saved");
           alert.setContentText("Please type the correct data in each field.  Min should be less than Max and Inventory should be between those two values.");
           alert.showAndWait().ifPresent(rs -> {
                 if (rs == ButtonType.OK) {
                       System.out.println("Pressed OK.");
    }});
    }
    }
    
//add button
    @FXML
    void onActionAddPart(ActionEvent event) {
    if(!associatedPartsList.contains(AvailablePartsTableView.getSelectionModel().getSelectedItem())){
    associatedPartsList.add(AvailablePartsTableView.getSelectionModel().getSelectedItem());
    selectedPartsTableView.setItems(associatedPartsList);
    }else{
    System.out.println("Duplicate!");
    }
    
    }
//create filter for part search    

    /**
     *
     * @param keyword the characters typed in the search box
     * @return the AllFilteredAvailableParts list
     */
    public ObservableList<Part> filter (String keyword)
    {       
      if(!(Inventory.getAllFilteredAvailableParts().isEmpty()))
      {
          Inventory.getAllFilteredAvailableParts().clear();
      }
//filter by part ID or name 
      for( Part part : Inventory.getAllParts())
      {
          String partID =  Integer.toString(part.getId()) ;
       
      if(part.getName().contains(keyword) || partID.contains(keyword)){
          Inventory.getAllFilteredAvailableParts().add(part);
      }            }
      return Inventory.getAllFilteredAvailableParts();
      }  
    
//search button box
    @FXML
    void onActionKeyTyped(KeyEvent event) {
        if (searchAddProductTxt.getText().isEmpty()){
            AvailablePartsTableView.setItems(Inventory.getAllParts());
        }
        else{
            AvailablePartsTableView.setItems(filter(searchAddProductTxt.getText()));
        }
    }

//remove associated part button
    @FXML
    void onActionSelectedRemoveAssPart(ActionEvent event) {
        if(selectedPartsTableView.getSelectionModel().getSelectedItem() != null){
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Remove " + selectedPartsTableView.getSelectionModel().getSelectedItem().getName() + " from associated parts list?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
        selectedPartsTableView.getItems().removeAll(selectedPartsTableView.getSelectionModel().getSelectedItem());
       }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Error Message");
           alert.setHeaderText("Item not selected");
           alert.setContentText("Please selet the item from parts table");
           alert.showAndWait().ifPresent(rs -> {
                 if (rs == ButtonType.OK) {
                       System.out.println("Pressed OK.");
    }});}
        

    
    }

/**
* RUBRIC G (PART 1):
*  <P>A logical error that I corrected in the code was having correct input validation with textbox inputs
*  for the part and product forms. The runtime error that I received stated that the variable's data type failing to be assigned incompatible data values. The solution I found was to create a boolean "validateParts" method and inside, use try and catch statements to see if all of the strings from the textboxes would parse correctly into their required data types.<P>
* @return false if data validation fails and true if data validation is successful
*
*/
    public boolean validateParts() {
        try{
        String partName = nameProductTxt.getText();
        if(partName.trim().isEmpty()){
            System.out.println("Part Name is empty");
            return false;
        }else{
            System.out.println("Part Name is not empty");
            System.out.println(partName);
             
        }
        }
         catch(NumberFormatException e){
            System.out.println("Error " + e + " is not a word");    
        }
        try{  
        int inventoryLevel = Integer.parseInt(invProductTxt.getText());
        }
         catch(NumberFormatException e){
            System.out.println("Error " + e + " is not a number");
            return false;
         }
         try{
        double price = Double.parseDouble(priceProductTxt.getText());
        }
        catch(NumberFormatException e){
            System.out.println("Error " + e + " is not a number");
            return false;
        } 
         try{
        int max = Integer.parseInt(maxProductTxt.getText());
        }
        catch(NumberFormatException e){
            System.out.println("Error " + e + " is not a number");
            return false;
        } 
        try{
        int min = Integer.parseInt(minProductTxt.getText());
        }
        catch(NumberFormatException e){
            System.out.println("Error " + e + " is not a number");
            return false;
        }
                   // Min should be less than Max; and Inv should be between those two values.
         try{
             int max = Integer.parseInt(maxProductTxt.getText());
             int min = Integer.parseInt(minProductTxt.getText());
             int inventoryLevel = Integer.parseInt(invProductTxt.getText());
        if((max < min) || (inventoryLevel < min) || (inventoryLevel > max)){
        return false;
        }
        }
        catch(NumberFormatException e){
            System.out.println("Error " + e + " is not a number");
            return false;
        }
        
        return true;
    }
}
