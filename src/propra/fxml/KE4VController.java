/*
 * The MIT License
 *
 * Copyright 2017 eberh_000.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package propra.fxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;
import propra.model.GeneratorModel;
import propra.model.KE4Model;

/**
 * FXML Controller class
 *
 * @author eberh_000
 */
public class KE4VController extends GeneratorController implements Initializable {

    KE4Model model;
    
    @FXML TextField tfRandomSeedRed;
    @FXML TextField tfRandomSeedGreen;
    @FXML TextField tfRandomSeedBlue;
    
    @FXML TextField tfTreeHeightRed;
    @FXML TextField tfTreeHeightGreen;
    @FXML TextField tfTreeHeightBlue;
    
    @FXML TextField tfCanvasWidth;
    @FXML TextField tfCanvasHeight;
    
    @FXML Label labelFirstRow;
    @FXML Label labelSecondRow;
    @FXML Label labelThirdRow;
    
    @FXML RadioButton rbRGB;
    @FXML RadioButton rbHSV;
    @FXML ToggleGroup tgColorModel;
    

    public KE4VController() {
        model = new KE4Model();
    }

    public void buttonGenerateClicked() {
        model.generateInNewThread();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
        Bindings.bindBidirectional(tfRandomSeedRed.textProperty(), model.getRandomSeedRed(), new NumberStringConverter() );
        Bindings.bindBidirectional(tfRandomSeedGreen.textProperty(), model.getRandomSeedGreen(), new NumberStringConverter() );
        Bindings.bindBidirectional(tfRandomSeedBlue.textProperty(), model.getRandomSeedBlue(), new NumberStringConverter() );
        
        Bindings.bindBidirectional(tfTreeHeightRed.textProperty(), model.getTreeHeightRed(), new NumberStringConverter() );
        Bindings.bindBidirectional(tfTreeHeightGreen.textProperty(), model.getTreeHeightGreen(), new NumberStringConverter() );
        Bindings.bindBidirectional(tfTreeHeightBlue.textProperty(), model.getTreeHeightBlue(), new NumberStringConverter() );
        
        
        Bindings.bindBidirectional(tfCanvasWidth.textProperty(), model.getWidth(), new NumberStringConverter() );
        Bindings.bindBidirectional(tfCanvasHeight.textProperty(), model.getHeight(), new NumberStringConverter() );
        
        tgColorModel.getProperties().addListener( new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        } );
        
        
    }

    @Override
    GeneratorModel getModel() {
        return model;
    }
    
    @FXML
    public void rollRedDice() {
        tfRandomSeedRed.setText( randomLongAsString() );
    }
    
    @FXML
    public void rollGreenDice() {
        tfRandomSeedGreen.setText( randomLongAsString() );
    }
    
    @FXML
    public void rollBlueDice() {
        tfRandomSeedBlue.setText( randomLongAsString() );
    }
    
    @FXML
    public void rollAllDice() {
        rollRedDice();
        rollGreenDice();
        rollBlueDice();
    }
    
    private String randomLongAsString() {
        long r = (long)(Math.random() * Long.MAX_VALUE);
        return String.valueOf( r );
    }

}
