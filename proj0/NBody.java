public class NBody {
    public static double readRadius(String filePath) {
        In in = new In(filePath);
        int numPlanets = in.readInt();
        double radius = in.readDouble();
        return radius;
    }
}
