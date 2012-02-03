package expression;

import model.Parser;
import model.RGBColor;

/**
 * An Expression represents a mathematical expression as a tree.
 * 
 * In this format, the internal nodes represent mathematical 
 * functions and the leaves represent constant values.
 *
 * @author former student solution
 * @author Robert C. Duvall (added comments, some code)
 */
public interface Expression
{
    
	/**
	 * Determine whether or not input is this type of expression
	 * @param input string
	 * @param position of starting point in string
	 * @return true if the input is this kind of expression
	 */
	public abstract boolean isThisKindOfExpression(String input, int position);
    
	
	/**
	 * Parse the expression in the parser
	 * @param parser
	 * @return the expression tree
	 */
	public abstract Expression parseExpression(Parser parser);
    
	
	/**
	 * @param evalX the x value of the pixel
	 * @param evalY the y value of the pixel
	 * @return RGBColor for the pixel based on the expression
	 */
	public abstract RGBColor evaluate();

}
