import javax.swing.*;


public class SnakeGame extends JFrame {

    SnakeGame(){
        Board board = new Board();
        add(board);
        pack();
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        SnakeGame snakeGame = new SnakeGame();
    }
}