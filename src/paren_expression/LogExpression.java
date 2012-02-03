package paren_expression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import expression.Expression;
import expression.ExpressionFactory;


import model.RGBColor;

public class LogExpression extends ParenExpression{
    
    private static String[] myCommands = { "log"};
	

	LogExpression(ArrayList<Expression> operands)
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
		
		return new LogExpression(operands);
	}


	public RGBColor evaluate() 
	{
	    List<RGBColor> results = evaluateOperands();
        RGBColor operand1 = results.get(0);
        
		
		return new RGBColor(Math.log(operand1.getRed()), Math.log(operand1.getGreen()), 
				Math.log(operand1.getBlue()));
		
	}
	
	private LogExpression(){}
	
	public static ExpressionFactory getFactory()
	{
		return new ExpressionFactory(new LogExpression());
	}

}
