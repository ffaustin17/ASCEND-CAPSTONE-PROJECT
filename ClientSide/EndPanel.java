package ClientSide;

import CoreGame.GameController;
import CoreGame.GameMap;
import CoreGame.Player;

import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class EndPanel extends JPanel {
    private double scaleF = 1.5;

    private BufferedImage background_win;
    private BufferedImage background_lose;
    private BufferedImage logoutButton;
    private JButton logout;
    private boolean win;

    int newWidthB;
    int newHeightB;
    int newWidthL;
    int newHeightL;

    public EndPanel(EndControl ec) throws IOException {

        //int win = 2;

        this.setPreferredSize(new Dimension(1080,624));
        this.setLayout(new GridBagLayout());

//        // get original images
//        BufferedImage original_background_lose = ImageIO.read(getClass().getResourceAsStream("/assets/background_lose.png"));
//        BufferedImage original_background_win = ImageIO.read(getClass().getResourceAsStream("/assets/background_win.png"));
//        BufferedImage original_logout_image = ImageIO.read(getClass().getResourceAsStream("/assets/button_logout_2.png"));
//
//        // get re-scaled width and height
//        newWidthB = (int) (original_background_lose.getWidth() * scaleF);
//        newHeightB = (int) (original_background_lose.getHeight() * scaleF);
//        newWidthL = (int) (original_logout_image.getWidth() * scaleF);
//        newHeightL = (int) (original_logout_image.getHeight() * scaleF);

        // resizing
        background_lose = ImageIO.read(getClass().getResourceAsStream("/assets/background_lose.png"));
        background_win = ImageIO.read(getClass().getResourceAsStream("/assets/background_win.png"));
        logoutButton = ImageIO.read(getClass().getResourceAsStream("/assets/button_logout_2.png"));

        // get re-scaled width and height
        newWidthB = (int) (background_lose.getWidth() * scaleF);
        newHeightB = (int) (background_lose.getHeight() * scaleF);
        newWidthL = (int) (logoutButton.getWidth() * scaleF);
        newHeightL = (int) (logoutButton.getHeight() * scaleF);

        // draw background based on win or lose
//        if (gc.getPlayer().hasReachedGoal()) {
//            // draw BACKGROUND WIN!!!
//            Graphics2D g2b = background_win.createGraphics();
//            g2b.drawImage(original_background_win, 0, 0, newWidthB, newHeightB, null);
//            g2b.dispose();
//        }
//        else {
//            // draw BACKGROUND WIN!!!
//            Graphics2D g2b = background_lose.createGraphics();
//            g2b.drawImage(original_background_lose, 0, 0, newWidthB, newHeightB, null);
//            g2b.dispose();
//        }

//        // draw LOGOUT
//        Graphics2D g2l = logoutButton.createGraphics();
//        g2l.drawImage(logoutButton, 0, 0, newWidthL, newHeightL, null);
//        g2l.dispose();

        // ---------------------------------------------------

        logout = createCustomButton(logoutButton);

        logout.setActionCommand("Logout");
        logout.addActionListener(ec);

        this.add(logout);
    }

    private JButton createCustomButton(BufferedImage buttonImage) {
        // Create a new button with an icon
        JButton button = new JButton(new ImageIcon(buttonImage));
        button.setOpaque(false); // Make the button background transparent
        button.setContentAreaFilled(false); // Don't fill the button area with a color
        button.setBorderPainted(false); // Remove the border
        return button;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw background (fill the panel with the background image)
        if(win) {
            g.drawImage(background_win, 0, 0, newWidthB, newHeightB, this);
        }
        else {
            g.drawImage(background_lose, 0, 0, newWidthB, newHeightB, this);
        }

        // Set button positions after the panel is rendered
        if (logout != null) {
            // Position logout button below the join button
            int logoutX = (getWidth() - logout.getWidth()) / 2;
            int logoutY = (getWidth() - logout.getWidth()) / 2;
            logout.setBounds(logoutX, logoutY, newWidthL, newHeightL);

        }

        //g.drawImage(logoutButton, 0, 0, newWidthL, newHeightL, null);
    }

    public void setScreen(Player player){
        if(player.hasReachedGoal()){
            win = true;
        }
        else{
            win = false;
        }
    }
}
