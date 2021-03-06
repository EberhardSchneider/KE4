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
package functiontree.operatornodes;

import functiontree.FunctionNode;

/**
 *
 * @author eberh_000
 */
public class DivisionNode extends FunctionNode {

    public DivisionNode() {
        super(2);
    }

    @Override
    public double operate(double x, double y, int maxLevel ) {
        
        if (level < maxLevel) {
            // first check if divisor is zero
            double child1Result = child[1].operate( x, y, maxLevel);
            return (child1Result != 0) ? 
                    child[0].operate( x, y, maxLevel) / child1Result :
                    0.5;
        } else {
            return (y != 0) ?
                    x / y :
                    0.5;
        }
    }
  

}
