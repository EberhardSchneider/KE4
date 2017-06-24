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
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Eberhard Schneider
 */
public abstract class FunctionCanvas extends Canvas {

    // the variables which define the number space
    Selection numberSpace;

    // second Canvas which overlays "this" and in which the selection
    // rectangle is drawn
    Canvas selectionCanvas;

    // deltaX, deltaY  - difference in number space to get from one
    // pixel to another in x/y-direction
    double deltaX;
    double deltaY;

    ObjectProperty<Selection> selection = new SimpleObjectProperty<>();

    GraphicsContext gc;

    // variables for mouse listener
    int mouseClickedX;
    int mouseClickedY;

    int mouseCurrentX;
    int mouseCurrentY;

    public FunctionCanvas(int width, int height,
            double minX, double minY,
            double maxX, double maxY) {

        super(width, height);

        if (maxX - minX < 0.0001f) {
            throw new RuntimeException("minX must be sufficently smaller then maxX");
        }
        if (maxY - minY < 0.0001f) {
            throw new RuntimeException("minY must be sufficently smaller then maxY");
        }

        numberSpace = new Selection(minX, maxX, minY, maxY);

        deltaX = (maxX - minX) / this.getWidth();
        deltaY = (maxY - minY) / this.getHeight();
//
//
//        selection.set(new Selection(0, 0, 0, 0));
//
//        // set the selectionCanvas
//        // first get the parent scrollpane
//        ScrollPane parent = new ScrollPane(this);
//        System.out.println(parent);
//        AnchorPane wrapper = new AnchorPane();
//
//        selectionCanvas = new Canvas(this.getWidth(), this.getHeight());
//        gc = selectionCanvas.getGraphicsContext2D();
//        gc.setFill(javafx.scene.paint.Color.RED);
//        gc.fillRect(50, 50, 100, 100);
//        wrapper.getChildren().addAll(selectionCanvas);
//
//        parent.setContent(wrapper);
//
//        addSelectionListener();

    }

    public void setNumberSpace(Selection numberSpace) {
        this.numberSpace = numberSpace;
    }

    public void drawOnCanvas() {

        System.out.println("Entering drawOnCanvas");

        BufferedImage image = new BufferedImage((int) this.getWidth(), (int) this.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);

    
        for (int pixelY = 0; pixelY < this.getHeight(); pixelY++) {
            for (int pixelX = 0; pixelX < this.getWidth(); pixelX++) {

                Point p = new Point(pixelX, pixelY);
                Point.Double numberPoint = canvasPixelToNumberSpace(p);

                double pixelValue = getPixelValue(numberPoint.getX(), numberPoint.getY());
                Color color = getColorFromValue(pixelValue);

                int rgb = color.getRGB();

                image.setRGB(pixelX, pixelY, rgb);
            }

        }


        // now draw buffered image on canvas
        WritableImage fxImage = new WritableImage(image.getWidth(),
                image.getHeight());
        SwingFXUtils.toFXImage(image, fxImage);
        this.getGraphicsContext2D().drawImage(fxImage, 0, 0);

    }

    public void init() {

        // add mouselisteners
        this.setOnMousePressed((MouseEvent event) -> {
            mouseClickedX = (int) event.getX();
            mouseClickedY = (int) event.getY();
        });

        this.setOnMouseDragged((MouseEvent event) -> {
            if (event.isPrimaryButtonDown()) {
                mouseCurrentX = (int) event.getX();
                mouseCurrentY = (int) event.getY();
                gc.setFill(javafx.scene.paint.Color.WHITE);
                gc.setStroke(javafx.scene.paint.Color.BLACK);
                gc.clearRect(0, 0, getWidth(), getHeight());
                gc.strokeRect(mouseClickedX,
                        mouseClickedY,
                        mouseCurrentX - mouseClickedX,
                        mouseCurrentY - mouseClickedY);
            }
        });

        this.setOnMouseReleased((MouseEvent event) -> {

            selection.set(new Selection(mouseClickedX,
                    mouseCurrentX,
                    mouseClickedY,
                    mouseCurrentY));

        });

    }

    // Helper methods
    public Point numberSpaceToCanvasPixel(Point.Double p) {

        double x = p.getX();
        double y = p.getY();

        double numberspaceWidth = numberSpace.getX2() - numberSpace.getX1();
        double numberspaceHeight = numberSpace.getY2() - numberSpace.getY2();

        int newX = (int) ((x - numberSpace.getX1()) * this.getWidth() / numberspaceWidth);
        int newY = (int) ((y - numberSpace.getY1()) * this.getHeight() / numberspaceHeight);

        return new Point(newX, newY);
    }

    public Point.Double canvasPixelToNumberSpace(Point p) {

        int x = (int) p.getX();
        int y = (int) p.getY();

        // adjust direction of y coordinates
        //y = (int) this.getHeight() - y;
        double numberspaceWidth = numberSpace.getX2() - numberSpace.getX1();
        double numberspaceHeight = numberSpace.getY2() - numberSpace.getY1();

        double newX = (double) x * numberspaceWidth / this.getWidth() + numberSpace.getX1();
        double newY = (double) y * numberspaceHeight / this.getHeight() + numberSpace.getY1();

        return new Point.Double(newX, newY);
    }

    public void addSelectionListener() {
        selection.addListener((obs, oldV, newV) -> {
            System.out.println("Old: " + numberSpace.toString());
            Selection s = (Selection) newV;
            System.out.println(s.toString());
            Point pixelPoint1 = new Point((int) s.getX1(), (int) s.getY1());
            Point pixelPoint2 = new Point((int) s.getX2(), (int) s.getY2());
            System.out.println("Points before: " + pixelPoint1.toString() + ", " + pixelPoint2.toString());

            Point.Double numberPoint1 = canvasPixelToNumberSpace(pixelPoint1);
            Point.Double numberPoint2 = canvasPixelToNumberSpace(pixelPoint2);

            System.out.println("Points after calculation: " + numberPoint1.toString() + ", " + numberPoint2.toString());

            Selection newSelection = new Selection(numberPoint1.getX(),
                    numberPoint2.getX(),
                    numberPoint1.getY(),
                    numberPoint2.getY());

            numberSpace = newSelection;
            System.out.println("New: " + numberSpace.toString());

            drawOnCanvas();

        });
    }

    public void addSelectionListener(ChangeListener listener) {
        selection.addListener(listener);
    }

    abstract double getPixelValue(double x, double y);

    abstract Color getColorFromValue(double value);

}
