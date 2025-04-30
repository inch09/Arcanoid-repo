public class Boss {
    private int countlifes;
    private int force;
    private int beforeBoss;
    private boolean isThereNow;

    public int getCountlifes() {
        return countlifes;
    }

    public int getForce() {
        return force;
    }

    public void setCountlifes(int countlifes) {
        this.countlifes = countlifes;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public int getBeforeBoss() {
        return beforeBoss;
    }

    public void setBeforeBoss(int beforeBoss) {
        this.beforeBoss = beforeBoss;
    }

    public boolean isThereNow() {
        return isThereNow;
    }

    public void setThereNow(boolean thereNow) {
        isThereNow = thereNow;
    }

}
