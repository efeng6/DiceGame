import java.util.ArrayList;

public class Die
{
    /** Instance Variables **/
    private int sides;
    private DieGameView view;

    private int[] rolls;

    /** Constructors **/

    public Die(int numSides, DieGameView view) {
        sides = numSides;
        this.view = view;

    }

    /** Methods **/
    public int getSides() {
        return sides;
    }

    /**
     * Returns a random int between 1 and
     * the number of sides on the Die
     */
    public int roll() {
        int num = (int)(Math.random()*sides) + 1;
        return num;
    }

    /**
     * Rolls the dice the numRolls times
     * and returns the max value of the rolls
     */
    public int getMaxRoll(int numRolls) {
        int max = Integer.MIN_VALUE;
        rolls = new int[numRolls];
        for(int i=0; i<numRolls; i++) {
            int num = roll();
            rolls[i] = num;
            max = Math.max(max, num);
        }

        return max;
    }

    public int[] getRolls()
    {
        return rolls;
    }

    /**
     * TODO: Write a method that rolls the
     * die numRolls times and returns the
     * min value of the rolls
     */
    public int getMinRoll(int numRolls)
    {
        int min = Integer.MAX_VALUE;
        for(int i=0; i<numRolls; i++)
        {
            int num = roll();
            min = Math.min(min, num);
        }

        return min;
    }


    public String toString() {
        return "These are " + sides + " sided dice.";
    }
}