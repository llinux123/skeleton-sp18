public class Planet {
  public double xxPos;
  public double yyPos;
  public double xxVel;
  public double yyVel;
  public double mass;
  public String imgFileName;
  private static final double G = 6.67e-11;
  public Planet(double xP, double yP, double xV, double yV, double m, String img) {
    xxPos = xP;
    yyPos = yP;
    xxVel = xV;
    yyVel = yV;
    mass = m;
    imgFileName = img;
  }
  public Planet(Planet p) {
    xxPos = p.xxPos;
    yyPos = p.yyPos;
    xxVel = p.xxVel;
    yyVel = p.yyVel;
    mass = p.mass;
    imgFileName = p.imgFileName;
  }
  public double calcDistance(Planet p) {
    return Math.sqrt(
            Math.pow(this.xxPos - p.xxPos,2)+
            Math.pow(this.yyPos - p.yyPos,2));
  }
  public double calcForceExertedBy(Planet p) {
    return G*this.mass*p.mass/Math.pow(calcDistance(p),2);
  }
  public double calcForceExertedByX(Planet p) {
    if(p.xxPos==xxPos && p.yyPos==yyPos){
      return 0;
    }
    return -(this.xxPos-p.xxPos)/calcDistance(p)*calcForceExertedBy(p);
  }
  public double calcForceExertedByY(Planet p) {
    if(p.xxPos==xxPos && p.yyPos==yyPos){
      return 0;
    }
    return -(this.yyPos-p.yyPos)/calcDistance(p)*calcForceExertedBy(p);
  }
  public double calcNetForceExertedByX(Planet[] p) {
    double Total=0.0;
    for(Planet planet : p) {
      Total+=calcForceExertedByX(planet);
    }
    return Total;
  }
  public double calcNetForceExertedByY(Planet[] p) {
    double Total=0.0;
    for(Planet planet : p) {
      Total+=calcForceExertedByY(planet);
    }
    return Total;
  }
  public void update(double dt,double xForce,double yForce) {
    double aXX = xForce / this.mass;
    double yXX = yForce / this.mass;
    this.xxVel += aXX * dt;
    this.yyVel += yXX * dt;
    this.xxPos += this.xxVel * dt;
    this.yyPos += this.yyVel * dt;
  }
  public void draw(){
    StdDraw.picture(xxPos, yyPos, "images/"+imgFileName);
  }
}