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
 * " Undecorator Class by Arnaud Nouard [2015] [Ernest Chechelski] "
 * " MusicPlayer GUI [2015] [Ernest Chechelski] "
 * 
 */ 

package screensframework;

import insidefx.undecorator.UndecoratorScene;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Angie
 * @author Ernest Chechelski
 */
public class ScreensFramework extends Application 
{
    public static String screen1ID = "main";
    public static String screen1File = "Screen1.fxml";
    public static String screen2ID = "screen2";
    public static String screen2File = "Screen2.fxml";
    public static String screen3ID = "screen3";
    public static String screen3File = "Screen3.fxml";
    public static Stage stage;
    
    @Override
    public void start(Stage primaryStage) 
    {
        stage = primaryStage;
        ScreensController mainContainer = new ScreensController();
        
        mainContainer.loadScreen(ScreensFramework.screen1ID, ScreensFramework.screen1File);
        mainContainer.loadScreen(ScreensFramework.screen2ID, ScreensFramework.screen2File);
        mainContainer.loadScreen(ScreensFramework.screen3ID, ScreensFramework.screen3File);
        mainContainer.setScreen(ScreensFramework.screen1ID);
        
       
        // The Undecorator as a Scene
        final UndecoratorScene undecoratorScene = new UndecoratorScene(primaryStage, mainContainer);
        undecoratorScene.setFadeInTransition();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent we) {
                we.consume();   // Do not hide yet
                undecoratorScene.setFadeOutTransition();
            }
        });
        undecoratorScene.setFadeInTransition();
        primaryStage.setScene(undecoratorScene);
        primaryStage.widthProperty().addListener(createChangeListenerForWidthProperty());
        primaryStage.heightProperty().addListener(createChangeListenerForHeightProperty());
        primaryStage.setMinHeight(550);
        primaryStage.setMinWidth(650);
        primaryStage.show();
        
        
    }

  
    private ChangeListener<Number> createChangeListenerForHeightProperty() 
    {
       return new ChangeListener<Number>()
       {
          @Override
          public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println("Height = "+ newValue);
         }// value changed 
       };      
    }
    private ChangeListener<Number> createChangeListenerForWidthProperty() 
    {
       return new ChangeListener<Number>()
       {
          @Override
          public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
              
        	  System.out.println("Width = "+ newValue);
             
          }// value changed 
       };      
    }
    
    
    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
