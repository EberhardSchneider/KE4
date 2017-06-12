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

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javafx.scene.canvas.Canvas;

/**
 *
 * @author Eberhard Schneider
 */
public abstract class FunctionTreeCanvas extends Canvas {
    
    // the variables which define the 
    double minX;
    double minY;
    double maxX;
    double maxY;
    
    // deltaX, deltaY  - difference in number space to get from one 
    // pixel to another in x/y-direction
    double deltaX;
    double deltaY;
    
    public FunctionTreeCanvas( double minX, double minY, double maxX, double maxY ) {
        
        super( 1000, 800 );
        
        if ( maxX - minX < 0.0001f )
            throw new RuntimeException("minX must be sufficently smaller then maxX");
        if ( maxY - minY < 0.0001f ) 
            throw new RuntimeException("minY must be sufficently smaller then maxY");
        
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
        
        deltaX = ( maxX - minX ) / this.getWidth();
        deltaY = ( maxY - minY ) / this.getHeight();
        
    }
    
    public void drawOnCanvas() {
        
        BufferedImage image = new BufferedImage( (int)this.getWidth(), (int)this.getHeight(), BufferedImage.TYPE_4BYTE_ABGR );
        
        double x = minX;
        double y = minY;
        
        for (int pixelY = 0; pixelY < this.getHeight(); pixelY++) {
            for ( int pixelX = 0; pixelX < this.getWidth(); pixelX++) {
                
                double pixelValue = getPixelValue( x, y );
                Color color = getColorFromValue( pixelValue );
                
                int rgb = color.getRGB();
                
                image.setRGB(pixelX, pixelY, rgb);
                
                x += deltaX;
            }
            
            x = minX;
            y += deltaY;
        }
    }
    
    abstract double getPixelValue( double x, double y);
    
    abstract Color getColorFromValue( double value );

    
}
