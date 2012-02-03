package paren_expression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import expression.Expression;
import expression.ExpressionFactory;


import model.RGBColor;
import model.util.ColorModel;

public class ConvertToRGBExpression extends ParenExpression {
    
    private static String[] myCommands = { "yCrCbtoRGB"};
	
	ConvertToRGBExpression(ArrayList<Expression> operands)
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
		
		return new ConvertToRGBExpression(operands);
	}


	public RGBColor evaluate() 
	{
	    List<RGBColor> results = evaluateOperands();
        RGBColor operand1 = results.get(0);
		
		return ColorModel.ycrcb2rgb(operand1);
		
	}
	
	private ConvertToRGBExpression(){}
	
	public static ExpressionFactory getFactory()
	{
		return new ExpressionFactory(new ConvertToRGBExpression());
	}
}
