
package hintsystem;

import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;


/**
 *
 * @author Mike
 */
public class HintSystem extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("TimeRun HintSystem beta");
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(true);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
          @Override
          public void handle(WindowEvent we) {
              Platform.exit();
                                                }
                              });  
        Path image = Paths.get("src\\hintsystem\\icons\\miniLogom.png");
        stage.getIcons().add(new Image(image.toUri().toString()));
        root.setStyle("-fx-background-color: BLACK");
        stage.show(); 
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }
    
    
}
