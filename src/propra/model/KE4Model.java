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
package propra.model;

import functioncanvas.FunctionCanvas;
import functioncanvas.FunctionTreeCanvas;
import functioncanvas.MandelbrotCanvas;
import functioncanvas.Selection;
import functiontree.ConstantNodeFactory;
import functiontree.FunctionNode;
import functiontree.OperatorNodeFactory;
import functiontree.operatornodes.ConstantNode;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 *
 * @author eberh_000
 */
public class KE4Model extends GeneratorModel {
    
    SimpleIntegerProperty width = new SimpleIntegerProperty( 500 );
    SimpleIntegerProperty height = new SimpleIntegerProperty( 500 );
    
    SimpleIntegerProperty randomSeedRed = new SimpleIntegerProperty( 0 );
    SimpleIntegerProperty randomSeedGreen = new SimpleIntegerProperty( 0 );
    SimpleIntegerProperty randomSeedBlue = new SimpleIntegerProperty( 0 );
    SimpleIntegerProperty treeHeightRed = new SimpleIntegerProperty( 10 );
    SimpleIntegerProperty treeHeightGreen = new SimpleIntegerProperty( 10 );
    SimpleIntegerProperty treeHeightBlue = new SimpleIntegerProperty( 10 );

    
    
    public SimpleIntegerProperty getWidth() {
        return width;
    }

    public SimpleIntegerProperty getHeight() {
        return height;
    }

    public SimpleIntegerProperty getRandomSeedRed() {
        return randomSeedRed;
    }

    public SimpleIntegerProperty getRandomSeedGreen() {
        return randomSeedGreen;
    }

    public SimpleIntegerProperty getRandomSeedBlue() {
        return randomSeedBlue;
    }

    public SimpleIntegerProperty getTreeHeightRed() {
        return treeHeightRed;
    }

    public SimpleIntegerProperty getTreeHeightGreen() {
        return treeHeightGreen;
    }

    public SimpleIntegerProperty getTreeHeightBlue() {
        return treeHeightBlue;
    }
    
    



    

    @Override
    public String getGeneratorName() {
        return "KE4 Generator";
    }

    @Override
    public void generate() {
      

        canvas = new FunctionTreeCanvas( width.get(), height.get(), 
                        randomSeedRed.get(),
                        randomSeedGreen.get(),
                        randomSeedBlue.get(),
                        treeHeightRed.get(),
                        treeHeightGreen.get(),
                        treeHeightBlue.get()
                        );

        setGeneratorState("Calculating...");
        
        ( (FunctionTreeCanvas)canvas ).drawOnCanvas();
        
        setGeneratorState( GeneratorState.FINISHED_READY );

    }

    private AnchorPane getFittingAnchorPane(Canvas canvas) {
        AnchorPane result = new AnchorPane();
        result.setPrefSize(canvas.getWidth(), canvas.getHeight());
        return result;
    }

}
