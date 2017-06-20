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

import functiontree.operatornodes.*;

/**
 * This factory method makes nodes for the function tree.
 *
 * @author eberh_000
 */
public class OperatorNodeFactory {

    public enum Operator {
        ADD, SUB, MUL, DIV, SIN, COS, SQRT, CONST
    }

    public static Node getNode(Operator o) {

        switch (o) {
            case ADD:
                return new AdditionNode();
            case SUB:
                return new SubstractionNode();
            case MUL:
                return new MultiplicationNode();
            case DIV:
                return new DivisionNode();
            case SIN:
                return new SinusNode();
            case COS:
                return new CosinusNode();
            case SQRT:
                return new SquareRootNode();
            case CONST:
                return new ConstantNode(0);
            default:
                throw new RuntimeException("NodeFactory: Node not known.");
        }
    }
}
