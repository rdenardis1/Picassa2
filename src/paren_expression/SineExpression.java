package paren_expression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import expression.Expression;
import expression.ExpressionFactory;

import model.RGBColor;

public class SineExpression extends ParenExpression{
    
    private static String[] myCommands = { "sin"};

	SineExpression(ArrayList<Expression> operands)
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
		
		return new SineExpression(operands);
	}


	public RGBColor evaluate() 
	{
	    List<RGBColor> results = evaluateOperands();
        RGBColor operand1 = results.get(0);
        
		
		return new RGBColor(Math.sin(operand1.getRed()), Math.sin(operand1.getGreen()), 
				Math.sin(operand1.getBlue()));
		
	}
	
	private SineExpression(){}
	
	public static ExpressionFactory getFactory()
	{
		return new ExpressionFactory(new SineExpression());
	}

}
