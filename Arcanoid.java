import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Arcanoid extends JPanel implements ActionListener, KeyListener {

    private int x = 0;
    private int score = 0;
    private int countLife = 10;
    private int countStops = 30;
    boolean gameOver;
    private final int maxBallVelocity = 7;
    private final int minBallVelocity = 3;
    private final int  startVelocity = 5;
    private boolean firstStart = true;
    private boolean isStop = false;
    private int vectorLength = 100;
    private final double angularVelocity = 0.05;


    private int boardWidth;
    private int boardHeight;

    Platform platform = new Platform(150, 20, 300, 700, 0);
    Ball ball = new Ball(400, 100, 0, 0, 20);
    Brick brick = new Brick(100, 10);
    Bonus bonus = new Bonus();
    Vector vector  = new Vector(ball.getX()+ ball.getDiameter()/2,ball.getY()+ ball.getDiameter()/2,ball.getX(),ball.getY()+ball.getDiameter()/2-vectorLength,angularVelocity);


    Timer gameStart;

    Arcanoid(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        setPreferredSize(new Dimension(this.boardWidth, this.boardHeight));
        setBackground(new Color(66, 167, 245));
        addKeyListener(this);
        setFocusable(true);


        gameStart = new Timer(1, this);
        gameStart.start();


    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void paintCounters(Graphics g){
        g.setFont(new Font("SanSerif", 23, 20));
        g.setColor(Color.black);
        g.drawString("Счет: "+score,10,20);
        g.setColor(Color.black);
        g.drawString("Жизни: "+countLife,10,40);
        g.setColor(Color.black);
        g.drawString("Остановки: "+countStops,10,60);
        g.setColor(Color.black);
        g.drawString("Скорость: "+(int)(Math.sqrt(Math.pow(ball.getZapomnitskorostX(),2)+Math.pow(ball.getZapomnitskorostY(),2))),10,80);
        g.setColor(Color.black);
    }

    public void draw(Graphics g) {
        paintCounters(g);
        // platform
        g.setColor(platform.getColor());
        g.fillRect(platform.getX(), platform.getY(), platform.getWidth(), platform.getHeight());
        //brick
        g.setColor(brick.getColor());
        g.fillRect(brick.getX(), brick.getY(), brick.getWidth(), brick.getHeight());
        //ball
        g.setColor(Color.black);
        g.fillOval(ball.getX(), ball.getY(), ball.getDiameter(), ball.getDiameter());
        if(isStop || firstStart){
            g.setColor(Color.red);
            g.drawLine(vector.getxCenter(),vector.getyCenter(),(int)vector.getX(),(int) vector.getY());
        }
        if (bonus.isThereNow()) {
            g.setColor(bonus.getColor());
            g.fillOval(bonus.getX(), bonus.getY(), bonus.getDiameter(), bonus.getDiameter());
        }
        if (gameOver) {
            g.setColor(Color.red);
            g.fillRect(platform.getX(), platform.getY(), platform.getWidth(), platform.getHeight());
            g.setColor(Color.red);
            g.fillOval(ball.getX(), ball.getY(), ball.getDiameter(), ball.getDiameter());
        }

    }

    public void getBonus(){
        if ((ball.getX() >= (bonus.getX() - ball.getDiameter()) && ball.getX() <= (bonus.getX() + bonus.getDiameter())) && (ball.getY() >= (bonus.getY() - ball.getDiameter()) && ball.getY() <= (bonus.getY() + bonus.getDiameter())) && (bonus.isThereNow())) {
            bonus.setThereNow(false);
            if (bonus.getBonusType()==BonusType.PLUSSCORE) {
                score += 5;
            }
            if (bonus.getBonusType()==BonusType.PLUSLIFE) {
                countLife++;
            }
            if (bonus.getBonusType()==BonusType.PLUSWIDTHPLATFORM) {
                platform.updateWidth(30);
            }
            if (bonus.getBonusType()==BonusType.MINUSLIFE) {
                countStops--;
            }
            if(bonus.getBonusType()==BonusType.PLUSSCORE){
                countStops++;
            }
        }
    }

    public void gameOver(){
        if (countLife <= 0) {
            gameOver = true;
        }
    }
    public void isplatforminWall(){
        if (platform.getX() >= boardWidth - platform.getWidth()) {
            platform.setVelX(0);
            platform.setX(boardWidth - 1 - platform.getWidth());
        } else if (platform.getX() <= 0) {
            platform.setVelX(0);
            platform.setX(1);
        }
        platform.izmenitX(platform.getVelX());
    }
    public void isballinWall(){
        if ((ball.getX() >= boardWidth - ball.getDiameter()) || ball.getX() <= 0) {
            ball.smenanapravleniyaX();
        }
        if (ball.getY() <= 0) {
            ball.smenanapravleniyaY();
        }
        if (ball.getY() >= (boardHeight - ball.getDiameter())) {
            countLife--;
            ball.smenanapravleniyaY();
            if (countLife == 0) {
                gameOver = true;
                System.out.println("Game Over");
                System.out.println("Score: " + score);
            }
        }
    }
    public void isballinPlatform(){
        if ((ball.getY() + ball.getDiameter() >= platform.getY() && ball.getY() <= platform.getY() + platform.getHeight()) && (ball.getX() + platform.getHeight() >= platform.getX() && ball.getX() <= platform.getX() + platform.getWidth()) && ball.getVelY() > 0) {
            ball.smenanapravleniyaY();
        }
    }
    public void isballinBrick(){
        if ((ball.getY() + ball.getDiameter() >= brick.getY() && ball.getY() <= brick.getY() + brick.getHeight()) && (ball.getX() + brick.getHeight() >= brick.getX() && ball.getX() <= brick.getX() + brick.getWidth())) {
            ball.smenanapravleniyaY();
            score+=Math.abs(ball.getVelX())/3;
            brick.replace();
            if (!bonus.isThereNow()) {
                bonus.spawn();
            }
        }
    }
    public void changeVelBall(){
        ball.izmenitX(ball.getVelX());
        ball.izmenitY(ball.getVelY());
    }

    public void move() {
        if (isStop||firstStart){
            vector.setX(vector.getX()+vector.rotateX());
            vector.setY(vector.getY()+vector.rotateY());
        }
        gameOver();
        getBonus();
        isplatforminWall();
        isballinWall();
        isballinPlatform();
        isballinBrick();
        changeVelBall();
    }

    public void movePlatform(KeyEvent e){
        int platformVelocity = 5;
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            platform.setVelX(-platformVelocity);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            platform.setVelX(platformVelocity);
        }
    }
    public void upOrdownVelBall(KeyEvent e){
        // уменьшение или возрастание скорости мячика
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            if ((ball.getVelY()!=0) && (ball.getVelX()!=0) && (Math.abs(ball.getVelX())<maxBallVelocity)) {
                ball.setVelY(ball.getVelY() / Math.abs(ball.getVelY()) * (Math.abs(ball.getVelY()) + 1));
                ball.setVelX(ball.getVelX() / Math.abs(ball.getVelX()) * (Math.abs(ball.getVelX()) + 1));
            }
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN && Math.abs(ball.getVelY()) > minBallVelocity) {
            if (ball.getVelY() != 0 && ball.getVelX() != 0) {
                ball.setVelY(ball.getVelY() / Math.abs(ball.getVelY()) * (Math.abs(ball.getVelY()) - 1));
                ball.setVelX(ball.getVelX() / Math.abs(ball.getVelX()) * (Math.abs(ball.getVelX()) - 1));
            }
        }

    }
    public void isStop(KeyEvent e){
       if(e.getKeyCode() == KeyEvent.VK_SPACE && firstStart == true){
           ball.setVelX(startVelocity);
           ball.setVelY(startVelocity);
           firstStart = false;
           ball.setVelX((int)(Math.sqrt(Math.pow(ball.getZapomnitskorostX(),2)+Math.pow(ball.getZapomnitskorostY(),2)+1)*((vector.getX()-vector.getxCenter())/vectorLength)));
           ball.setVelY((int)(Math.sqrt(Math.pow(ball.getZapomnitskorostX(),2)+Math.pow(ball.getZapomnitskorostY(),2)+1)*((vector.getY()-vector.getyCenter())/vectorLength)));

       }
        else if(e.getKeyCode()==KeyEvent.VK_SPACE){
            if((int)(Math.sqrt(Math.pow(ball.getVelX(),2)+Math.pow(ball.getVelY(),2)))!=0 && countStops>0){
                ball.setVelX(0);
                ball.setVelY(0);
                countStops -= 1;
                isStop = true;
                changeVector();
            }
            else{
                ball.setVelX((int)(Math.sqrt(Math.pow(ball.getZapomnitskorostX(),2)+Math.pow(ball.getZapomnitskorostY(),2)+1)*((vector.getX()-vector.getxCenter())/vectorLength)));
                ball.setVelY((int)(Math.sqrt(Math.pow(ball.getZapomnitskorostX(),2)+Math.pow(ball.getZapomnitskorostY(),2)+1)*((vector.getY()-vector.getyCenter())/vectorLength)));
                isStop = false;
            }
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        movePlatform(e);
        upOrdownVelBall(e);
        isStop(e);
    }
    public void isThereNowBonus(){
        if (bonus.isThereNow()) {
            if (x == bonus.getTimeLife()) {
                bonus.setThereNow(false);
                x = 0;
            }
            x += 1;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        isThereNowBonus();
        repaint();
        if (gameOver) {
            gameStart.stop();
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }


    @Override
    public void keyReleased(KeyEvent e) {

    }
    public void changeVector(){
            vector.setxCenter(ball.getX()+ ball.getDiameter()/2);
            vector.setyCenter(ball.getY()+ball.getDiameter()/2);
            vector.setX(ball.getX());
            vector.setY(vector.getyCenter()-vectorLength);
            vector.setAngularVelocity(angularVelocity);

    }


}