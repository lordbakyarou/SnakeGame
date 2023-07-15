import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Board extends JPanel implements ActionListener {

    int WIDTH = 400;
    int HEIGHT = 400;

    //Max Length of a Snake
    int MAX_DOTS = 1600;
    int DOTS;

    //Max Score
    int maxScore = 0;

    //Initilizing X and Y Arrays
    int x[] = new int[MAX_DOTS];
    int y[] = new int[MAX_DOTS];

    //Declaring apple cordinates

    int apple_x;
    int apple_y;

    //Game images

    Image body, apple, head;

    int DELAY = 100;

    Timer timer;

    //Directions
    boolean left = true;
    boolean right = false;
    boolean up = false;
    boolean down = false;

    //Ingame Vairable to check collision
    boolean ingame = true;

    //Button
    JButton startButton;
    JButton restartButton;
    JLabel text;

    //First Game counter

    Board(){
        TAdapter tAdapter = new TAdapter();
        addKeyListener(tAdapter);
        setFocusable(true);

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);


            setButton();

    }

    Board(int maxScore){
        TAdapter tAdapter = new TAdapter();
        addKeyListener(tAdapter);
        setFocusable(true);
        this.maxScore = maxScore;

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
    }

    public void setButton(){
        startButton = new JButton("Play Me");
        startButton.setOpaque(false);
        startButton.setContentAreaFilled(false);
        startButton.setBorderPainted(false);

        Font buttonFont = new Font("Arial", Font.BOLD, 14);
        startButton.setFont(buttonFont);
        startButton.setForeground(Color.WHITE);

        startButton.setPreferredSize(new Dimension(100,40));

        setLayout(null);
        startButton.setBounds(150,150,100,40);
        add(startButton);

        text = new JLabel("Classic Snake Game");
        text.setForeground(Color.WHITE);
        Font textFont = new Font("Arial", Font.BOLD, 18);
        FontMetrics fontMetrics = getFontMetrics(textFont);

        text.setFont(textFont);
        text.setBounds((WIDTH - fontMetrics.stringWidth(text.getText()))/2,50,200,50);
        add(text);


        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startButton.setVisible(false);
                text.setVisible(false);
                initGame();
                loadImages();
            }
        });
    }


    //Initialize the Snake function
    public void initGame(){
        DOTS = 3;

        x[0] = 50;
        y[0] = 50;

        for(int i = 1;i<DOTS;i++){
            x[i] = x[0]+(10*i);
            y[i] = y[0];
        }

        locateApple();

        timer = new Timer(DELAY, this);
        timer.start();

    }

    //Load Images from resource folder

    public void loadImages(){
        ImageIcon bodyIcon  = new ImageIcon("src/resource/body.png");
        body = bodyIcon.getImage();
        ImageIcon headIcon  = new ImageIcon("src/resource/head.png");
        head = headIcon.getImage();
        ImageIcon appleIcon  = new ImageIcon("src/resource/apple.png");
        apple = appleIcon.getImage();
    }

    //Draw Images using Graphics object
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        drawImage(g);
    }

    //Draw image
    public void drawImage(Graphics g){
        if(ingame){
            g.drawImage(apple, apple_x, apple_y, this);

            for(int i =0;i<DOTS;i++){
                if(i==0){
                    g.drawImage(head,x[0],y[0],this);
                }
                else{
                    g.drawImage(body,x[i],y[i],this);
                }
            }
        }
        else{
            gameOver(g);
            timer.stop();
        }
    }

    public void locateApple(){
        apple_x = (int) (Math.random()*39)*10;
        apple_y = (int) (Math.random()*39)*10;

    }

    public void gameOver(Graphics g){
        String msg = "Game Over";
        int score = (DOTS-3)*100;
        if(score > maxScore){
            maxScore = score;
        }
        String scoreMsg = "Score: "+score;
        String maxScoreMsg = "Max Score: "+maxScore;
        Font small = new Font("Helvetica", Font.BOLD,14);
        FontMetrics fontMetrics = getFontMetrics(small);


        g.setColor(Color.WHITE);
        g.setFont(small);

        g.drawString(msg, (WIDTH - fontMetrics.stringWidth(msg))/2, 50);
        g.drawString(scoreMsg, (WIDTH - fontMetrics.stringWidth(scoreMsg))/2, 150);
        g.drawString(maxScoreMsg, (WIDTH - fontMetrics.stringWidth(maxScoreMsg))/2, 100);


        //Restart Button
        restartButton = new JButton("Restart");
        restartButton.setOpaque(false);
        restartButton.setContentAreaFilled(false);
        restartButton.setBorderPainted(false);

        Font buttonFont = new Font("Arial", Font.BOLD, 14);
        restartButton.setFont(buttonFont);
        restartButton.setForeground(Color.GREEN);

        restartButton.setPreferredSize(new Dimension(100,40));

        setLayout(null);
        restartButton.setBounds(150,250,100,40);
        add(restartButton);

        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Container parent = getParent();
                parent.remove(Board.this);
                Board newBoard = new Board(maxScore);
                parent.add(newBoard);
                parent.revalidate();
                newBoard.requestFocusInWindow();
                newBoard.initGame();
                newBoard.loadImages();
            }
        });
    }

    public void checkCollision(){
        for(int i =1;i<DOTS;i++){
            if(i>4 && x[0] == x[i] && y[0] == y[i]){
                ingame = false;
            }
        }

        if(x[0]<0) {
            ingame = false;
        }
        if(y[0] < 0){
            ingame = false;
        }
        if(x[0]>=HEIGHT){
            ingame = false;
        }
        if(y[0] >=HEIGHT){
            ingame = false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(ingame){
            checkAppel();
            move();
            checkCollision();
        }

        repaint();
    }

    public void move(){
        for(int i = DOTS-1;i>0;i--){
            x[i] = x[i-1];
            y[i] = y[i-1];
        }

        if(left)
            x[0] = x[0]-10;
        if(right)
            x[0] = x[0]+10;
        if(up)
            y[0] = y[0]-10;
        if(down)
            y[0] = y[0]+10;


    }

    public void checkAppel(){
        if(apple_x == x[0] && apple_y == y[0]){
            DOTS++;
            locateApple();
        }
    }


    private class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent k){
            int key = k.getKeyCode();

            if(key == KeyEvent.VK_LEFT && !right){
                left = true;
                up = false;
                down = false;
            }
            if(key == KeyEvent.VK_RIGHT && !left){
                right = true;
                up = false;
                down = false;
            }
            if(key == KeyEvent.VK_UP && !down){
                left = false;
                right = false;
                up = true;
            }
            if(key == KeyEvent.VK_DOWN && !up){
                left = false;
                right = false;
                down = true;
            }
        }
    }
}
