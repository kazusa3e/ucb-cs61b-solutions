public class NBody {

    private static final String backgroundImage = "images/starfield.jpg";

    public static double readRadius(String filePath) {
        In in = new In(filePath);
        int numPlanets = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String filePath) {
        In in = new In(filePath);
        int numPlanets = in.readInt();
        double radius = in.readDouble();
        Planet[] planets = new Planet[numPlanets];
        for (int ix = 0; ix != numPlanets; ++ix) {
            Planet p = new Planet(
                in.readDouble(),
                in.readDouble(),
                in.readDouble(),
                in.readDouble(),
                in.readDouble(),
                in.readString()
            );
            planets[ix] = p;
        }
        return planets;
    }

    public static void main(String[] args) {
        if (args.length != 3) {
            System.exit(-1);
        }
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        // Drawing the background
        StdDraw.setScale(-radius, radius);
        StdDraw.picture(0, 0, backgroundImage);

        // Drawing all of the planets
        for (int ix = 0; ix != planets.length; ++ix) {
            planets[ix].draw();
        }

        // creating an animation
        StdDraw.enableDoubleBuffering();
        for (double t = 0; t <= T; t += dt) {
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            for (int ix = 0; ix != planets.length; ++ix) {
                xForces[ix] = planets[ix].calcNetForceExertedByX(planets);
                yForces[ix] = planets[ix].calcNetForceExertedByY(planets);
            }
            for (int ix = 0; ix != planets.length; ++ix) {
                planets[ix].update(dt, xForces[ix], yForces[ix]);
            }
            StdDraw.picture(0, 0, backgroundImage);
            for (int ix = 0; ix != planets.length; ++ix) {
                planets[ix].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }

        // printing the universe
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }

        System.exit(0);

    }
}
