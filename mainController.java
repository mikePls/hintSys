/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hintsystem;


import java.io.File;
import java.nio.file.*; 
import java.io.*;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import static java.lang.Math.floor;
import static java.lang.String.format;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javafx.scene.control.Slider;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import static javafx.geometry.NodeOrientation.RIGHT_TO_LEFT;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


/**
 * FXML Controller class
 *
 * @author Mike P.
 */
public class mainController implements Initializable  {
    @FXML
    private Label timerLabel; 
    @FXML
    private TextArea shoutBox;
    @FXML
    private AnchorPane mainWin;
    @FXML
    private Button sendImage;
    @FXML
    private ComboBox<String> presetBox;
    @FXML
    private Button sendPreset;
    @FXML
    private Button sendShout;
    @FXML
    private Button startButton;
    @FXML
    private Button pauseButton;
    @FXML
    private Button plusOne;
    @FXML
    private Button minusOne;
    @FXML
    private Button plus5;
    @FXML
    private Button setCounter1;
    @FXML
    private Button addCounter2;
    @FXML
    private Button addCounter;
    @FXML
    private Button addCounter3;  
    @FXML
    private Button addCounter4;
    @FXML
    private Button setCounter2;
    @FXML
    private Button setCounter3;
    @FXML
    private Button setCounter4;
    @FXML
    private Button minus5;   
    @FXML
    private ComboBox<File> imageList;
    @FXML
    private FlowPane sfxPane;
    @FXML
    private Button themePlay;
    @FXML
    private Button themePause;
    @FXML
    private Button themeRewind;
    @FXML
    private Slider musicSlider;
    @FXML
    private Label musicLabel;
    @FXML
    private Label previewPreset;
    @FXML
    private Label timeStatus;
    @FXML
    private Label counterStatus;
    @FXML
    private Button resetButton;
    @FXML
    private HBox statusBar;
//****CONTROLLERS****
    CounterController[] newC = new CounterController[4];
    Stage[] counterStage = new Stage[4];
    boolean[] conIsActive =  new boolean[4];
    boolean hintIsActive = false;
    CheckBox[] countCheck = new CheckBox[4];
//****TIME VARIABLES****
    private static Integer STARTTIME = 4800;   
    private Timeline timeline;
    /*Integer property left for future use:
    private static IntegerProperty timeSeconds =
        new SimpleIntegerProperty(60);*/
   Integer secondsRemaining = STARTTIME % 60;
   Integer minutesRemaining = STARTTIME / 60;
//*****
   private String shoutString = null; 
//****Music Player****   
    public static File file = new File("src\\hintsystem\\musicTheme\\mainTheme.mp3");
    public static Media mainTheme = new Media(file.toURI().toString());
    public static MediaPlayer mainThemePlayer = new MediaPlayer(mainTheme);
    Duration duration = mainTheme.getDuration();
//****RingPlayer
    File ringS = new File("src\\hintsystem\\resources\\ring.mp3");
    Media ringSound = new Media(ringS.toURI().toString());
    MediaPlayer ringPlayer = new MediaPlayer(ringSound);
    //public mainController myself;
    @FXML
    private ImageView previewImage;
    @FXML
    private HBox backgroundBox;
    @FXML
    private ColorPicker timeColor;
    @FXML
    private CheckBox timeReminder;
    @FXML
    private Button ring;
    @FXML
    private Label displayingHint;
    @FXML
    private ColorPicker backgroundColour;
    @FXML
    private Slider volumeSlider;
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
      
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
    Reminder reminder = new Reminder();    
    timerLabel.setText(minutesRemaining.toString() + ":" + 
            secondsRemaining .toString() + "0");
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1),
                new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    calculate();
                    if (timeReminder.isSelected() == true)
                reminder.remind(STARTTIME);
                
                }
            }));  
    try {
            getImages();
        } 
    catch (IOException ex) {
            Logger.getLogger(mainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        setPresets();
        getSamples();
        setMusicControlls();
        setCheckBoxes();
        Path image = Paths.get("src\\hintsystem\\icons\\miniLogo.png");
        backgroundBox.setStyle("-fx-background-image: url('" + image.toUri().toString() + "'); " +
           "-fx-background-position: center center; " +
           "-fx-background-repeat: stretch;");
    
    }    
    
    

    @FXML
    private void startHandle(ActionEvent event) {
      timeline.play();
      timeStatus.setText("Running");
      timeStatus.setTextFill(Color.GREEN);
    }


    @FXML
    private void pauseHandle(ActionEvent event) {
        timeline.pause();
        timeStatus.setText("Paused");
        timeStatus.setTextFill(Color.YELLOW);
    }


    @FXML
    private void shoutHandle(ActionEvent event) {
         if (hintIsActive == false)
         {sendPreset.setDisable(true);
          sendImage.setDisable(true);
          hintIsActive = true;
          sendShout.setText("Hide");
         for (int i = 0; i < conIsActive.length; ++i)
        if (conIsActive[i] == true && countCheck[i].isSelected() == true)
        {shoutString = shoutBox.getText();
         newC[i].setHint(shoutString, true);}
         displayingHint.setVisible(true);}
         
        
        else
        {hintIsActive = false;
        sendPreset.setDisable(false);
        sendImage.setDisable(false);
        sendShout.setText("Send");
        displayingHint.setVisible(false);
        for (int i = 0; i < conIsActive.length; ++i)
        if (conIsActive[i] == true)
          newC[i].setHint("", false);
        }
        
    }


    @FXML
    private void plus1Handle(ActionEvent event) {
    STARTTIME = STARTTIME + 60;
    calculateAddSubtract();
    }

    @FXML
    private void minus1Handle(ActionEvent event) {
    STARTTIME = STARTTIME - 60;
    calculateAddSubtract();
    }

    @FXML
    private void plus5Handle(ActionEvent event) {
        STARTTIME = STARTTIME + 300;
        calculateAddSubtract();
    }

    @FXML
    private void minus5Handle(ActionEvent event) {
        STARTTIME = STARTTIME - 300;
        calculateAddSubtract();
    }
    
    private void calculate()
    {--STARTTIME;    
 if (STARTTIME <= 0)
 {timeline.stop();
 timeStatus.setTextFill(Color.DARKGOLDENROD);
 timeStatus.setText("ended");
 STARTTIME = 0;}
 
 Integer secondsRemaining = STARTTIME % 60;
 Integer minutesRemaining = STARTTIME / 60;
 String calculatedTime;
 
if (minutesRemaining < 10 && secondsRemaining < 10)
 calculatedTime =("0" + minutesRemaining.toString() + ":" + "0" +
         secondsRemaining.toString());

 else if (minutesRemaining < 10)
 calculatedTime =("0" + minutesRemaining.toString() + ":" +
         secondsRemaining.toString());
 
 else if (secondsRemaining < 10)
 calculatedTime =(minutesRemaining.toString() + ":" + "0" + 
         secondsRemaining.toString());
 else
 calculatedTime =(minutesRemaining.toString() + ":" + 
         secondsRemaining.toString());

 timerLabel.setText(calculatedTime);
 
 for(int i = 0 ; i<=3 ; ++i)
 if (conIsActive[i] == true)
 newC[i].setTime(calculatedTime);
 
 }

    
    private void calculateAddSubtract()
    {    
 if (STARTTIME <= 0)
 {timeline.stop();
 STARTTIME = 0;
 }
 
 Integer secondsRemaining = STARTTIME % 60;
 Integer minutesRemaining = STARTTIME / 60;
 String calculatedTime;
 
if (minutesRemaining < 10 && secondsRemaining < 10)
 calculatedTime =("0" + minutesRemaining.toString() + ":" + "0" +
         secondsRemaining.toString());

 else if (minutesRemaining < 10)
 calculatedTime =("0" + minutesRemaining.toString() + ":" +
         secondsRemaining.toString());
 
 else if (secondsRemaining < 10)
 calculatedTime =(minutesRemaining.toString() + ":" + "0" + 
         secondsRemaining.toString());
 else
 calculatedTime =(minutesRemaining.toString() + ":" + 
         secondsRemaining.toString());

 timerLabel.setText(calculatedTime);
 for (int i = 0; i < conIsActive.length; ++i)
     if (conIsActive[i] == true)
 newC[i].setTime(calculatedTime);
 }
   

    /*private void addCounterHandle(ActionEvent event) throws Exception {
    counterLoader newLoad = new counterLoader();
    newLoad.start();
    newC = newLoad.getController();
    counterStage = newLoad.getStage();
   }*/

    @FXML
    private void imageHandle(ActionEvent event) throws FileNotFoundException {
        if (hintIsActive == false)
        {hintIsActive = true;
        for (int i = 0; i < conIsActive.length; ++i)
            {if (conIsActive[i] == true && countCheck[i].isSelected() == true)
            newC[i].setPhoto(imageList.getValue(), true);}
        sendShout.setDisable(true);
        sendPreset.setDisable(true);
        sendImage.setText("Hide");
        FileInputStream input = new FileInputStream(imageList.getValue());
        Image image = new Image(input);
        previewImage.setVisible(true);
        previewImage.setImage(image);}
        
        else
        {hintIsActive = false;
        sendPreset.setDisable(false);
        sendShout.setDisable(false);
        shoutBox.setDisable(false);
        sendImage.setText("Send");
        displayingHint.setVisible(false);
        for (int i = 0; i < conIsActive.length; ++i)
        if (conIsActive[i] == true)
          newC[i].setPhoto(imageList.getValue(), false);
        }
    }

    @FXML
    private void presetHandle(ActionEvent event) 
    {
        if (hintIsActive == false)
         {sendPreset.setText("Hide");
          sendImage.setDisable(true);
          hintIsActive = true;
          sendShout.setDisable(true);
         for (int i = 0; i < conIsActive.length; ++i)
        if (conIsActive[i] == true && countCheck[i].isSelected() == true)
        {shoutString = presetBox.getValue();
         newC[i].setHint(shoutString, true);}
         displayingHint.setVisible(true);}
         
        
        else
        {hintIsActive = false;
        sendPreset.setDisable(false);
        sendImage.setDisable(false);
        sendShout.setDisable(false);
        shoutBox.setDisable(false);
        sendPreset.setText("Send");
        displayingHint.setVisible(false);
        for (int i = 0; i < conIsActive.length; ++i)
        if (conIsActive[i] == true)
          newC[i].setHint("", false);
        }
    }



    
    @FXML
    private void setCount1(ActionEvent event) {
        if (conIsActive[0] == true)
        {//counterStage[0].setFullScreen(true);
        counterStage[0].setMaximized(true);}
    }
    @FXML
    private void setCount2(ActionEvent event) {
        if (conIsActive[1] == true)
        {//counterStage[1].setFullScreen(true);
        counterStage[1].setMaximized(true);}
    }

    @FXML
    private void setCount3(ActionEvent event) {
        if (conIsActive[2] == true)
        {//counterStage[2].setFullScreen(true);
        counterStage[2].setMaximized(true);}
    }

    @FXML
    private void setCount4(ActionEvent event) {
        if (conIsActive[3] == true)
        {//counterStage[3].setFullScreen(true);
        counterStage[3].setMaximized(true);}
    }
    
    @FXML
    private void addCounter1Handle(ActionEvent event) {
        allCounterHandle(0);
        if(conIsActive[0] == true)
        addCounter.setTextFill(Color.BLUE);
        
        else
        addCounter.setTextFill(Color.BLACK);
    }

    @FXML
    private void addCounter2Handle(ActionEvent event) {
        allCounterHandle(1);
        if(conIsActive[1] == true)
        addCounter2.setTextFill(Color.BLUE);
        
        else
        addCounter2.setTextFill(Color.BLACK);
    }

    @FXML
    private void addCounter3Handle(ActionEvent event) {
        allCounterHandle(2);
        if(conIsActive[2] == true)
        addCounter3.setTextFill(Color.BLUE);
        
        else
        addCounter3.setTextFill(Color.BLACK);
    }

    @FXML
    private void addCounter4Handle(ActionEvent event) {
        allCounterHandle(3);
        if(conIsActive[3] == true)
        addCounter4.setTextFill(Color.BLUE);
        
        else
        addCounter4.setTextFill(Color.BLACK);
    }

    public void allCounterHandle(int i)
    {if (conIsActive[i] == false)
        {counterLoader newLoad = new counterLoader();
        newLoad.start();
        newC[i] = newLoad.getController();
        counterStage[i] = newLoad.getStage();
        conIsActive[i] = true;
        countCheck[i].setVisible(true);
        counterStatus.setText("Active:");
        counterStatus.setTextFill(Color.GREEN);
        calculateAddSubtract();
        }   
        
        else
        {counterStage[i].close();
        conIsActive[i] = false;
        countCheck[i].setVisible(false);
        if (conIsActive[0] == false)
            addCounter.setTextFill(Color.BLACK);
        if (conIsActive[1] == false)
            addCounter2.setTextFill(Color.BLACK);
        if (conIsActive[2] == false)
            addCounter3.setTextFill(Color.BLACK);
        if (conIsActive[3] == false)
            addCounter4.setTextFill(Color.BLACK);
        if(conIsActive[0] == false && conIsActive[1] == false && 
                      conIsActive[2] == false && conIsActive[3] == false)
              {counterStatus.setText("no active counters");
               counterStatus.setTextFill(Color.ORANGE);}
        }
       
        counterStage[i].setOnCloseRequest(new EventHandler<WindowEvent>() {
          @Override
          public void handle(WindowEvent we) {
              addCounter.setTextFill(Color.BLACK);
              conIsActive[i] = false;
              countCheck[i].setVisible(false);
              if(conIsActive[0] == false && conIsActive[1] == false && 
                      conIsActive[2] == false && conIsActive[3] == false)
              {counterStatus.setText("no active counters");
               counterStatus.setTextFill(Color.ORANGE);}
              if (conIsActive[0] == false)
            addCounter.setTextFill(Color.BLACK);
        if (conIsActive[1] == false)
            addCounter2.setTextFill(Color.BLACK);
        if (conIsActive[2] == false)
            addCounter3.setTextFill(Color.BLACK);
        if (conIsActive[3] == false)
            addCounter4.setTextFill(Color.BLACK);
                                             }
      });
    }
    
    
   private void getImages() throws IOException
   {File[] listOfFiles;
        FilenameFilter filter = new FilenameFilter() {
         @Override
         public boolean accept (File dir, String name) { 
            return name.contains(".jpg");
                                                       } 
        };

        File folder = new File("src\\hintsystem\\hintPhotos");
        if(folder.exists() == true)
        {listOfFiles = folder.listFiles(filter);
        imageList.getItems().addAll(Arrays.asList(listOfFiles));}
        imageList.setVisibleRowCount(7);
        imageList.getValue();       
   }
    
    private void getSamples()        
    {
        File[] listOfFiles;
            FilenameFilter filter = new FilenameFilter(){
            @Override
         public boolean accept (File dir, String name) { 
            if (name.contains(".mp3") || name.endsWith(".wav"));
                 return true;                                      }    
            };
    File folder = new File("src\\hintsystem\\soundFx");
    listOfFiles = folder.listFiles(filter);
    for (final File fileEntry : listOfFiles) {
        if (fileEntry.exists()) {
            Media media = new Media(fileEntry.toURI().toString());
            MediaPlayer sfxPlayer = new MediaPlayer(media);
        Button b = new Button(fileEntry.getName());
        b.setOnAction(new EventHandler<ActionEvent>()
    {
        @Override
        public void handle(ActionEvent e)
        {
        Status status = sfxPlayer.getStatus();
        if (status == Status.PLAYING)
        {sfxPlayer.stop();}
        
        else
         sfxPlayer.play();  
        }
    });     
        sfxPane.getChildren().add(b);
        }
    }}

    @FXML
    private void themePlayHandle(ActionEvent event) {
        mainThemePlayer.play();
    }

    @FXML
    private void themeRewindHandle(ActionEvent event) {
        mainThemePlayer.seek(mainThemePlayer.getStartTime());
    }

    @FXML
    private void themePauseHandle(ActionEvent event) {
        mainThemePlayer.pause();
    }
    
    public void setMusicControlls()
    {
        volumeSlider.valueProperty().addListener(new InvalidationListener() { 
                @Override
                public void invalidated(Observable ov) 
                { 
                    if (volumeSlider.isPressed()) { 
                        mainThemePlayer.setVolume(volumeSlider.getValue() / 100); // It would set the volume 
                        // as specified by user by pressing 
                    } 
                } 
            }); 
   mainThemePlayer.setOnReady(new Runnable() {
        @Override
        public void run() {}
                                               });
    
    musicSlider.valueProperty().addListener(new InvalidationListener() { 
                @Override
                public void invalidated(Observable ov) 
                { 
                    if (musicSlider.isPressed()) { // It would set the time 
                        // as specified by user by pressing 
                        mainThemePlayer.seek(mainThemePlayer.getMedia().getDuration().multiply(musicSlider.getValue() / 100)); 
                    } 
                } 
            }); 
    
    mainThemePlayer.currentTimeProperty().addListener((Observable ov) -> {
    updateValues();
});
    }
  
    
    protected void updateValues() {

    Platform.runLater(new Runnable() {
    @Override
    public void run() {
    Duration currentTime = mainThemePlayer.getCurrentTime();
    musicLabel.setText(formatTime(currentTime, duration));
//musicSlider.setDisable(duration.isUnknown());
//musicSlider.setValue(currentTime.toSeconds());
//if (!musicSlider.isDisabled() && duration.greaterThan(Duration.ZERO) && !musicSlider.isValueChanging()) {
//musicSlider.setValue(currentTime.divide(duration).toMillis() * 100.0);
//}
    musicSlider.setValue(mainThemePlayer.getCurrentTime().toMillis() / mainThemePlayer.getTotalDuration().toMillis() * 100); 
    }
    });

    }
    
private static String formatTime(Duration elapsed, Duration duration) {
    int intElapsed = (int) floor(elapsed.toSeconds());
    int elapsedHours = intElapsed / (60 * 60);
    if (elapsedHours > 0) {
    intElapsed -= elapsedHours * 60 * 60;
    }
    int elapsedMinutes = intElapsed / 60;
    int elapsedSeconds = intElapsed - elapsedHours * 60 * 60
    - elapsedMinutes * 60;

    if (duration.greaterThan(Duration.ZERO)) {
    int intDuration = (int) floor(duration.toSeconds());
    int durationHours = intDuration / (60 * 60);
    if (durationHours > 0) {
    intDuration -= durationHours * 60 * 60;
    }
    int durationMinutes = intDuration / 60;
    int durationSeconds = intDuration - durationHours * 60 * 60
    - durationMinutes * 60;
    if (durationHours > 0) {
    return format("%d:%02d:%02d",
    elapsedHours, elapsedMinutes, elapsedSeconds);
    } 
    else {
    return format("%02d:%02d",
    elapsedMinutes, elapsedSeconds);
    }
    }
    else {
    if (elapsedHours > 0) {
    return format("%d:%02d:%02d", elapsedHours,
    elapsedMinutes, elapsedSeconds);
    } 
    else {
    return format("%02d:%02d", elapsedMinutes,
    elapsedSeconds);
    }
    }
}    

    @FXML
    private void resetHandle(ActionEvent event) {
        STARTTIME = 4800;
        timeStatus.setText("ready");
        timeStatus.setTextFill(Color.GREEN);
        timeline.stop();
        calculateAddSubtract();
        
        
    }
    
    private void setCheckBoxes()
    {
        
        countCheck[0] = new CheckBox("Counter1");
        countCheck[1] = new CheckBox("Counter2");
        countCheck[2] = new CheckBox("Counter3");
        countCheck[3] = new CheckBox("Counter4");
        for(int i = 0; i < countCheck.length; ++i)
        {statusBar.getChildren().add(countCheck[i]);
        countCheck[i].setTextFill(Color.BLUE);
        countCheck[i].setVisible(false);
        countCheck[i].setSelected(true);
        countCheck[i].setNodeOrientation(RIGHT_TO_LEFT);
        }
    }

    @FXML
    private void timeColorSet(ActionEvent event) {
        timerLabel.setTextFill(timeColor.getValue());
        for (int i = 0; i < conIsActive.length; ++i)
            {if (conIsActive[i] == true && countCheck[i].isSelected() == true)
            newC[i].setTextColour(timeColor.getValue());}
    }

    @FXML
    private void ringHandle(ActionEvent event) 
    {
        Status status = ringPlayer.getStatus();
         
        if(status == Status.STOPPED || status != Status.PLAYING)
        {mainThemePlayer.setVolume(mainController.mainThemePlayer.getVolume() - 0.9);
        ringPlayer.play();
        ringPlayer.setOnEndOfMedia(new Runnable() {
    @Override
    public void run() 
    {ringPlayer.stop();
    mainThemePlayer.setVolume(mainController.mainThemePlayer.getVolume() + 0.9);}
                                                  });
        }
          
        if (status == Status.PLAYING)
        {ringPlayer.stop();
        mainThemePlayer.setVolume(mainController.mainThemePlayer.getVolume() + 0.9);}
     }

    public void setPresets()
    {Path file = Paths.get("src\\hintsystem\\resources\\presets.txt");      
    String s = "";
         try      
         {         
          InputStream input = new BufferedInputStream(Files.newInputStream(file));         
          BufferedReader reader = new BufferedReader(new InputStreamReader(input, "UTF-8"));                  
           while(s != null)         
               {
                            
                 s = reader.readLine(); 
                 presetBox.getItems().add(s);
                 System.out.println(s);
               }         
                   reader.close(); 
                   
         }      
         catch(Exception e)      
         {}
         
    }

    @FXML
    private void backgroundColourSet(ActionEvent event) {
      Paint fill = backgroundColour.getValue();
                BackgroundFill backgroundFill = 
                    new BackgroundFill(fill, 
                            CornerRadii.EMPTY, 
                            Insets.EMPTY);
                Background background = new Background(backgroundFill);
                timerLabel.setBackground(background);
                for (int i = 0; i < conIsActive.length; ++i)
            {if (conIsActive[i] == true && countCheck[i].isSelected() == true)
            {newC[i].setBackground(fill);
            countCheck[i].setTextFill(fill);}}
    }

    @FXML
    private void setPresetPreview(ActionEvent event) {
        previewPreset.setVisible(true);
        previewPreset.setText(presetBox.getValue());
    }

    @FXML
    private void setImagePreview(ActionEvent event) throws FileNotFoundException {
    FileInputStream input = new FileInputStream(imageList.getValue());
        Image image = new Image(input);
        previewImage.setVisible(true);
        previewImage.setImage(image);    
    }

    @FXML
    private void hidePresetPreview(MouseEvent event) {
        previewPreset.setVisible(false);
    }

    @FXML
    private void hideImagePreview(MouseEvent event) {
        previewImage.setVisible(false);
    }
    
    
}
