/***
 * Excerpted from "The Definitive ANTLR 4 Reference",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/tpantlr2 for more book information.
***/
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileInputStream;
import java.io.InputStream;

public class MainVisit {
    private static class Visitor extends CalculatorBaseVisitor<Integer> {
        
        @Override 
        public Integer visitParens(CalculatorParser.ParensContext ctx) { 
            return visit(ctx.expr());
        }

        @Override 
        public Integer visitMulDiv(CalculatorParser.MulDivContext ctx) { 
            int left = visit(ctx.expr(0));
            int right = visit(ctx.expr(1));
            if (ctx.op.getType() == CalculatorParser.MUL) {
                return left * right;
            } else {
                return left / right;
            }
        }
        
        @Override 
        public Integer visitAddSub(CalculatorParser.AddSubContext ctx) { 
            int left = visit(ctx.expr(0));
            int right = visit(ctx.expr(1));
            if (ctx.op.getType() == CalculatorParser.ADD) {
                return left + right;
            } else {
                return left - right;
            }
        }

        @Override 
        public Integer visitInt(CalculatorParser.IntContext ctx) { 
            return Integer.valueOf(ctx.INT().getText());
        }
    }

    public static void main(String[] args) throws Exception {
        String inputFile = null;
        if ( args.length>0 ) inputFile = args[0];
        InputStream is = System.in;
        if ( inputFile!=null ) is = new FileInputStream(inputFile);
        ANTLRInputStream input = new ANTLRInputStream(is);
        CalculatorLexer lexer = new CalculatorLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        //System.out.println(tokens.getText());

        CalculatorParser parser = new CalculatorParser(tokens);
        ParseTree tree = parser.expr(); // parse

        Visitor eval = new Visitor();
        System.out.println(eval.visit(tree));
    }
}
