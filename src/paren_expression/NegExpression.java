package paren_expression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import expression.Expression;
import expression.ExpressionFactory;


import model.RGBColor;

public class NegExpression extends ParenExpression {
    
    private static String[] myCommands = { "neg", "!"};	

	
	NegExpression(ArrayList<Expression> operands)
	{
		myOperands = operands;
	}


	public List<String> getCommand() 
	{
		return Arrays.asList(myCommands);
	}


	public int getNumberOfOperands() 
	{
		return 1;
	}


	public Expression makeNewExpression(ArrayList<Expression> operands) 
	{
		
		return new NegExpression(operands);
	}


	public RGBColor evaluate() 
	{
		
		
    	    List<RGBColor> results = evaluateOperands();
            RGBColor operand1 = results.get(0);
            
			
	        return new RGBColor(-operand1.getRed(), - operand1.getGreen(), - operand1.getBlue());
		
	}
	
	private NegExpression(){}
	
	public static ExpressionFactory getFactory()
	{
		return new ExpressionFactory(new NegExpression());
	}
}
