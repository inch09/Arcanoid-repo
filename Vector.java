public class Vector {
    private int xCenter;
    private int yCenter;
    private int length;
    private int x;
    private double y;
    private double velXofEnd;
    private int angularVelocity;
    Vector(int xCenter,int yCenter,int length,int x0,int angularVelocity){
        this.xCenter = xCenter;
        this.yCenter = yCenter;
        this.length = length;
        this.x = x0;
        this.angularVelocity = angularVelocity;




    }

    public void rotate(){
        velXofEnd = angularVelocity*Math.sqrt(length*length-(xCenter-x)*(xCenter-x));
    }
    public void setX(int x){
        this.x = x;
    }


}
