package paren_expression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import expression.Expression;
import expression.ExpressionFactory;


import model.RGBColor;

public class ClampExpression extends ParenExpression{
	
    private static String[] myCommands = { "clamp"};

	ClampExpression(ArrayList<Expression> operands)
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
		
		return new ClampExpression(operands);
	}


	public RGBColor evaluate() 
	{
	    List<RGBColor> results = evaluateOperands();
        RGBColor operand1 = results.get(0);
		operand1.clamp();
		return operand1;
		
	}
	
	private ClampExpression(){}
	
	public static ExpressionFactory getFactory()
	{
		return new ExpressionFactory(new ClampExpression());
	}
}
