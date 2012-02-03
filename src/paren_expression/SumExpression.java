package paren_expression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import expression.Expression;
import expression.ExpressionFactory;

import model.RGBColor;

public class SumExpression extends ParenExpression{
    private static String[] myCommands = { "sum"};

    SumExpression(ArrayList<Expression> operands)
    {
        myOperands = operands;
    }


    public List<String> getCommand() 
    {
        
        return Arrays.asList(myCommands);
    }


    public int getNumberOfOperands() 
    {
        return INFINITE_OPERANDS;
    }


    public Expression makeNewExpression(ArrayList<Expression> operands) 
    {
        
        return new SumExpression(operands);
    }


    public RGBColor evaluate() 
    {
            List<RGBColor> results = evaluateOperands();
            double redSum = 0.0;
            double greenSum = 0.0;
            double blueSum = 0.0;
            for(RGBColor operand: results)
            {
                redSum += operand.getRed();
                greenSum += operand.getBlue();
                blueSum +=operand.getGreen();
                
            }
            return new RGBColor(redSum, greenSum, blueSum);
                    
        
    }
    
    private SumExpression(){}
    
    public static ExpressionFactory getFactory()
    {
        return new ExpressionFactory(new SumExpression());
    }
}
