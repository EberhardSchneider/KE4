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

/**
 *
 * @author eberh_000
 */
public class MandelbrotCanvas extends FunctionCanvas {

    private int iterationLimit = 400;
    private int magnitudeLimit = 4;

    private static final Color[] colors = {Color.BLACK, Color.RED, Color.BLUE, Color.MAGENTA, Color.GREEN};

    public void setIterationLimit(int iterationLimit) {
        this.iterationLimit = iterationLimit;
    }

    public MandelbrotCanvas(int width, int height, double minX, double minY, double maxX, double maxY) {
        super(width, height, minX, minY, maxX, maxY);
    }

    @Override
    double getPixelValue(double x, double y) {

        int pixelValue = mandelbrot(x, y);
        return pixelValue;

    }

    @Override
    Color getColorFromValue(double value) {

        int index = (int) value % colors.length;
        return colors[index];

    }

    private int mandelbrot(double x, double y) {

        int iterationCount = 0;
        Complex z = new Complex(0, 0);
        Complex c = new Complex(x, y);

        while (iterationCount < iterationLimit && z.magnitude() < magnitudeLimit) {
            iterationCount++;

            z = z.multiply(z);
            z = z.add(c);

        }

        return iterationCount;
    }

    // here a simple class for complex numbers
    static class Complex {

        private double real;
        private double imaginary;

        public Complex(double real, double imaginary) {
            this.real = real;
            this.imaginary = imaginary;
        }

        public double getReal() {
            return real;
        }

        public double getImaginary() {
            return imaginary;
        }

        public void setReal(double real) {
            this.real = real;
        }

        public void setImaginary(double imaginary) {
            this.imaginary = imaginary;
        }

        // Methods
        public double magnitude() {
            return Math.sqrt(real * real + imaginary * imaginary);
        }

        public Complex add(Complex z) {
            return new Complex(real + z.getReal(),
                    imaginary + z.getImaginary());
        }

        public Complex multiply(Complex z) {
            return new Complex(real * z.getReal() - imaginary * z.getImaginary(),
                    real * z.getImaginary() + imaginary * z.getReal());

        }
    }
}
