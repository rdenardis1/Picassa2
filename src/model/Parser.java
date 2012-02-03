package model;

import java.util.ArrayList;
import java.util.HashMap;

import paren_expression.AbsExpression;
import paren_expression.ArcTangentExpression;
import paren_expression.AverageExpression;
import paren_expression.CeilingExpression;
import paren_expression.ClampExpression;
import paren_expression.ColorExpression;
import paren_expression.ConvertToLuminanceExpression;
import paren_expression.ConvertToRGBExpression;
import paren_expression.CosineExpression;
import paren_expression.DivExpression;
import paren_expression.ExpExpression;
import paren_expression.FloorExpression;
import paren_expression.IfExpression;
import paren_expression.LogExpression;
import paren_expression.MaxExpression;
import paren_expression.MinExpression;
import paren_expression.MinusExpression;
import paren_expression.ModExpression;
import paren_expression.MulExpression;
import paren_expression.NegExpression;
import paren_expression.PerlinBWExpression;
import paren_expression.PerlinColorExpression;
import paren_expression.PlusExpression;
import paren_expression.ProductExpression;
import paren_expression.RandomExpression;
import paren_expression.SineExpression;
import paren_expression.SumExpression;
import paren_expression.TangentExpression;
import paren_expression.WrapExpression;

import expression.Expression;
import expression.ExpressionFactory;
import expression.LetExpression;
import expression.NumberExpression;
import expression.VariableExpression;


/**
 * Parses a string into an expression tree based on rules for arithmetic.
 * 
 * Due to the nature of the language being parsed, a recursive descent parser 
 * is used 
 *   http://en.wikipedia.org/wiki/Recursive_descent_parser
 *   
 * @author former student solution
 * @author Robert C. Duvall (added comments, exceptions, some functions)
 */
public class Parser
{
    // state of the parser
    private int myCurrentPosition;
    private String myInput;
    private HashMap<String, Expression> myVariableToExpression;
    
    public Parser()
    {
        myVariableToExpression = new HashMap<String, Expression>();
    }


    /**
     * Converts given string into expression tree.
     * 
     * @param input expression given in the language to be parsed
     * @return expression tree representing the given formula
     */
    public Expression makeExpression (String input)
    {
        myInput = input;
        myCurrentPosition = 0;
        Expression result = parseExpression();
        skipWhiteSpace();
        if (notAtEndOfString())
        {
            throw new ParserException("Unexpected characters at end of the string: " +
                                      myInput.substring(myCurrentPosition),
                                      ParserException.Type.EXTRA_CHARACTERS);
        }
        return result;
    }



    public Expression parseExpression ()
    {        
    	ArrayList<ExpressionFactory> expressionKinds = new ArrayList<ExpressionFactory>();
        expressionKinds.add(NumberExpression.getFactory());
        expressionKinds.add(VariableExpression.getFactory());
        expressionKinds.add(PlusExpression.getFactory());
        expressionKinds.add(MinusExpression.getFactory());
        expressionKinds.add(DivExpression.getFactory());
        expressionKinds.add(ModExpression.getFactory());
        expressionKinds.add(ColorExpression.getFactory());
        expressionKinds.add(ExpExpression.getFactory());
        expressionKinds.add(NegExpression.getFactory());
        expressionKinds.add(MulExpression.getFactory());
        expressionKinds.add(RandomExpression.getFactory());
        expressionKinds.add(FloorExpression.getFactory());
        expressionKinds.add(CeilingExpression.getFactory());
        expressionKinds.add(AbsExpression.getFactory());
        expressionKinds.add(ClampExpression.getFactory());
        expressionKinds.add(WrapExpression.getFactory());
        expressionKinds.add(SineExpression.getFactory());
        expressionKinds.add(CosineExpression.getFactory());
        expressionKinds.add(TangentExpression.getFactory());
        expressionKinds.add(ArcTangentExpression.getFactory());
        expressionKinds.add(LogExpression.getFactory());
        expressionKinds.add(ConvertToLuminanceExpression.getFactory());
        expressionKinds.add(ConvertToRGBExpression.getFactory());
        expressionKinds.add(PerlinColorExpression.getFactory());
        expressionKinds.add(PerlinBWExpression.getFactory());
        expressionKinds.add(LetExpression.getFactory());
        expressionKinds.add(SumExpression.getFactory());
        expressionKinds.add(ProductExpression.getFactory());
        expressionKinds.add(AverageExpression.getFactory());
        expressionKinds.add(MinExpression.getFactory());
        expressionKinds.add(MaxExpression.getFactory());
        expressionKinds.add(IfExpression.getFactory());

        
        skipWhiteSpace();
        for(String variableName : myVariableToExpression.keySet())
        {
            if(myInput.startsWith(variableName,myCurrentPosition))
            {
                myCurrentPosition+=variableName.length();
                return myVariableToExpression.get(variableName);
            }
               
        }
        for( ExpressionFactory expressionKind: expressionKinds)
        {
            
        	if(expressionKind.isThisKindOfExpression(myInput, myCurrentPosition))
        	{
        	    return expressionKind.parseExpression(this);
        	}	
        }
        throw new ParserException("Did not recognize expression");
    }

    public void addToMap(String variableName, Expression expression)
    {
        myVariableToExpression.put(variableName, expression);
    }

    public void removeFromMap(String variableName)
    {
        myVariableToExpression.remove(variableName);
    }

    public void skipWhiteSpace ()
    {
        while (notAtEndOfString() && Character.isWhitespace(currentCharacter()))
        {
            myCurrentPosition++;
        }
    }


    public char currentCharacter ()
    {
        return myInput.charAt(myCurrentPosition);
    }

    public boolean notAtEndOfString ()
    {
        return myCurrentPosition < myInput.length();
    }
    public String getInput()
    {
    	return myInput;
    }
    
    public int getCurrentPosition()
    {
    	return myCurrentPosition;
    }
    
    public void setCurrentPosition(int position)
    {
    	myCurrentPosition = position;
    }
    
}
