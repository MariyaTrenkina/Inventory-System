package controller;

import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Part;
import static controller.AddPartController.id;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 *
 * @author Mariya.Trenkina
 */
public class ModifyPartController implements Initializable {
    Part getSelectedPartName;
     @FXML
    private TextField idModifyPartTxt;

    @FXML
    private TextField nameModifyPartTxt;

    @FXML
    private TextField invModifyPartTxt;

    @FXML
    private TextField priceModifyPartTxt;

    @FXML
    private TextField maxModifyPartTxt;

    @FXML
    private TextField machineIdModifyPartTxt;

    @FXML
    private TextField minModifyPartTxt;

    @FXML
    private Button saveModifyPartBtn;

    @FXML
    private Button cancelModifyPartBtn;

    @FXML
    private RadioButton outsourcedModifyPartBtn;

    @FXML
    private ToggleGroup sourceModifyPartTG;

    @FXML
    private RadioButton inhouseModifyPartBtn;

    @FXML
    private Label idModifyPartLabel;

    @FXML
    private Label nameModifyPartLabel;

    @FXML
    private Label invModifyPartLabel;

    @FXML
    private Label priceModifyPartLabel;

    @FXML
    private Label maxModifyPartLabel;

    @FXML
    private Label machineIdModifyPartLabel;

    @FXML
    private Label minModifyPartLabel;

//Cancel button switching back to main form
    @FXML
    void onActionModifyPartDisplayMainMenu(ActionEvent event) throws IOException {
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Object scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setScene(new Scene((Parent) scene));
        stage.show();
    }
    
//Save modify part button    
    @FXML
    void onActionSaveModifyPart(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/MainForm.fxml"));
        loader.load();
        MainFormController mainFormController = loader.getController();
//replacing old row to modify row
        mainFormController.deleteSelectedPartRowItem(getSelectedPartName);
//creating new object with modified information from text boxes    
         if (validateParts()){
           int id = Integer.parseInt(idModifyPartTxt.getText());
           String partName = nameModifyPartTxt.getText();
           int inventoryLevel = Integer.parseInt(invModifyPartTxt.getText());
           double price = Double.parseDouble(priceModifyPartTxt.getText());
           int max = Integer.parseInt(maxModifyPartTxt.getText());
           int min = Integer.parseInt(minModifyPartTxt.getText());
           int machineID;
           String companyName;
       if (inhouseModifyPartBtn.isSelected()){
        machineID = Integer.parseInt(machineIdModifyPartTxt.getText());
//adding object to part list if inhouse radio button selected
         Inventory.addPart(new InHouse(id, partName, price, inventoryLevel, min, max, machineID));
         System.out.println("Machine ID is " + machineID);
         System.out.println(Inventory.getAllParts());
        }
        else{
        companyName = machineIdModifyPartTxt.getText();
//adding object to part list if outsourced radio button selected
          Inventory.addPart(new Outsourced(id, partName, price, inventoryLevel, min, max, companyName));
        System.out.println(Inventory.getAllParts());
        }
//brings you back to main menu after you hit save button        
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Object scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setScene(new Scene((Parent) scene));
        stage.show();
       }
//pop message for incorrect data in text boxes
       else{
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Input Validation Error");
           alert.setHeaderText("Part Not Saved");
           alert.setContentText("Please type the correct data in each field.  Min should be less than Max and Inventory should be between those two values.");
           alert.showAndWait().ifPresent(rs -> {
                 if (rs == ButtonType.OK) {
                       System.out.println("Pressed OK.");
    }});
    } 
    }

//input validation for modify part form    
/**
* RUBRIC G (PART 1):
*  <P>A logical error that I corrected in the code was having correct input validation with textbox inputs
*  for the part and product forms. The runtime error that I received stated that the variable's data type failing to be assigned incompatible data values. The solution I found was to create a boolean "validateParts" method and inside, use try and catch statements to see if all of the strings from the textboxes would parse correctly into their required data types.<P>
* @return false if data validation fails and true if data validation is successful
*
*/
        public boolean validateParts() {
//input validation for modify part Name text box            
        try{
        String partName = nameModifyPartTxt.getText();
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

//input validation for modify part inventory text box         
         try{              
        int inventoryLevel = Integer.parseInt(invModifyPartTxt.getText());
         }
         catch(NumberFormatException e){
            System.out.println("Error " + e + " is not a number");
            return false;
         }
//input validation for modify part price text box 
         try{
        double price = Double.parseDouble(priceModifyPartTxt.getText());
        }
        catch(NumberFormatException e){
            System.out.println("Error " + e + " is not a number");
            return false;
        } 
//input validation for modify part max text box          
         try{
        int max = Integer.parseInt(maxModifyPartTxt.getText());
        }
        catch(NumberFormatException e){
            System.out.println("Error " + e + " is not a number");
            return false;
        } 
//input validation for modify part min text box 
         try{
        int min = Integer.parseInt(minModifyPartTxt.getText());
        }
        catch(NumberFormatException e){
            System.out.println("Error " + e + " is not a number");
            return false;
        } 
//input validation for modify part machine id text box 
         try{
             if (inhouseModifyPartBtn.isSelected()){   
        int machineID = Integer.parseInt(machineIdModifyPartTxt.getText());
        
        
        }
         }
        catch(NumberFormatException e){
            System.out.println("Error " + e + " is not a number"); 
            return false;
        }
//input validation for modify part company name text box 
        try{
            if (outsourcedModifyPartBtn.isSelected()){
        String companyName = machineIdModifyPartTxt.getText();
        if(companyName.trim().isEmpty()){
            System.out.println("Company Name is empty");
            return false;
        }else{
            System.out.println("Company Name is not empty");
            System.out.println(companyName);
            
        }
            }
        }
        catch(NumberFormatException e){
            System.out.println("Error " + e + " is not a word");
            return false;
            
        }
           // Min should be less than Max; and Inv should be between those two values.
         try{
             int max = Integer.parseInt(maxModifyPartTxt.getText());
             int min = Integer.parseInt(minModifyPartTxt.getText());
             int inventoryLevel = Integer.parseInt(invModifyPartTxt.getText());
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
//switching label text based on radio button selected 
    @FXML
    void partTypeCheck(ActionEvent event) throws IOException {
        
        if (inhouseModifyPartBtn.isSelected()){
            
        machineIdModifyPartLabel.setText("Machine ID");
        }
        else {

        machineIdModifyPartLabel.setText("Company Name");
             }
        }
  
//when modify form loads up
    @Override
    public void initialize(URL location, ResourceBundle resources) {
//switching label text based on radio button on load up 
    if (inhouseModifyPartBtn.isSelected()){
            
        machineIdModifyPartLabel.setText("Machine ID");
        }
        else {

        machineIdModifyPartLabel.setText("Company Name");
             }
 //importing part information to the text boxes       
    }

    /**
     *
     * @param part the part selected from the parts table view
     */
    public void importPartInfo(Part part){
    getSelectedPartName = part;
    idModifyPartTxt.setText(String.valueOf(part.getId()));
    nameModifyPartTxt.setText(part.getName());
    invModifyPartTxt.setText(String.valueOf(part.getStock()));
    priceModifyPartTxt.setText(String.valueOf(part.getPrice()));
    maxModifyPartTxt.setText(String.valueOf(part.getMax()));
    minModifyPartTxt.setText(String.valueOf(part.getMin()));
    if (part instanceof InHouse){
        inhouseModifyPartBtn.setSelected(true);
        System.out.println(String.valueOf(((InHouse) part).getMachineId()));
        machineIdModifyPartTxt.setText(String.valueOf(((InHouse) part).getMachineId()));
    }
    if (part instanceof Outsourced){
        outsourcedModifyPartBtn.setSelected(true);
        machineIdModifyPartTxt.setText(((Outsourced) part).getCompanyName());
    }
    }   
}
