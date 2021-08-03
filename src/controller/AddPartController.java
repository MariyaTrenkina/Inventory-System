package controller;

import model.InHouse;
import model.Inventory;
import model.Outsourced;
import java.io.IOException;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Mariya.Trenkina
 */
public class AddPartController {

    @FXML
    private TextField partIdTxt;

    @FXML
    private TextField nameTxt;

    @FXML
    private TextField invTxt;

    @FXML
    private TextField priceCostTxt;

    @FXML
    private TextField maxTxt;

    @FXML
    private TextField machineIdTxt;

    @FXML
    private TextField minTxt;

    @FXML
    private Button saveBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private RadioButton outsourcedRadioBtn;

    @FXML
    private RadioButton InHouseRadioBtn;

    @FXML
    private Label idLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label invLabel;

    @FXML
    private Label priceCostLabel;

    @FXML
    private Label maxLabel;

    @FXML
    private Label machineIdAddPartLabel;

    @FXML
    private Label minLabel;
    
    static int id = 0;


//Cancel button switching back to main form
    @FXML
    void onActionDisplayMainMenu(ActionEvent event) throws IOException {
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Object scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setScene(new Scene((Parent) scene));
        stage.show();    
    }

//Save part button
    @FXML
    void onActionSavePart(ActionEvent event) throws IOException {
        id++;
       if (validateParts()){
           String partName = nameTxt.getText();
           int inventoryLevel = Integer.parseInt(invTxt.getText());
           double price = Double.parseDouble(priceCostTxt.getText());
           int max = Integer.parseInt(maxTxt.getText());
           int min = Integer.parseInt(minTxt.getText());
           int machineID;
           String companyName;
       if (InHouseRadioBtn.isSelected()){
        machineID = Integer.parseInt(machineIdTxt.getText());
         Inventory.addPart(new InHouse(id, partName, price, inventoryLevel, min, max, machineID));
         System.out.println("Machine ID is " + machineID);
         System.out.println(Inventory.getAllParts());
        }
        else{
        companyName = machineIdTxt.getText();
          Inventory.addPart(new Outsourced(id, partName, price, inventoryLevel, min, max, companyName));
        System.out.println(Inventory.getAllParts());
        }
        
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Object scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setScene(new Scene((Parent) scene));
        stage.show();
       }
       else{
           Alert alert = new Alert(AlertType.INFORMATION);
           alert.setTitle("Input Validation Error");
           alert.setHeaderText("Part Not Saved");
           alert.setContentText("Please type the correct data in each field.  Min should be less than Max and Inventory should be between those two values.");
           alert.showAndWait().ifPresent(rs -> {
                 if (rs == ButtonType.OK) {
                       System.out.println("Pressed OK.");
    }});}}
    
//switching label text based on radio button selected 
    @FXML
    void partTypeCheck(ActionEvent event) throws IOException {
        
        if (InHouseRadioBtn.isSelected()){
            
        machineIdAddPartLabel.setText("Machine ID");
        }
        else {

        machineIdAddPartLabel.setText("Company Name");
             }
        }
  
//input validation for add part form  
/**
* RUBRIC G (PART 1):
*  <P>A logical error that I corrected in the code was having correct input validation with textbox inputs
*  for the part and product forms. The runtime error that I received stated that the variable's data type failing to be assigned incompatible data values. The solution I found was to create a boolean "validateParts" method and inside, use try and catch statements to see if all of the strings from the textboxes would parse correctly into their required data types.<P>
* @return false if data validation fails and true if data validation is successful
*
*/
    public boolean validateParts() {
//input validation for add part Name text box
        try{
        String partName = nameTxt.getText();
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
//input validation for add part inventory text box        
         try{
        int inventoryLevel = Integer.parseInt(invTxt.getText());
         }
         catch(NumberFormatException e){
            System.out.println("Error " + e + " is not a number");
            return false;
         }
//input validation for add part price text box
         try{
        double price = Double.parseDouble(priceCostTxt.getText());
        }
        catch(NumberFormatException e){
            System.out.println("Error " + e + " is not a number");
            return false;
        }  
//input validation for add part max text box
         try{
        int max = Integer.parseInt(maxTxt.getText());
        }
        catch(NumberFormatException e){
            System.out.println("Error " + e + " is not a number");
            return false;
        }  
//input validation for add part min text box
         try{
        int min = Integer.parseInt(minTxt.getText());
        }
        catch(NumberFormatException e){
            System.out.println("Error " + e + " is not a number");
            return false;
        }   
//input validation for add part machine id text box
         try{
             if (InHouseRadioBtn.isSelected()){
            
        int machineID = Integer.parseInt(machineIdTxt.getText());
        
        }}
        catch(NumberFormatException e){
            System.out.println("Error " + e + " is not a number");
        return false;            
        }
//input validation for add part company name text box
        try{
            if (outsourcedRadioBtn.isSelected()){
        String companyName = machineIdTxt.getText();
//if outsourced radio button selected changes to company name
        if(companyName.trim().isEmpty()){
            return false;
        }
            }
        }
        catch(NumberFormatException e){
            System.out.println("Error " + e + " is not a word");
            return false;   
        }
    // Min should be less than Max; and Inv should be between those two values.
         try{
             int max = Integer.parseInt(maxTxt.getText());
             int min = Integer.parseInt(minTxt.getText());
             int inventoryLevel = Integer.parseInt(invTxt.getText());
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