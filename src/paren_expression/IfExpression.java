package paren_expression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import expression.Expression;
import expression.ExpressionFactory;


import model.RGBColor;

public class IfExpression extends ParenExpression {
    
    
    private static String[] myCommands = { "if"};

    IfExpression(ArrayList<Expression> operands)
    {
        myOperands = operands;
    }


    public List<String> getCommand() 
    {
        
        return Arrays.asList(myCommands);
    }


    public int getNumberOfOperands() 
    {
        return 3;
    }


    public Expression makeNewExpression(ArrayList<Expression> operands) 
    {
        
        return new IfExpression(operands);
    }


    public RGBColor evaluate() 
    {
        List<RGBColor> results = evaluateOperands();
        RGBColor condition = results.get(0);
        RGBColor branch1 = results.get(1);
        RGBColor branch2 = results.get(2);
        
        double averageOfCondition = (condition.getRed() + condition.getBlue() + condition.getGreen())/3.0;
        
        if( averageOfCondition > 0)
            return branch1;
        return branch2;
        
    }
    
    private IfExpression(){}
    
    public static ExpressionFactory getFactory()
    {
        return new ExpressionFactory(new IfExpression());
    }

}
