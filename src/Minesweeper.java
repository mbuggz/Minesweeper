import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

import game.Coord;
import game.Game;
import game.Ranges;
import enums.Box;

import javax.swing.*;

public class Minesweeper extends JFrame {
    private JPanel panel;
    private JLabel label;
    private final Game game;
    private final int IMAGE_SIZE = 50;

    public static void main(String[] args) {
        new Minesweeper();
    }

    private Minesweeper() {
        int BOMBS = 10;
        int ROWS = 9;
        int COLS = 9;
        game = new Game(COLS, ROWS, BOMBS);
        game.start();
        setImages();
        initLabel();
        initPanel();
        initFrame();
    }

    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Minesweeper");
        setResizable(false);
        setVisible(true);
        setIconImage(getImage("icon"));
        pack();
        setLocationRelativeTo(null);
    }

    private void initPanel() {
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (Coord coord : Ranges.getAllCoords()) {
                    g.drawImage(
                        (Image) game.getBox(coord).image,
                     coord.getX() * IMAGE_SIZE,
                     coord.getY() * IMAGE_SIZE,
                this
                    );
                }
            }
        };

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / IMAGE_SIZE;
                int y = e.getY() / IMAGE_SIZE;
                Coord coord = new Coord(x, y);

                if (e.getButton() == MouseEvent.BUTTON1) {
                    game.pressLeftButton(coord);
                }
                if (e.getButton() == MouseEvent.BUTTON3) {
                    game.pressRightButton(coord);
                }
                if (e.getButton() == MouseEvent.BUTTON2) {
                    game.start();
                }
                label.setText(getMessage());
                panel.repaint();
            }
        });

        panel.setPreferredSize(new Dimension(
            Ranges.getSize().getX() * IMAGE_SIZE,
            Ranges.getSize().getY() * IMAGE_SIZE
        ));
        add(panel);
    }

    private void initLabel() {
        label = new JLabel("Welcome!");
        add(label, BorderLayout.SOUTH);
    }

    private Image getImage(String name) {
        String filename = "img/" + name + ".png";
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(filename)));
        return icon.getImage();
    }

    private void setImages() {
        for(Box box : Box.values()) {
        box.image = getImage(box.name().toLowerCase());
        }
    }

    private String getMessage() {
        switch (game.getGameState()) {
            case PLAYED:
                return "Think twice";
            case BOMBED:
                return "You lose";
            case WINNER:
                return "Congratulation!";
            default:
                return "Welcome!";
        }
    }
}
