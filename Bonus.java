import java.awt.*;
import java.util.Random;

public class Bonus {
    private Color color;
    private boolean isThereNow;
    private int X;
    private int Y;
    private int diameter;
    private String str;
    private BonusType bonusType;
    private int timeLife;

    Bonus() {
        diameter = 30;
        timeLife = 500;
    }


    public void setX(int x) {
        X = x;
    }

    public void setY(int y) {
        Y = y;
    }

    public int getY() {
        return Y;
    }

    public int getX() {
        return X;
    }

    public void spawn() {
        Random random = new Random();
        X = random.nextInt(100, 400);
        Y = random.nextInt(100, 400);
        int c = random.nextInt(1, 12);
        if (c <= 2) {
            color = new Color(1, 1, 1);
            bonusType = BonusType.PLUSSCORE;
        } else if (c <= 4) {
            color = Color.GREEN;
            bonusType = BonusType.PLUSLIFE;
        } else if (c <= 6) {
            color = Color.BLUE;
            bonusType = BonusType.PLUSWIDTHPLATFORM;
        } else if (c <= 8) {
            color = new Color(245, 87, 66);
            bonusType = BonusType.MINUSLIFE;
        }
        isThereNow = true;
    }

    public void ubratbonus() {
        isThereNow = false;
    }

    public boolean isThereNow() {
        return isThereNow;
    }

    public Color getColor() {
        return color;
    }


    public void setColor(Color color) {
        this.color = color;
    }

    public void setThereNow(boolean thereNow) {
        this.isThereNow = thereNow;
    }


    public String getStr() {
        return str;
    }

    public int getTimeLife() {
        return timeLife;
    }

    public void setTimeLife(int timeLife) {
        this.timeLife = timeLife;
    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    public BonusType getBonusType() {
        return bonusType;
    }
}