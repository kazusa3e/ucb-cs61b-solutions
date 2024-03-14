public class NBody {
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
}
