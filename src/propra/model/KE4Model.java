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
import functioncanvas.MandelbrotCanvas;
import functioncanvas.Selection;
import functiontree.ConstantNodeFactory;
import functiontree.Node;
import functiontree.OperatorNodeFactory;
import functiontree.operatornodes.ConstantNode;
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

    @Override
    public String getGeneratorName() {
        return "KE4 Generator";
    }

    @Override
    public void generate() {

        Node root = OperatorNodeFactory
                .getNode(OperatorNodeFactory.Operator.DIV);
        Node constant1 = ConstantNodeFactory.getNode(2.0);
        Node constant2 = ConstantNodeFactory.getNode(3.0);

        root.setChildNode(0, constant1);
        root.setChildNode(1, constant2);

        System.out.println(root.operate());

        canvas = new MandelbrotCanvas(500, 500, -2.5, -1.0, 1.0, 1.0);

        AnchorPane wrapper = getFittingAnchorPane(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 500, 500);

        ((FunctionCanvas) canvas).init();
//        ((FunctionFunctionCanvas).addSelectionListener((o, oldValue, newValue) -> {
//            ((FunctionTreeFunctionCanvastNumberSpace((Selection) newValue);
//        });

        wrapper.getChildren().addAll(canvas);
        ((FunctionCanvas) canvas).drawOnCanvas();
        scrollPane.setContent(wrapper);
    }

    private AnchorPane getFittingAnchorPane(Canvas canvas) {
        AnchorPane result = new AnchorPane();
        result.setPrefSize(canvas.getWidth(), canvas.getHeight());
        return result;
    }

}
