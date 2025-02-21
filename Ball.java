public class Ball {
    private int X;
    private int Y;
    private int VelX;
    private int VelY;
    private int diameter;
    private int zapomnitskorostX;
    private int zapomnitskorostY;


    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public void setX(int X) {
        this.X = X;
    }

    public void setY(int Y) {
        this.Y = Y;
    }

    public int getVelX() {
        return VelX;
    }

    public int getVelY() {
        return VelY;
    }

    public void setVelX(int VelX) {
        this.VelX = VelX;
        if(VelX!=0){
            zapomnitskorostX = VelX;
        }
    }

    public void setVelY(int VelY) {
        this.VelY = VelY;
        if(VelY!=0){
            zapomnitskorostY = VelY;
        }
    }
    Ball(int X,int Y,int VelX,int VelY,int diameter){
        this.X = X;
        this.Y = Y;
        this.VelX = VelX;
        zapomnitskorostX = VelX;
        this.VelY = VelY;
        zapomnitskorostY = VelY;
        this.diameter = diameter;
    }
    public void smenanapravleniyaX(){
        this.VelX*=-1;
        zapomnitskorostX = VelX;
    }
    public void smenanapravleniyaY(){
        this.VelY*=-1;
        zapomnitskorostY = VelY;
    }
    public void izmenitvelX(int izm){
        this.VelX+=izm;
        zapomnitskorostX = VelX;
    }
    public void izmenitvelY(int izm){
        this.VelY+=izm;
        zapomnitskorostY = VelY;
    }
    public void izmenitX(int izm){
        this.X+=izm;
    }
    public void izmenitY(int izm){
        this.Y+=izm;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    public int getDiameter() {
        return diameter;
    }
    public int getZapomnitskorostX() {
        return zapomnitskorostX;
    }

    public int getZapomnitskorostY() {
        return zapomnitskorostY;
    }
}
