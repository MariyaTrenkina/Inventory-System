package view;

import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Product;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 
 * RUBRIC G (PART 2):
 *  <P>A compatible feature suitable to the application that would extend functionality would be the ability to show all of the attributes of parts/products on the mainform's tables. The feature would allow the ability to turn off and on showing all of the columns, not just the simplified columns (ID, Name, Inventory, and Price).<P>
 *@author Mariya.Trenkina
 */

public class Main extends Application {

//loads main form
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        primaryStage.setTitle("Inventory Management System");
        primaryStage.setScene(new Scene(root, 1000, 500));
        primaryStage.show();
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
