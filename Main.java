/***
 * Excerpted from "The Definitive ANTLR 4 Reference",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material,
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose.
 * Visit http://www.pragmaticprogrammer.com/titles/tpantlr2 for more book information.
***/
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Stack;

public class Main {
    private static class Listner extends CalculatorBaseListener {

        private Stack<Integer> stack = new Stack<Integer>();

        public int getResult() {
            return stack.peek();
        }

        @Override
        public void exitMulDiv(CalculatorParser.MulDivContext ctx) {
            int right = stack.pop();
            int left = stack.pop();
            int result;
            if (ctx.op.getType() == CalculatorParser.MUL) {
                result = left * right;
            } else {
                result = left / right;
            }
            stack.push(result);
        }

        @Override
        public void exitAddSub(CalculatorParser.AddSubContext ctx) {
            int right = stack.pop();
            int left = stack.pop();
            int result;
            if (ctx.op.getType() == CalculatorParser.ADD) {
                result = left + right;
            } else {
                result = left - right;
            }
            stack.push(result);
        }

        @Override
        public void exitInt(CalculatorParser.IntContext ctx) {
            stack.push(Integer.valueOf(ctx.INT().getText()));
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

        ParseTreeWalker walker = new ParseTreeWalker();
        Listner listner = new Listner();
        walker.walk(listner, tree);
        System.out.println(listner.getResult());

    }
}
