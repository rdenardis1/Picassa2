package paren_expression;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import expression.Expression;
import expression.ExpressionFactory;


import model.RGBColor;

public class ArcTangentExpression extends ParenExpression {
    private static String[] myCommands = { "atan"};

	ArcTangentExpression(ArrayList<Expression> operands)
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
		
		return new ArcTangentExpression(operands);
	}


	public RGBColor evaluate() 
	{
		
	    List<RGBColor> results = evaluateOperands();
        RGBColor operand1 = results.get(0);
		
		return new RGBColor(Math.atan(operand1.getRed()), Math.atan(operand1.getGreen()), 
				Math.atan(operand1.getBlue()));
		
	}
	
	private ArcTangentExpression(){}
	
	public static ExpressionFactory getFactory()
	{
		return new ExpressionFactory(new ArcTangentExpression());
	}

}
