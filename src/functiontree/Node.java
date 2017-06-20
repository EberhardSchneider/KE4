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

/**
 * Implements the Node of a function tree, which represents either a function
 * with as many child Nodes as arguments, or a constant.
 *
 * @author eberh_000
 */
public abstract class Node {

    final int numberOfChilds;
    protected Node[] childs;

    public Node(int numberOfChilds) {
        this.numberOfChilds = numberOfChilds;
        childs = new Node[numberOfChilds];
    }

    public abstract double operate();

    public void setChildNode(int index, Node childNode) {
        if (index < 0 || index > numberOfChilds - 1) {
            throw new RuntimeException("Node " + this
                    + "cannot have more than " + numberOfChilds + " child nodes.");
        }

        childs[index] = childNode;
    }

}
