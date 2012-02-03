package paren_expression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import expression.Expression;
import expression.ExpressionFactory;


import model.RGBColor;

public class ColorExpression extends ParenExpression {
	
    private static String[] myCommands = { "color"};
	
	ColorExpression(ArrayList<Expression> operands)
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
		
		return new ColorExpression(operands);
	}


	public RGBColor evaluate() 
	{

	        List<RGBColor> results = evaluateOperands();
	        RGBColor operand1 = results.get(0);
	        RGBColor operand2 = results.get(1);
	        RGBColor operand3 = results.get(2);
			
			return new RGBColor( operand1.getRed(), operand2.getGreen(), operand3.getBlue());
	
		
	}
	
	private ColorExpression(){}
	
	public static ExpressionFactory getFactory()
	{
		return new ExpressionFactory(new ColorExpression());
	}
}
