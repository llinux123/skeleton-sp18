import java.util.ArrayList;
import java.util.Scanner;

public class NBody {
    private static In in ;
    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String fileName = args[2];
        double Radius = readRadius(fileName);
        StdDraw.setXscale(-Radius, Radius);
        StdDraw.setYscale(-Radius, Radius);
        StdDraw.picture(0,0,"images/starfield.jpg");
        Planet[] planets = readPlanets(fileName);
        for(Planet p : planets) {
            p.draw();
        }
        StdDraw.enableDoubleBuffering();
        int planetsLength = planets.length;
        int t=0;
        while(t<T){
            double[] xForces = new double[planetsLength];
            double[] yForces = new double[planetsLength];
            for(int i = 0; i<planetsLength; i++){
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for(int i = 0; i<planetsLength; i++){
                planets[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0,0,"images/starfield.jpg");
            for (int i = 0; i < planetsLength; i++) {
                planets[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            t+=dt;
        }
        StdOut.printf("%d\n"+"%.2e\n",planetsLength,Radius);
        for (int i = 0; i < planetsLength; i++) {
            Planet p = planets[i];
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                          p.xxPos, p.yyPos, p.xxVel, p.yyVel, p.mass, p.imgFileName);
        }
    }
    public static double readRadius(String planetsTxtPath) {
        in = new In(planetsTxtPath);
        in.readInt();
        return in.readDouble();
    }
    public static Planet[] readPlanets(String planetsTxtPath) {

        in = new In(planetsTxtPath);
        int times = in.readInt();
        in.readDouble();
        ArrayList<Planet> planets = new ArrayList<Planet>();
        while(times > 0) {
            times--;
            Planet tmpPlanet = new Planet(in.readDouble(),in.readDouble(),
                                          in.readDouble(), in.readDouble(),
                                          in.readDouble() , in.readString());
            planets.add(tmpPlanet);
        }
        return planets.toArray(new Planet[planets.size()]);
    }
}
