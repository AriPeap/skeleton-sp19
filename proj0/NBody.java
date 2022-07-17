public class NBody {
    public static double readRadius(String fileName) {
        In in = new In(fileName);
        in.readInt();
        double Radius = in.readDouble();
        return Radius;
    }

    public static Body[] readBodies(String fileName) {
        In in = new In(fileName);
        int num = in.readInt();
        in.readDouble();
        Body[] planets = new Body[num];

        int i = 0;
        while (i < num) {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String file = in.readString();
            planets[i] = new Body(xP, yP, xV, yV, m, file);
            i++;
        }
        return planets;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String fileName = args[2];
        double radius = NBody.readRadius(fileName);
        Body[] Planets = NBody.readBodies(fileName);

        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");

        for (Body planet : Planets) {
            planet.draw();
        }

        /**
         * Animation
         */
        StdDraw.enableDoubleBuffering();
        /**
         * Set up a loop to loop until time variable reaches T
         */
        for (double t = 0; t <= T; t += dt) {
            double[] xForces = new double[Planets.length];
            double[] yForces = new double[Planets.length];
            /**
             * Calculate the net forces for every planet
             */
            for (int i = 0; i < Planets.length; i++) {
                xForces[i] = Planets[i].calcNetForceExertedByX(Planets);
                yForces[i] = Planets[i].calcNetForceExertedByY(Planets);
            }
            /**
             * Update positions and velocities of each planet
             */
            for (int i = 0; i < Planets.length; i++) {
                Planets[i].update(dt, xForces[i], yForces[i]);
            }
            /**
             * Draw the background
             */
            StdDraw.picture(0, 0, "images/starfield.jpg");
            /**
             * Draw all planets
             */
            for (Body planet : Planets) {
                planet.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);

        }

        /**
         * Print out the final state of the universe when time reaches T
         */
        StdOut.printf("%d\n", Planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < Planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    Planets[i].xxPos, Planets[i].yyPos, Planets[i].xxVel,
                    Planets[i].yyVel, Planets[i].mass, Planets[i].imgFileName);

        }
    }
}
