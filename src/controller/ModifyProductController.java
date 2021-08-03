package controller;

import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Part;
import model.Product;
import static controller.AddProductController.id;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
public class ModifyProductController implements Initializable  {
    Product getSelectedProductName;
    ObservableList<Part> associatedPartsList;
    @FXML
    private TextField idModifyProductTxt;

    @FXML
    private TextField invModifyProductTxt;

    @FXML
    private TextField nameModifyProductTxt;

    @FXML
    private TextField priceModifyProductTxt;

    @FXML
    private TextField maxModifyProductTxt;

    @FXML
    private TextField minModifyProductTxt;

    @FXML
    private Label idModifyProductLabel;

    @FXML
    private Label nameModifyProuctLabel;

    @FXML
    private Label invModifyProductlabel;

    @FXML
    private Label priceModifyProductLabel;

    @FXML
    private Label maxModifyProductLabel;

    @FXML
    private Label minModifyPorductLbale;

    @FXML
    private Button addModifyProductBtn;

    @FXML
    private Button removeAssociatedPartModifyProductBtn;

    @FXML
    private Button cancelModifyProductBtn;

    @FXML
    private Button saveModifyProductBtn;

    @FXML
    private TextField availableSearchPartsTxt;
    @FXML
    private TableView<Part> availablePartsTableView;

    @FXML
    private TableColumn<Part, Integer> availablePartIDColumn;

    @FXML
    private TableColumn<Part, String>  availablePartsNameColumn;

    @FXML
    private TableColumn<Part, Integer> availablePartsInvColumn;

    @FXML
    private TableColumn<Part, Double> availablePartsPriceColumn;

    @FXML
    private TableView<Part> selectedPartsTableView;

    @FXML
    private TableColumn<Part, Integer> selectedPartIDColumn;

    @FXML
    private TableColumn<Part, String> selectedPartNameColumn;

    @FXML
    private TableColumn<Part, Integer> selectedPartInvColumn;

    @FXML
    private TableColumn<Part, Double> selectedPartPriceColumn;


//Cancel button switching back to main form
    @FXML
    void onActionDislayMainMenuModifyProduct(ActionEvent event) throws IOException {
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Object scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setScene(new Scene((Parent) scene));
        stage.show();
    }

//Save modify product button 
    @FXML
    void onActionSaveModifyProduct(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/MainForm.fxml"));
        loader.load();
        MainFormController mainFormController = loader.getController();
//replacing old row to modify row
        mainFormController.deleteSelectedProductRowItem(getSelectedProductName);
        if (validateParts()){
        int id = Integer.parseInt(idModifyProductTxt.getText());
        String partName = nameModifyProductTxt.getText();
        int inventoryLevel = Integer.parseInt(invModifyProductTxt.getText());
        double price = Double.parseDouble(priceModifyProductTxt.getText());
        int max = Integer.parseInt(maxModifyProductTxt.getText());
        int min = Integer.parseInt(minModifyProductTxt.getText());
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
        



    /**
     *importing product information to the text boxes
     * @param product the product selected on the product table view
     */
    
    public void importProductInfo(Product product){
    getSelectedProductName = product;
    idModifyProductTxt.setText(String.valueOf(product.getId()));
    nameModifyProductTxt.setText(product.getName());
    invModifyProductTxt.setText(String.valueOf(product.getStock()));
    priceModifyProductTxt.setText(String.valueOf(product.getPrice()));
    maxModifyProductTxt.setText(String.valueOf(product.getMax()));
    minModifyProductTxt.setText(String.valueOf(product.getMin()));
    selectedPartsTableView.setItems(product.getAssociatedParts());
//assign associated part list
    associatedPartsList = product.getAssociatedParts();
    } 

//on form load up
    @Override
    public void initialize(URL location, ResourceBundle resources) {
//available parts table
        availablePartsTableView.setItems(Inventory.getAllParts());
        availablePartIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        availablePartsNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        availablePartsInvColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        availablePartsPriceColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));  
//selected product table       
        selectedPartIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        selectedPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        selectedPartInvColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        selectedPartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));

        

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
           alert.setContentText("Please select the item from parts table");
           alert.showAndWait().ifPresent(rs -> {
                 if (rs == ButtonType.OK) {
                       System.out.println("Pressed OK.");
    }});}    
        
  
    }


//add button   
    @FXML
    void onActionAddBtn(ActionEvent event) {
    if(!associatedPartsList.contains(availablePartsTableView.getSelectionModel().getSelectedItem())){
    associatedPartsList.add(availablePartsTableView.getSelectionModel().getSelectedItem());
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
        if (availableSearchPartsTxt.getText().isEmpty()){
            availablePartsTableView.setItems(Inventory.getAllParts());
        }
        else{
            availablePartsTableView.setItems(filter(availableSearchPartsTxt.getText()));
        }
    }
//input validation
/**
* RUBRIC G (PART 1):
*  <P>A logical error that I corrected in the code was having correct input validation with textbox inputs
*  for the part and product forms. The runtime error that I received stated that the variable's data type failing to be assigned incompatible data values. The solution I found was to create a boolean "validateParts" method and inside, use try and catch statements to see if all of the strings from the textboxes would parse correctly into their required data types.<P>
* @return false if data validation fails and true if data validation is successful
*
*/
     public boolean validateParts() {
        try{
        String partName =  nameModifyProductTxt.getText();
        if(partName.trim().isEmpty()){
            System.out.println("Product Name is empty");
            return false;
        }else{
            System.out.println("Product Name is not empty");
            System.out.println(partName);
             
        }
        }
         catch(NumberFormatException e){
            System.out.println("Error " + e + " is not a word");    
        }
        try{  
        int inventoryLevel = Integer.parseInt(invModifyProductTxt.getText());
        }
         catch(NumberFormatException e){
            System.out.println("Error " + e + " is not a number");
            return false;
         }
         try{
        double price = Double.parseDouble(priceModifyProductTxt.getText());
        }
        catch(NumberFormatException e){
            System.out.println("Error " + e + " is not a number");
            return false;
        } 
         try{
        int max = Integer.parseInt(maxModifyProductTxt.getText());
        }
        catch(NumberFormatException e){
            System.out.println("Error " + e + " is not a number");
            return false;
        } 
        try{
        int min = Integer.parseInt(minModifyProductTxt.getText());
        }
        catch(NumberFormatException e){
            System.out.println("Error " + e + " is not a number");
            return false;
        } 
                   // Min should be less than Max; and Inv should be between those two values.
         try{
             int max = Integer.parseInt(maxModifyProductTxt.getText());
             int min = Integer.parseInt(minModifyProductTxt.getText());
             int inventoryLevel = Integer.parseInt(invModifyProductTxt.getText());
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
    

