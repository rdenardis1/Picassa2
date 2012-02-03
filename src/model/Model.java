package model;

import java.awt.Dimension;

import expression.Expression;


/**
 * Evaluate an expression for each pixel in a image.
 * 
 * @author Robert C Duvall
 */
public class Model
{
    public static final double DOMAIN_MIN = -1;
    public static final double DOMAIN_MAX = 1;
    public static final int NUM_FRAMES = 50;

    private static double evalX;
    private static double evalY;
    private static double myCurrentTime = 0;


    /**
     * Advance to the next frame in the animation.
     */
    public void reset ()
    {
        myCurrentTime = 0;
    }


    /**
     * Advance to the next frame in the animation.
     */
    public void nextFrame ()
    {
        myCurrentTime += 1.0 / NUM_FRAMES;
    }


    /**
     * Evaluate an expression for each point in the image.
     */
    public Pixmap evaluate (String input, Dimension size)
    {
        Pixmap result = new Pixmap(size);
        // create expression to evaluate just once
        Expression toEval = new Parser().makeExpression(input);
        // evaluate at each pixel
        for (int imageY = 0; imageY < size.height; imageY++)
        {
            evalY = imageToDomainScale(imageY, size.height);
            for (int imageX = 0; imageX < size.width; imageX++)
            {
                evalX = imageToDomainScale(imageX, size.width);

                result.setColor(imageX, imageY,
                        toEval.evaluate().toJavaColor());            
                }
            
        }
        return result;
    }

    public static double getEvalT()
    {
        return myCurrentTime*2 - 1;
    }
    
    public static double getEvalX()
    {
        return evalX;
    }
    
    public static double getEvalY()
    {
        return evalY;
    }

    /**
     * Convert from image space to domain space.
     */
    protected double imageToDomainScale (int value, int bounds)
    {
        double range = DOMAIN_MAX - DOMAIN_MIN;
        return ((double)value / bounds) * range + DOMAIN_MIN;
    }
}
