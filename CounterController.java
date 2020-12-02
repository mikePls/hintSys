/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hintsystem;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class CounterController implements Initializable {

    @FXML
    private Label counterLabel;
    @FXML
    private Label hintLabel;
    @FXML
    private AnchorPane Counter;
    @FXML
    private ImageView imageHint;
    
    private double offsetX = 0;
    private double offsetY = 0;
    @FXML
    private VBox imageBox;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        
    }    
    
    public void setTime(String s)
    {counterLabel.setText(s);}
    
    
    public void setHint(String s, boolean b){
    hintLabel.setVisible(b);
    hintLabel.setText(s);
    }

    public void setPhoto(File file, boolean b) throws FileNotFoundException
    {FileInputStream input = new FileInputStream(file.getAbsoluteFile());
    Image image = new Image(input);
    imageHint.setImage(image);
    imageHint.setVisible(b);}
    
    public void setBackground(Paint fill)
    {BackgroundFill backgroundFill = 
                    new BackgroundFill(fill, 
                            CornerRadii.EMPTY, 
                            Insets.EMPTY);
                Background background = new Background(backgroundFill);
                counterLabel.setBackground(background);
    }
    
    public void setTextColour(Paint fill)
    {counterLabel.setTextFill(fill);}

    
    
    @FXML
    private void clickHandle(MouseEvent event) {
        Stage stage = (Stage) imageBox.getScene().getWindow();
        offsetX = stage.getX() - event.getScreenX();
        offsetY = stage.getY() - event.getScreenY();
    }
    
    @FXML
    private void moveHandle(MouseEvent event) {
    Stage stage = (Stage) imageBox.getScene().getWindow();
    stage.setX(event.getScreenX() + offsetX);
    stage.setY(event.getScreenY() + offsetY);
    }
    
    
    
    
    
    }

    
