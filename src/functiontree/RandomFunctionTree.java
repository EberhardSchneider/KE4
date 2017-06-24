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

import java.util.Random;

/**
 *
 * @author eberh_000
 */
public class RandomFunctionTree {

    private FunctionNode root;
    private final int height;
    Random random;
    
    public RandomFunctionTree( long randomSeed, int height ) {
        
        random = new Random( randomSeed );
        root = OperatorNodeFactory.getRandomNode( random );
        root.setLevel( 1 );
        this.height = height;
        
        buildRandomTree( root, 0 );
        
    }

    private void buildRandomTree( FunctionNode node, int level ) {
        
        if (level >= height) {
            return;
        }
        
        for ( int i = 0; i < node.getChildrenCount(); i++) {
            FunctionNode child = OperatorNodeFactory.getRandomNode( random );
            node.setChildNode(i, child);
            node.setLevel( level );
            buildRandomTree( child, level+1 );
        }
    }
    
//    private void setConstants(FunctionNode node, int level, double x, double y) {
//        
//        if (level == height) {
//           for (int i = 0; i < node.getChildrenCount(); i++) {
//               FunctionNode constant = ConstantNodeFactory.getNode( (i % 2 == 0) ? x : y );
//               node.setChildNode( i, constant );
//           } 
//        } else {
//          for (int i = 0; i < node.getChildrenCount(); i++ ) {
//              setConstants( node.getChildNode(i), level+1, x, y);
//          }
//        }
//    }
    
    public double calculate( double x, double y ) {
        return root.operate( x, y, height-1 );
    }
    
   public void setRandomSeed( long seed ) {
       random = new Random( seed );
       root = OperatorNodeFactory.getRandomNode( random );
       buildRandomTree( root, 0 );
   }
    
    
    
}
