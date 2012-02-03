package paren_expression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import expression.Expression;
import expression.ExpressionFactory;


import model.RGBColor;

public class MinExpression extends ParenExpression {
    
    private static String[] myCommands = { "min"};

    MinExpression(ArrayList<Expression> operands)
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
        
        return new MinExpression(operands);
    }


    public RGBColor evaluate() 
    {
        List<RGBColor> results = evaluateOperands();
        RGBColor least = results.get(0);
        double value;
        double leastValue = least.getRed()+ least.getBlue() + least.getRed();
        for(RGBColor operand: results)
        {
            value = operand.getRed() + operand.getBlue() + operand.getRed();
            if(leastValue > value)
            {
                leastValue = value;
                least = operand;
            }
            
            
        }
        return least;
                    
        
    }
    
    private MinExpression(){}
    
    public static ExpressionFactory getFactory()
    {
        return new ExpressionFactory(new MinExpression());
    }

}
