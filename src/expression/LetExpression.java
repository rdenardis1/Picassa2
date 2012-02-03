package expression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Parser;
import model.ParserException;
import model.RGBColor;

public class LetExpression implements Expression{
    private static final Pattern LET_REGEX =
        Pattern.compile("\\((let)");
    @Override
    public boolean isThisKindOfExpression(String input, int position) 
    {
        Matcher expMatcher =
            LET_REGEX.matcher(input.substring(position));
        boolean value = expMatcher.lookingAt();
        return value;
    }

    @Override
    public Expression parseExpression(Parser parser) 
    {
        Matcher expMatcher = LET_REGEX.matcher(parser.getInput());
        expMatcher.find(parser.getCurrentPosition());
        parser.setCurrentPosition(expMatcher.end());        
        parser.skipWhiteSpace();
        String variableName = parser.getInput().substring(parser.getCurrentPosition()).split("\\s+")[0];
        parser.setCurrentPosition(parser.getCurrentPosition()+variableName.length());
        parser.skipWhiteSpace();
        
        Expression variableExpression = parser.parseExpression();
        parser.addToMap(variableName, variableExpression);        
        Expression expressionTree = parser.parseExpression();
        
        if( !parser.notAtEndOfString() ||parser.currentCharacter()!= ')')
            throw new ParserException("no close paren");
        parser.setCurrentPosition(parser.getCurrentPosition()+1);
        parser.removeFromMap(variableName);
        return expressionTree;
    }

    @Override
    public RGBColor evaluate() 
    {
        return null;
    }
    private LetExpression(){}
    
    public static ExpressionFactory getFactory()
    {
        return new ExpressionFactory(new LetExpression());
    }


}
