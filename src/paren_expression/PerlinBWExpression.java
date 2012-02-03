package paren_expression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import expression.Expression;
import expression.ExpressionFactory;

import model.RGBColor;
import model.util.PerlinNoise;

public class PerlinBWExpression extends ParenExpression {
    
    private static String[] myCommands = { "perlinBW"};

    PerlinBWExpression(ArrayList<Expression> operands) 
    {
        myOperands = operands;
    }

    public List<String> getCommand() 
    {
        return Arrays.asList(myCommands);
    }

    public int getNumberOfOperands() 
    {
        return 2;
    }

    public Expression makeNewExpression(ArrayList<Expression> operands) 
    {

        return new PerlinBWExpression(operands);
    }

    public RGBColor evaluate() 
    {
        List<RGBColor> results = evaluateOperands();
        RGBColor operand1 = results.get(0);
        RGBColor operand2 = results.get(1);

        return PerlinNoise.greyNoise(operand1, operand2);

    }

    private PerlinBWExpression() {   }

    public static ExpressionFactory getFactory() 
    {
        return new ExpressionFactory(new PerlinBWExpression());
    }

}
