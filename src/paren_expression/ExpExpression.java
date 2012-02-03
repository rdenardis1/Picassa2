package paren_expression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import expression.Expression;
import expression.ExpressionFactory;


import model.RGBColor;

public class ExpExpression extends ParenExpression {
    
    private static String[] myCommands = { "exp", "^"};


	
	ExpExpression(ArrayList<Expression> operands)
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
		
		return new ExpExpression(operands);
	}


	public RGBColor evaluate() 
	{
		
		
    	    List<RGBColor> results = evaluateOperands();
            RGBColor operand1 = results.get(0);
            RGBColor operand2 = results.get(1);
			return new RGBColor(Math.pow(operand1.getRed(),operand2.getRed()),
					Math.pow(operand1.getGreen(),operand2.getGreen()),
					Math.pow(operand1.getBlue(), operand2.getBlue()));
		
	}
	
	private ExpExpression(){}
	
	public static ExpressionFactory getFactory()
	{
		return new ExpressionFactory(new ExpExpression());
	}
}
