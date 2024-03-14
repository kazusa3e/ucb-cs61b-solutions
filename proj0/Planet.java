public class Planet {

    public static final double G = 6.67e-11;

    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Planet(Planet p) {
        if (p == null) {
            return ;
        }
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        if (p == null) {
            return 0;
        }
        return Math.sqrt(Math.pow(this.xxPos - p.xxPos, 2) + Math.pow(this.yyPos - p.yyPos, 2));
    }

    public double calcForceExertedBy(Planet p) {
        return Planet.G * this.mass * p.mass / Math.pow(this.calcDistance(p), 2);
    }

    public double calcForceExertedByX(Planet p) {
        return this.calcForceExertedBy(p) * ((p.xxPos - this.xxPos) / this.calcDistance(p));
    }

    public double calcForceExertedByY(Planet p) {
        return this.calcForceExertedBy(p) * ((p.yyPos - this.yyPos) / this.calcDistance(p));
    }

}
