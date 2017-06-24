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
import java.util.Random;

/**
 * This factory method makes nodes for the function tree.
 *
 * @author eberh_000
 */
public class OperatorNodeFactory {

    public enum Operator {
        ADD, 
//        SUB, MUL, DIV, 
        SIN, COS,
//        SQR, EXP, 
//        ARCCOS, ARCSIN,
        MEAN
    }
    public static FunctionNode getNode(Operator o) {

        switch (o) {
            case ADD:
                return new AdditionNode();
//            case SUB:
//                return new SubstractionNode();
//            case MUL:
//                return new MultiplicationNode();
//            case DIV:
//                return new DivisionNode();
            case SIN:
                return new SinusNode();
            case COS:
                return new CosinusNode();
//            case SQR:
//                return new SquareNode();
//            case EXP:
//                return new ExpNode();
//            case ARCCOS:
//                return new ArccosNode();
//            case ARCSIN:
//                return new ArcsinNode();
            case MEAN:
                return new MeanNode();
                
            default:
                throw new RuntimeException("NodeFactory: Node not known.");
        }
    }
    
   public FunctionNode getNodeById(int id) {
       Operator[] values = Operator.values();
       
       if ( id < 0 || id > values.length ) {
           throw new IllegalStateException("OperatorNodeFactory: cannot get "
                        + "Node with id " + id + ".");
       }
       
       return getNode( values[ id ]);
   }
   
   public static FunctionNode getRandomNode( Random random ) {
       // get random id for enum Operator
       int id = random.nextInt( Operator.values().length );
       FunctionNode node = getNode( Operator.values()[ id ]);
       return node;
   }
   
   public int getFunctionCount() {
       return Operator.values().length;
   }
  
}
