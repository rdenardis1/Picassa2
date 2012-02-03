package paren_expression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import expression.Expression;
import expression.ExpressionFactory;

import model.RGBColor;

public class RandomExpression extends ParenExpression {
    
    private static String[] myCommands = {"random" };
    

	private Random myRandom;
	
	RandomExpression(ArrayList<Expression> operands)
	{
	    
	    myOperands = operands;
		myRandom = new Random();
	}


	public List<String> getCommand() 
	{
		return Arrays.asList(myCommands);
	}


	public int getNumberOfOperands() 
	{
		return 0;
	}


	public Expression makeNewExpression(ArrayList<Expression> operands) 
	{
		
		return new RandomExpression(operands);
	}


	public RGBColor evaluate() 
	{
		
		return new RGBColor(getRandomColor(), getRandomColor(), getRandomColor());
		
	}
	
	private double getRandomColor()
	{
		return myRandom.nextDouble()*2 -1;
	}
	
	private RandomExpression(){}
	
	public static ExpressionFactory getFactory()
	{
		return new ExpressionFactory(new RandomExpression());
	}

}
