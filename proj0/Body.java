public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    // 计算距离
    public double calcDistance(Body b) {
        double x = this.xxPos - b.xxPos;
        double y = this.yyPos - b.yyPos;
        double z;
        z = Math.sqrt(x * x + y * y);
        return z;
    }

    // 计算两个天体间作用力
    public double calcForceExertedBy(Body b) {
        double G = 6.67e-11;
        double F = (G * this.mass * b.mass) / Math.pow(this.calcDistance(b), 2);
        return F;
    }

    public double calcForceExertedByX(Body b) {
        double Fx = this.calcForceExertedBy(b) * (b.xxPos - this.xxPos) / this.calcDistance(b);
        return Fx;
    }

    public double calcForceExertedByY(Body b) {
        double Fy = this.calcForceExertedBy(b) * (b.yyPos - this.yyPos) / this.calcDistance(b);
        return Fy;
    }

    public double calcNetForceExertedByX(Body[] bs) {
        double FxNet = 0;
        for (Body b : bs) {
            if (!this.equals(b)) {
                FxNet += this.calcForceExertedByX(b);
            }
        }
        return FxNet;
    }

    public double calcNetForceExertedByY(Body[] bs) {
        double FyNet = 0;
        for (Body b : bs) {
            if (!this.equals(b)) {
                FyNet += this.calcForceExertedByY(b);
            }
        }
        return FyNet;
    }

    public void update(double dt, double fX, double fY) {
        double ax = fX / this.mass;
        double ay = fY / this.mass;
        this.xxVel += ax * dt;
        this.yyVel += ay * dt;
        this.xxPos += this.xxVel * dt;
        this.yyPos += this.yyVel * dt;
    }

    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }

    public Body(double xxPos, double yyPos, double xxVel, double yyVel, double mass, String imgFileName) {
        this.xxPos = xxPos;
        this.yyPos = yyPos;
        this.xxVel = xxVel;
        this.yyVel = yyVel;
        this.mass = mass;
        this.imgFileName = imgFileName;
    }

    public Body(Body b) {
        this.xxPos = b.xxPos;
        this.yyPos = b.yyPos;
        this.xxVel = b.xxVel;
        this.yyVel = b.yyVel;
        this.mass = b.mass;
        this.imgFileName = b.imgFileName;
    }

}
