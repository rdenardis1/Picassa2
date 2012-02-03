package paren_expression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import expression.Expression;
import expression.ExpressionFactory;

import model.RGBColor;
import model.util.PerlinNoise;

public class PerlinColorExpression extends ParenExpression {
    
    private static String[] myCommands = { "perlinColor"};
	PerlinColorExpression(ArrayList<Expression> operands)
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
		
		return new PerlinColorExpression(operands);
	}


	public RGBColor evaluate() 
	{
	    List<RGBColor> results = evaluateOperands();
        RGBColor operand1 = results.get(0);
        RGBColor operand2 = results.get(1);
		
		return PerlinNoise.colorNoise(operand1, operand2);
		
	}
	
	private PerlinColorExpression(){}
	
	public static ExpressionFactory getFactory()
	{
		return new ExpressionFactory(new PerlinColorExpression());
	}

}
