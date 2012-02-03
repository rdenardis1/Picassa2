package paren_expression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import expression.Expression;
import expression.ExpressionFactory;


import model.RGBColor;

public class AverageExpression extends ParenExpression {
    
    private static String[] myCommands = { "average"};

    AverageExpression(ArrayList<Expression> operands)
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
        
        return new AverageExpression(operands);
    }


    public RGBColor evaluate() 
    {
            List<RGBColor> results = evaluateOperands();
            double redProduct = 0.0;
            double greenProduct = 0.0;
            double blueProduct = 0.0;
            for(RGBColor operand: results)
            {
                redProduct += operand.getRed();
                greenProduct += operand.getBlue();
                blueProduct +=operand.getGreen();
                
            }
            
            return new RGBColor(redProduct/results.size(), greenProduct/results.size(), blueProduct/results.size());
                    
        
    }
    
    private AverageExpression(){}
    
    public static ExpressionFactory getFactory()
    {
        return new ExpressionFactory(new AverageExpression());
    }

}
