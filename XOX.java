import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class XOX implements ActionListener {

    Random random = new Random();
    JFrame frame = new JFrame("XOX");
    JPanel titlePanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JLabel textField = new JLabel();
    JButton[] buttons = new JButton[9];
    boolean player1Turn;
    JButton playButton = new JButton();
    boolean gameOver = false;
    int turn = 0;

    XOX() {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(600, 700);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textField.setBackground(new Color(6, 40, 61));
        textField.setForeground(new Color(71, 181, 255));
        textField.setFont(new Font("Agency FB", Font.BOLD, 80));

        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setText("X O X");
        textField.setOpaque(true);

        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBounds(0, 0, 600, 100);
        titlePanel.add(textField);
        playButton.setFont(new Font("Agency FB", Font.BOLD, 50));
        playButton.addActionListener(this);
        playButton.setSize(100, 100);
        playButton.setBackground(new Color(71, 181, 255));
        playButton.setForeground(new Color(6, 40, 61));
        playButton.setText("Play");
        playButton.setFocusable(false);
        titlePanel.add(playButton, BorderLayout.EAST);

        buttonPanel.setLayout(new GridLayout(3, 3));
        buttonPanel.setBackground(new Color(37, 109, 133, 255));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttonPanel.add(buttons[i]);
            buttons[i].setFont(new Font("Agency FB", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
            buttons[i].setBackground(new Color(37, 109, 133, 255));
            buttons[i].setForeground(new Color(223, 246, 255));
            buttons[i].setEnabled(false);
        }

        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(buttonPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playButton) {
            if (gameOver) {
                cleanGrid();
                gameOver = false;
            }
            firstTurn();
            for (int i = 0; i < 9; i++) {
                buttons[i].setEnabled(true);
            }
            playButton.setVisible(false);
        }

        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i]) {
                if (player1Turn) {
                    if (buttons[i].getText().equals("")) {
                        buttons[i].setText("X");
                        player1Turn = false;
                        textField.setText("O Turn");
                        turn++;
                        check("X");
                    }
                } else {
                    if (buttons[i].getText().equals("")) {
                        buttons[i].setText("O");
                        player1Turn = true;
                        textField.setText("X Turn");
                        turn++;
                        check("O");
                    }
                }
            }
        }
    }

    void firstTurn() {

        if (random.nextInt(2) == 0) {
            player1Turn = true;
            textField.setText("X Turn");
        } else {
            player1Turn = false;
            textField.setText("O Turn");
        }
    }

    void check(String s) {
        if (turn == 9 && !gameOver) {
            textField.setText("Draw");
            for (int i = 0; i < 9; i++) {
                buttons[i].setEnabled(false);
            }
            gameOver = true;
            playButton.setText("Play again");
            playButton.setVisible(true);
            turn = 0;
        }

        if ((buttons[0].getText().equals(s)) &&
                (buttons[1].getText().equals(s)) &&
                (buttons[2].getText().equals(s))) {
            gameOver(0, 1, 2);
            textField.setText(s + " won!");
        }

        if ((buttons[3].getText().equals(s)) &&
                (buttons[4].getText().equals(s)) &&
                (buttons[5].getText().equals(s))) {
            gameOver(3, 4, 5);
            textField.setText(s + " won!");
        }

        if ((buttons[6].getText().equals(s)) &&
                (buttons[7].getText().equals(s)) &&
                (buttons[8].getText().equals(s))) {
            gameOver(6, 7, 8);
            textField.setText(s + " won!");
        }

        if ((buttons[0].getText().equals(s)) &&
                (buttons[3].getText().equals(s)) &&
                (buttons[6].getText().equals(s))) {
            gameOver(0, 3, 6);
            textField.setText(s + " won!");
        }

        if ((buttons[1].getText().equals(s)) &&
                (buttons[4].getText().equals(s)) &&
                (buttons[7].getText().equals(s))) {
            gameOver(1, 4, 7);
            textField.setText(s + " won!");
        }

        if ((buttons[2].getText().equals(s)) &&
                (buttons[5].getText().equals(s)) &&
                (buttons[8].getText().equals(s))) {
            gameOver(2, 5, 8);
            textField.setText(s + " won!");
        }

        if ((buttons[0].getText().equals(s)) &&
                (buttons[4].getText().equals(s)) &&
                (buttons[8].getText().equals(s))) {
            gameOver(0, 4, 8);
            textField.setText(s + " won!");
        }

        if ((buttons[2].getText().equals(s)) &&
                (buttons[4].getText().equals(s)) &&
                (buttons[6].getText().equals(s))) {
            gameOver(2, 4, 6);
            textField.setText(s + " won!");
        }
    }

    void gameOver(int a, int b, int c) {

        buttons[a].setBackground(new Color(71, 181, 255));
        buttons[b].setBackground(new Color(71, 181, 255));
        buttons[c].setBackground(new Color(71, 181, 255));

        buttons[a].setForeground(new Color(6, 40, 61));
        buttons[b].setForeground(new Color(6, 40, 61));
        buttons[c].setForeground(new Color(6, 40, 61));

        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }

        gameOver = true;
        playButton.setText("Play again");
        playButton.setVisible(true);

        turn = 0;
    }

    void cleanGrid() {
        for (int i = 0; i < 9; i++) {
            buttons[i].setText("");
            buttons[i].setBackground(new Color(37, 109, 133, 255));
            buttons[i].setForeground(new Color(223, 246, 255));
        }
    }

    public static void main(String[] args) {
        new XOX();
    }
}
