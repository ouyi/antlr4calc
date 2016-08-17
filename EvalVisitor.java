
public class EvalVisitor extends CalculatorBaseVisitor<Integer> {
    
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
