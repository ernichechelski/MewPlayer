/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License"). You
 * may not use this file except in compliance with the License. You can
 * obtain a copy of the License at
 * https://glassfish.dev.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */ 

package screensframework;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import mediaplayer.MediaPlayerEngine;

/**
 * FXML Controller class
 *
 * @author Angie
 * @author Ernest Chechelski
 */
public class Screen1Controller implements Initializable, ControlledScreen {

    
     
     
    @FXML
    private ProgressBar progressBar;

    @FXML
    private ProgressBar volumeBar;
    
    @FXML
    private Label artist;

    @FXML
    private Label title;

    @FXML
    private Label album;

    @FXML
    private Label year;
    
    @FXML
    private ImageView imageView;


    
    ScreensController myController;
    static public MediaPlayerEngine media;
    
    
 
    
    
    /**
     * Initializes the controller class.
     */
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    System.out.println("Initialize 1");
       
    	media = new MediaPlayerEngine(progressBar,volumeBar,imageView); 
    	System.out.println("Initialize 2");
       
       media.setMetaDataLabels(artist, title, album, year);
       System.out.println("Initialize 3");
       
       
    }
    
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }

    @FXML
    private void goToScreen2(ActionEvent event){
       media.getPlaylist().refreshtMetadata(media.getPlaylist().getMediaList());
       myController.setScreen(ScreensFramework.screen2ID);
    }
    
    @FXML
    private void goToScreen3(ActionEvent event){
       media.getPlaylist().refreshtMetadata(media.getPlaylist().getMediaList());
       myController.setScreen(ScreensFramework.screen3ID);
    }
    
    
    
    @FXML
    private void openButton(ActionEvent event){
        media.addSongs(ScreensFramework.stage);
        
    }
   
     @FXML
    private void startButton(ActionEvent event){
       media.play();
    }
   
     @FXML
    private void pauseButton(ActionEvent event){
        media.pause();
    }
   
    @FXML
    private void stopButton(ActionEvent event){
        media.stop();
    }
   
    @FXML
    private void nextButton(ActionEvent event){
        media.next();
    }
   
    @FXML
    private void previousButton(ActionEvent event){
        media.previous();
    }
    
    @FXML
    private void infoButton(ActionEvent event){
    	imageView.setImage(media.getCurrentSongImage());
        media.getPlaylist().refreshtMetadata(media.getPlaylist().getMediaList());
        System.out.println(media.getPlaylist().getOverview());
        media.getPlaylist().mutate();
    }
   
    
}
