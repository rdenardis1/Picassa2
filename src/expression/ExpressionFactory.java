package expression;

import model.Parser;

public class ExpressionFactory {
	Expression myExpression;
	
	public ExpressionFactory(Expression expression)
	{
		myExpression = expression;
	}
	
	public boolean isThisKindOfExpression(String input, int position)
	{
		return myExpression.isThisKindOfExpression(input, position);
	}
	
	public Expression parseExpression(Parser parser)
	{
		return myExpression.parseExpression(parser);
	}
}
