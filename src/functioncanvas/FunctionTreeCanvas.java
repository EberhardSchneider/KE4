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
package functioncanvas;

import functiontree.RandomFunctionTree;
import java.awt.Color;

/**
 *
 * @author eberh_000
 */
public class FunctionTreeCanvas extends FunctionCanvas {
    
    RandomFunctionTree randomTreeRed;
    RandomFunctionTree randomTreeGreen;
    RandomFunctionTree randomTreeBlue;
    
    public FunctionTreeCanvas( int width, int height, 
            int randomSeedRed, int randomSeedGreen, int randomSeedBlue,
            int treeHeightRed, int treeHeightGreen, int treeHeightBlue) {
        super( width, height, 0.0, 0.0, 1.0, 1.0 );
        
        randomTreeRed = new RandomFunctionTree( 0, treeHeightRed );
        randomTreeGreen = new RandomFunctionTree( 0, treeHeightGreen );
        randomTreeBlue = new RandomFunctionTree( 0, treeHeightBlue  );
        
        
        randomTreeRed.setRandomSeed( randomSeedRed );
        randomTreeGreen.setRandomSeed( randomSeedGreen );
        randomTreeBlue.setRandomSeed( randomSeedBlue );

    }

    @Override
    double getPixelValue(double x, double y) {

        double red = randomTreeRed.calculate( x, y) * 255.0;
        double green = randomTreeGreen.calculate( x, y) * 255.0;
        double blue = randomTreeBlue.calculate( x, y) * 255.0;
        
//        int rgb = ((int)red << 16 | (int)green << 8 | (int)blue);
        Color color = Color.getHSBColor((float)red/255.0f, (float)green/255.0f, (float)blue/255.0f);
        return color.getRGB();
        //int rgb = (int) ( randomTreeRed.calculate( x, y) * 256 * 256 * 256 );

    }

    @Override
    Color getColorFromValue(double value) {
        return new Color( (int)value );
    }
    
}
