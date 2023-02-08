import java.util.Scanner;

public class DieGame {
    //Defining/ initializing all multi-method variables
    public static int player1Points = 0;
    public static int player2Points = 0;

    private static int dieSides = 6;
    public static String winner;

    private DieGameView view;
    private static Die player1Die;
    private static Die player2Die;


    public static int player1RollsLeft = 10;

    public static int player2RollsLeft = 10;

    private static int player1Rolls;
    private static int player2Rolls;

    public static final int firstRollX = 200;
    public static final int firstRollY = 300;

    public static final int p1p2Distance = 100;

    public final int incrementLength = 50;

    public static int[] p1rollarrayy;
    public static int[] p2rollarrayy;


    //constructor - empty
    public DieGame()
    {
        view = new DieGameView(this);
        player1Die = new Die(dieSides, view);
        player2Die = new Die(dieSides, view);
        winner = "NULL";
    }

    //prints the instructions for the game at the beginning of the game
    public static void printInstructions(String sides)
    {
        System.out.println("This is   What's your Wager! ");
        System.out.println("Each player starts with 10 dice");
        System.out.println("Each turn, each player will decide how many dice to roll");
        System.out.println("The player with the highest roll will get points");
        System.out.println("equal to the total number of dice rolled that turn");
        System.out.println("If there is a tie, no one gets points");
        System.out.println("The game is over when one person runs out of dice");
        System.out.println("Any leftover dice will be converted to points");
        System.out.println("Note: " + sides);
        System.out.println();
        System.out.println();
    }



    //actually plays the game
    public void playGame()
    {
        Scanner s = new Scanner(System.in);

        int numSides = player1Die.getSides();

        //Yes, I could have done this more abstractly, but I needed to hit
        //the required number of dice functions used
        printInstructions(player1Die.toString());

        //The game runs until one player runs out of dice
        while (player1RollsLeft != 0 && player2RollsLeft != 0)
        {
            //Sets the number of dice rolled this turn by this player
            //To more than the allowed number to generate loop
            //Waits until an acceptable number is entered
            player1Rolls = player1RollsLeft + 1;
            while (player1Rolls > player1RollsLeft)
            {
                System.out.println("Player 1, how many dice would you like to roll?");
                player1Rolls = s.nextInt();
            }
            //Same as above
            player2Rolls = player2RollsLeft + 1;
            while (player2Rolls > player2RollsLeft)
            {
                System.out.println("Player 2, how many dice would you like to roll?");
                player2Rolls = s.nextInt();
            }

            //Reducing the number of rolls each player has left
            player1RollsLeft -= player1Rolls;
            player2RollsLeft -= player2Rolls;

            //Winning a round
            //Adds points to the player who won the round

            int p1rolls = player1Die.getMaxRoll(player1Rolls);
            int p2rolls = player2Die.getMaxRoll(player2Rolls);

            p1rollarrayy = player1Die.getRolls();
            p2rollarrayy = player2Die.getRolls();

            if (p1rolls > p2rolls)
            {
                System.out.println("Player 1 wins the round!");
                player1Points += (player1Rolls + player2Rolls);
                System.out.println();
            }
            else if (p1rolls < p2rolls)
            {
                System.out.println("Player 2 wins the round!");
                player2Points += (player1Rolls + player2Rolls);
                System.out.println();
            }
            else
            {
                System.out.println("Tie!");
                System.out.println("No one gets points");
                System.out.println();

            }

            view.repaint();
        }
        addExtraPoints();
        checkWin();
    }
    //Adds the leftover rolls to the person's points
    public static void addExtraPoints()
    {
        if (player1RollsLeft != 0)
        {
            System.out.println("Player 1 has " + player1RollsLeft + " rolls left!");
            System.out.println("Player 1 gains " + player1RollsLeft + " points!");
            player1Points += player1RollsLeft;
        }
        if (player2RollsLeft != 0)
        {
            System.out.println("Player 2 has " + player2RollsLeft + " rolls left!");
            System.out.println("Player 2 gains " + player2RollsLeft + " points!");
            player2Points += player2RollsLeft;
        }
    }

    //Checks which player has won when the game ends
    public static void checkWin()
    {

        if (player1Points > player2Points)
        {
            System.out.println();
            System.out.println("Player 1 wins!");
            System.out.println("Player 1 wins " + player1Points + " to " +
                    player2Points + "!");
            winner = "Player 1";
        }
        else if (player1Points < player2Points)
        {
            System.out.println();
            System.out.println("Player 2 wins!");
            System.out.println("Player 2 wins " + player2Points + " to " +
                    player1Points + "!");
            winner = "Player 2";

        }
        //Does a tiebreaker if it is tied
        else
        {
            System.out.println();
            System.out.println("Tie game!");
            System.out.println("For the tie breaker, dice will be rolled until");
            System.out.println("One player has a higher roll!");
            System.out.println();
            System.out.println();

            int tieBreaker1 = 0;
            int tieBreaker2 = 0;
            while (tieBreaker1 == tieBreaker2)
            {
                tieBreaker1 = player1Die.getMaxRoll(1);
                System.out.println("Player 1 rolled a " + tieBreaker1 + "!");
                tieBreaker2 = player2Die.getMaxRoll(1);
                System.out.println("Player 2 rolled a " + tieBreaker2 + "!");
                System.out.println();
            }
            if(tieBreaker1 > tieBreaker2)
            {
                System.out.println("Player 1 wins!");
                winner = "Player 1";
            }
            else if(tieBreaker1 < tieBreaker2)
            {
                System.out.println("Player 2 wins!");
                winner = "Player 2";
            }
        }

    }
    //Generates current version of game and runs
    public static void main(String[] args)
    {
        DieGame game = new DieGame();
        game.playGame();
    }

}