package expression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Parser;
import model.RGBColor;

public class NumberExpression implements Expression{
	
	// double is made up of an optional negative sign, followed by a sequence 
    // of one or more digits, optionally followed by a period, then possibly 
    // another sequence of digits
	private static final Pattern DOUBLE_REGEX =
        Pattern.compile("(\\-?[0-9]+(\\.[0-9]+)?)|(\\-?+(\\.[0-9]+))");
	private RGBColor myValue;
    
	
	public NumberExpression(RGBColor value)
	{
		myValue = value;
	}
	
	public boolean isThisKindOfExpression(String input, int position)
	{
		Matcher doubleMatcher =
            DOUBLE_REGEX.matcher(input.substring(position));
        return doubleMatcher.lookingAt();
	}
	
	public Expression parseExpression(Parser parser)
	{
		Matcher doubleMatcher = DOUBLE_REGEX.matcher(parser.getInput());
        doubleMatcher.find(parser.getCurrentPosition());
        String numberMatch =
            (parser.getInput()).substring(doubleMatcher.start(), doubleMatcher.end());
        parser.setCurrentPosition(doubleMatcher.end());
        // this represents the color gray of the given intensity
        double value = Double.parseDouble(numberMatch);
        RGBColor gray = new RGBColor(value);
        return new NumberExpression(gray);
	}
	
	public RGBColor evaluate ()
    {
		return myValue;
    }
	
	private NumberExpression(){}
	
	public static ExpressionFactory getFactory()
	{
		return new ExpressionFactory(new NumberExpression());
	}

	public String toString()
	{
		return myValue.toString();
	}
	
}
