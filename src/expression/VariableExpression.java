package expression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Model;
import model.Parser;
import model.RGBColor;

public class VariableExpression implements Expression {
	private static final Pattern VARIABLE_REGEX =Pattern.compile("[xyt]");
	
	private String myVariable;
	
	public VariableExpression(String variable)
	{
		myVariable = variable;
	}
	
	

	public boolean isThisKindOfExpression(String input, int position)
	{
		Matcher variableMatcher =
    		VARIABLE_REGEX.matcher(input.substring(position));
    		return variableMatcher.lookingAt();
	}
	
	public Expression parseExpression(Parser parser)
	{
		Matcher variableMatcher = VARIABLE_REGEX.matcher(parser.getInput());
    	variableMatcher.find(parser.getCurrentPosition());
    	String variableName = parser.getInput().substring(variableMatcher.start(), variableMatcher.end() );
    	parser.setCurrentPosition(variableMatcher.end());
    	return new VariableExpression(variableName);
	}
	
	public RGBColor evaluate()
	{
		if(myVariable.equals("x"))
    		return new RGBColor(Model.getEvalX());
		else if(myVariable.equals("t"))
		    return new RGBColor(Model.getEvalT());
    	else
    		return new RGBColor(Model.getEvalY());
	}
	
	private VariableExpression(){}
	
	public static ExpressionFactory getFactory()
	{
		return new ExpressionFactory(new VariableExpression());
	}



}
