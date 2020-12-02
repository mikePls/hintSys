/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hintsystem;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;

/**
 *
 * @author User
 */
public class Reminder {

    int timeCheck = 0;
MediaPlayer reminderPlayer;   
public void remind(Integer STARTTIME)
    {  File f = null;
        switch(STARTTIME)
        {case 5400 : 
            f = new File("src\\hintsystem\\reminder\\1hour30minutes.mp3");
            remindPlayerSet(f);
            break;
         case 4800 :
            f = new File("src\\hintsystem\\reminder\\1hour20minutes.mp3");
            remindPlayerSet(f);
            break;
        case 4200 :
            f = new File("src\\hintsystem\\reminder\\1hour10minutes.mp3");
            remindPlayerSet(f);
            break;
        case 3600 :
            f = new File("src\\hintsystem\\reminder\\1hour.mp3");
            remindPlayerSet(f);
            break;
        case 3000 :
            f = new File("src\\hintsystem\\reminder\\50minutes.mp3");
            remindPlayerSet(f);
            break;
        case 2400 :
            f = new File("src\\hintsystem\\reminder\\40minutes.mp3");
            remindPlayerSet(f);
            break;
        case 1800 :
            f = new File("src\\hintsystem\\reminder\\30minutes.mp3");
            remindPlayerSet(f);
            break;
        case 1200 :
            f = new File("src\\hintsystem\\reminder\\20minutes.mp3");
            remindPlayerSet(f);
            break;
        case 600 :
            f = new File("src\\hintsystem\\reminder\\10minutes.mp3");
            remindPlayerSet(f);
            break;
        case 300 :
            f = new File("src\\hintsystem\\reminder\\5minutes.mp3");
            remindPlayerSet(f);
            break;
        case 60 :
            f = new File("src\\hintsystem\\reminder\\1minute.mp3");
            remindPlayerSet(f);
            break;
        case 5 :
            f = new File("src\\hintsystem\\reminder\\5.mp3");
            remindPlayerSet(f);
            break;
        case 4 :
            f = new File("src\\hintsystem\\reminder\\4.mp3");
            remindPlayerSet(f);
            break;
        case 3 :
            f = new File("src\\hintsystem\\reminder\\3.mp3");
            remindPlayerSet(f);
            break;
        case 2 :
            f = new File("src\\hintsystem\\reminder\\2.mp3");
            remindPlayerSet(f);
            break;
        case 1 :
            f = new File("src\\hintsystem\\reminder\\1.mp3");
            remindPlayerSet(f);
            break;
        case 0 :
            f = new File("src\\hintsystem\\reminder\\end.mp3");
            remindPlayerSet(f);
            break;
            
        default:
                timeCheck = 0;
             
        }         
    }
    
    public void remindPlayerSet(File f)
    {Media remind;
     remind = new Media(f.toURI().toString());
     reminderPlayer = new MediaPlayer(remind);
     mainController.mainThemePlayer.setVolume(mainController.mainThemePlayer.getVolume() - 0.7);
     reminderPlayer.play();
     reminderPlayer.setOnEndOfMedia(new Runnable() {
        @Override
       public void run() 
       {reminderPlayer.stop();
       mainController.mainThemePlayer.setVolume(mainController.mainThemePlayer.getVolume() + 0.7);}
                                                  }); 
     
    }    
    
}
