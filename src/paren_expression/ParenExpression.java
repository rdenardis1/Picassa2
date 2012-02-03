package paren_expression;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import expression.Expression;

import model.Parser;
import model.ParserException;
import model.RGBColor;

public abstract class ParenExpression implements Expression{
    
	// expression begins with a left paren followed by the command name, 
    // which is a sequence of alphabetic characters
    private static final Pattern EXPRESSION_BEGIN_REGEX =
        Pattern.compile("\\(([a-zA-z\\!-\\%\\*-\\-/]+)");
    protected static final int INFINITE_OPERANDS = -1 ;
    protected ArrayList<Expression> myOperands = new ArrayList<Expression>();

    protected List<RGBColor> evaluateOperands()
    {
        List<RGBColor> result = new ArrayList<RGBColor>(myOperands.size());
        for(Expression exp : myOperands) {
            result.add(exp.evaluate());
        }
        return result;
    }
    /**
     * @return the command associated with the given expression
     */
    public abstract List<String> getCommand();
    
    /**
     * @return the number of operands needed for the expresssion
     */
    public abstract int getNumberOfOperands();
    
    /**
     * @param operandList of expressions 
     * @return new expression with operands contained in operandList
     */
    public abstract Expression makeNewExpression(ArrayList<Expression> operandList);
    
    public boolean isThisKindOfExpression(String input, int position)
    {
    	Matcher expMatcher =
            EXPRESSION_BEGIN_REGEX.matcher(input.substring(position));
        if( expMatcher.lookingAt() )
        {
        	expMatcher = EXPRESSION_BEGIN_REGEX.matcher(input);
            expMatcher.find(position);
            String commandName = expMatcher.group(1);
            
            return getCommand().contains(commandName);
        }
        return false;
    }
    
    public Expression parseExpression (Parser parser)
    {
        Matcher expMatcher = EXPRESSION_BEGIN_REGEX.matcher(parser.getInput());
        expMatcher.find(parser.getCurrentPosition());
        parser.setCurrentPosition(expMatcher.end());        
        
        ArrayList<Expression>  operandList = new ArrayList<Expression>();
        parser.skipWhiteSpace();
        
        while(parser.currentCharacter()!= ')')
        {
        	Expression toAdd = parser.parseExpression();
            parser.skipWhiteSpace();
            
            if(!parser.notAtEndOfString()) throw new ParserException("No close paren");
            
            operandList.add(toAdd);   
        }
        parser.setCurrentPosition(parser.getCurrentPosition()+1);
        
        

        if(operandList.size() == getNumberOfOperands() || (getNumberOfOperands() == INFINITE_OPERANDS && operandList.size()>0))
        	return makeNewExpression(operandList);

        else
        	throw new ParserException("Incorrect number of operands");
        
        
    }
    
    
}
