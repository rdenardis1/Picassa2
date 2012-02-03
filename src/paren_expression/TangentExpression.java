package paren_expression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import expression.Expression;
import expression.ExpressionFactory;

import model.RGBColor;

public class TangentExpression extends ParenExpression{
    
    private static String[] myCommands = { "tan"};

	TangentExpression(ArrayList<Expression> operands)
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
		
		return new TangentExpression(operands);
	}


	public RGBColor evaluate() 
	{
	    List<RGBColor> results = evaluateOperands();
        RGBColor operand1 = results.get(0);
        
		
		return new RGBColor(Math.tan(operand1.getRed()), Math.tan(operand1.getGreen()), 
				Math.tan(operand1.getBlue()));
		
	}
	
	private TangentExpression(){}
	
	public static ExpressionFactory getFactory()
	{
		return new ExpressionFactory(new TangentExpression());
	}

}
