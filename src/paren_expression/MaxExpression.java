package paren_expression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import expression.Expression;
import expression.ExpressionFactory;


import model.RGBColor;

public class MaxExpression extends ParenExpression {
    
    private static String[] myCommands = { "max"};

    MaxExpression(ArrayList<Expression> operands)
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
        
        return new MaxExpression(operands);
    }


    public RGBColor evaluate() 
    {
            List<RGBColor> results = evaluateOperands();
            RGBColor greatest = results.get(0);
            double value;
            double greatestValue = greatest.getRed()+ greatest.getBlue() + greatest.getRed();
            for(RGBColor operand: results)
            {
                value = operand.getRed() + operand.getBlue() + operand.getRed();
                if(greatestValue<value)
                {
                    greatestValue = value;
                    greatest = operand;
                }
                
                
            }
            return greatest;
                    
        
    }
    
    private MaxExpression(){}
    
    public static ExpressionFactory getFactory()
    {
        return new ExpressionFactory(new MaxExpression());
    }

}
