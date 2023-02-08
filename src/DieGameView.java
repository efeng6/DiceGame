import javax.swing.*;
import java.awt.*;

public class DieGameView extends JFrame {

    public final int WINDOW_WIDTH = 1000;
    public final int WINDOW_HEIGHT = 800;

    public final int DIE_WIDTH = 50;
    public final int DIE_HEIGHT = 50;

    public final int RollsLeftDisplayX = 50;
    public final int p1RollsLeftDisplayY = 100;
    public final int p2RollsLeftDisplayY = WINDOW_HEIGHT - 100;
    private DieGame game;

    private Image[] images;
    private Image background;

    private Image blankbackground;

    public DieGameView(DieGame d)
    {
        images = new Image[6];
        for (int i = 0; i < images.length; i++)
        {
            images[i] = new ImageIcon("Resources/" + (i + 1) + "side.png").getImage();
        }
        background = new ImageIcon("Resources/Background.png").getImage();
        blankbackground = new ImageIcon("Resources/Background.jpeg").getImage();
        game = d;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Die Game");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
        createBufferStrategy(2);

    }

    public void paint(Graphics g)
    {
        g.drawImage(background, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
        drawDieArray(DieGame.p1rollarrayy, DieGame.firstRollX, DieGame.firstRollY, g);
        drawDieArray(DieGame.p2rollarrayy, DieGame.firstRollX, DieGame.firstRollY + DieGame.p1p2Distance, g);

        for (int i = 0; i < DieGame.player1RollsLeft; i++)
        {
            drawDie(6, RollsLeftDisplayX + i * 50, p1RollsLeftDisplayY, g);
        }
        for (int i = 0; i < DieGame.player2RollsLeft; i++)
        {
            drawDie(6, RollsLeftDisplayX + i * 50, p2RollsLeftDisplayY, g);
        }
        g.setColor(Color.white);
        g.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 50));
        g.drawString("" + DieGame.player1Points,800, 205);
        g.drawString("" + DieGame.player2Points, 800, WINDOW_HEIGHT - 215);

        if (DieGame.winner != "NULL")
        {
            g.drawImage(blankbackground, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
            g.setColor(Color.white);
            g.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 100));
            g.drawString("Winner: " + DieGame.winner,WINDOW_WIDTH / 2 - 400, WINDOW_HEIGHT / 2);
        }
    }
    public void drawDieArray(int[] faces, int x, int y, Graphics g)
    {
        for (int i = 0; i < faces.length; i++)
        {
            drawDie(faces[i], x + game.incrementLength * (i + 1), y, g);
        }
    }
    public void drawDie(int face, int x, int y, Graphics g)
    {
        g.drawImage(images[face - 1], x, y,
                DIE_WIDTH, DIE_HEIGHT, this);
    }


}
