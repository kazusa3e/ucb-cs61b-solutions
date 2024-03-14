public class NBody {

    public static final String backgroundImage = "images/starfield.jpg";

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

    }
}
