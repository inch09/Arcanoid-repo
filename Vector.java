public class Vector {
    private int xCenter;
    private int yCenter;
    private double x;
    private double y;
    private double angularVelocity;
    Vector(int xCenter,int yCenter,int X,int Y,double angularVelocity){
        this.xCenter = xCenter;
        this.yCenter = yCenter;
        this.x = X;
        this.y = Y;
        this.angularVelocity = angularVelocity;




    }

    public double rotateX(){
        return angularVelocity*(yCenter-y);
    }
    public void setX(double x){
        this.x = x;
    }
    public double rotateY(){
        return angularVelocity*(x-xCenter);
    }
    public void setY(double y){
        this.y =y;
    }

    public void setxCenter(int xCenter) {
        this.xCenter = xCenter;
    }
    public void setyCenter(int yCenter) {
        this.yCenter = yCenter;
    }


    public void setAngularVelocity(double angularVelocity) {
        this.angularVelocity = angularVelocity;

    }
    public int getxCenter(){
        return xCenter;
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }

    public int getyCenter() {
        return yCenter;
    }


}
