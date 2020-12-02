/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hintsystem;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author User
 */
public class counterLoader /*implements Initializable*/ {
    Stage stage = new Stage();
    CounterController counterController;
    counterLoader()
    {System.out.println("ok");}
    
    public void start() {
        
        
        try {
            FXMLLoader customLoader = new FXMLLoader(getClass().getResource("Counter.fxml"));
            Parent root = customLoader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("TimeRun hint System");
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setResizable(true);
            stage.setAlwaysOnTop(true);
            stage.show();  
            counterController = customLoader.getController();
        } catch (IOException ex) {
            Logger.getLogger(counterLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public CounterController getController()
    {return counterController;}
    
    public Stage getStage()
    {return stage;}
    
}
