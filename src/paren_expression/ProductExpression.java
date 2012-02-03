package paren_expression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import expression.Expression;
import expression.ExpressionFactory;

import model.RGBColor;

public class ProductExpression extends ParenExpression{
    
    private static String[] myCommands = { "product"};

    ProductExpression(ArrayList<Expression> operands)
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
        
        return new ProductExpression(operands);
    }


    public RGBColor evaluate() 
    {
            List<RGBColor> results = evaluateOperands();
            double redProduct = 1.0;
            double greenProduct = 1.0;
            double blueProduct = 1.0;
            for(RGBColor operand: results)
            {
                redProduct *= operand.getRed();
                greenProduct *= operand.getBlue();
                blueProduct *=operand.getGreen();
                
            }
            return new RGBColor(redProduct, greenProduct, blueProduct);
                    
        
    }
    
    private ProductExpression(){}
    
    public static ExpressionFactory getFactory()
    {
        return new ExpressionFactory(new ProductExpression());
    }
    

}
