package paren_expression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import expression.Expression;
import expression.ExpressionFactory;

import model.RGBColor;

public class PlusExpression extends ParenExpression {
    
    private static String[] myCommands = { "plus", "+"};
	

	
	PlusExpression(ArrayList<Expression> operands)
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
		
		return new PlusExpression(operands);
	}



	@Override
	public RGBColor evaluate() 
	{
		
		
    	    List<RGBColor> results = evaluateOperands();
            RGBColor operand1 = results.get(0);
            RGBColor operand2 = results.get(1);
	        return new RGBColor(operand1.getRed() + operand2.getRed(), 
                    operand1.getGreen() + operand2.getGreen(),
                    operand1.getBlue() + operand2.getBlue());
		
	}
	
	private PlusExpression(){}
	
	public static ExpressionFactory getFactory()
	{
		return new ExpressionFactory(new PlusExpression());
	}
}
		
		
		
		
		
