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
package functiontree;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Implements the FunctionNode of a function tree, which represents either a function
 with as many child Nodes as arguments, or a constant.
 *
 * @author eberh_000
 */
public abstract class FunctionNode {

    final private int childrenCount;
    protected FunctionNode[] child;
    protected int level = -1;
    
    // Using java.util.Function would reduce redundant code in the
    // operator node classes, but would complicate the use of functions
    // with more than two input parameters
    // private TriFunction<Double, Double, Integer, Double> operator;

    public FunctionNode(int numberOfChilds) {
        this.childrenCount = numberOfChilds;
        child = new FunctionNode[numberOfChilds];
    }
    
    public void setLevel( int level ) {
        this.level = level;
    }
    
    public int getChildrenCount() {
        return childrenCount;
    }
    

    public abstract double operate( double x, double y, int maxLevel );


    public void setChildNode(int index, FunctionNode childNode) {
        if (index < 0 || index > childrenCount - 1) {
            throw new IllegalStateException("Node " + this
                    + "cannot have more than " + childrenCount + " child nodes.");
        }

        child[index] = childNode;
    }
    
    public FunctionNode getChildNode(int index) {
        if (index < 0 || index > childrenCount - 1) {
            throw new IllegalStateException("Node " + this
                    + "has no child of index " + index + ".");
        }
        return child[ index ];
    }
   

}
