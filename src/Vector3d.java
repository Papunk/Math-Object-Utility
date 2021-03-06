public class Vector3d implements Comparable<Vector3d> {

    private double xComp, yComp, zComp;

    public double getxComp() {
        return xComp;
    }
    public double getyComp() {
        return yComp;
    }
    public double getzComp() {
        return zComp;
    }

    public Vector3d(String vector) {
        Vector3d ref = stringToVector(vector);
        if (ref != null) {
            this.xComp = ref.getxComp();
            this.yComp = ref.getyComp();
            this.zComp = ref.getzComp();
        } else {
            this.xComp = 0;
            this.yComp = 0;
            this.zComp = 0;
        }
    }

    public Vector3d(double xComp, double yComp, double zComp) {
        this.xComp = xComp;
        this.yComp = yComp;
        this.zComp = zComp;
    }

    public Vector3d(Point endPoint) {
        if (endPoint.getDimension() == 3) {
            this.xComp = endPoint.coords[0];
            this.yComp = endPoint.coords[1];
            this.zComp = endPoint.coords[2];
        }
    }

    // METHODS

    /**
     * @return magnitude of this vector
     */
    public double getMagnitude() {
        return Math.sqrt(Math.pow(this.xComp, 2) + Math.pow(this.yComp, 2) + Math.pow(this.zComp, 2));
    }

    /**
     * performs scalar multiplication on every component of this vector
     * @param magnitude the scalar to be multiplied
     */
    public void multiplyByScalar(int magnitude) {
        this.xComp *= magnitude;
        this.yComp *= magnitude;
        this.zComp *= magnitude;
    }

    /**
     * computes the unit vector of this vector
     * @return unit vector
     */
    public Vector3d getUnitVector() {
        return new Vector3d(this.xComp / this.getMagnitude(),this.yComp / this.getMagnitude(), this.zComp / this.getMagnitude());
    }

    // STATIC METHODS

    /**
     * @param v1 first vector
     * @param v2 second vector
     * @return the angle in degrees between the two vectors
     */
    public static double getAngle(Vector3d v1, Vector3d v2) {
        return Math.toDegrees(Math.acos(dotProduct(v1, v2) / (v1.getMagnitude() * v2.getMagnitude())));
    }

    /**
     * @param v1 first vector
     * @param v2 second vector
     * @return the dot product of the two vectors
     */
    public static double dotProduct(Vector3d v1, Vector3d v2) {
        return (v1.getxComp() * v2.getxComp()) + (v1.getyComp() * v2.getyComp()) + (v1.getzComp() * v2.getzComp());
    }

    /**
     * @param v1 first vector
     * @param v2 second vector
     * @return the cross product of the two vectors
     */
    public static Vector3d crossProduct(Vector3d v1, Vector3d v2) {
        double x = v1.getyComp()*v2.getzComp() - v1.getzComp()*v2.getyComp();
        double y = v1.getzComp()*v2.getxComp() - v1.getxComp()*v2.getzComp();
        double z = v1.getxComp()*v2.getyComp() - v1.getyComp()*v2.getxComp();
        return new Vector3d(x, y, z);
    }

    /**
     * @param v1 first vector
     * @param v2 second vector
     * @return true if the vectors are perpendicular and false otherwise
     */
    public static boolean arePerpendicular(Vector3d v1, Vector3d v2) {
        return dotProduct(v1, v2) == 0;
    }

    /**
     * makes a vector object from a string
     * @param vector string representation of the vector
     * @return vector object
     * @throws NumberFormatException for vectors with incorrect format
     */
    public static Vector3d stringToVector(String vector) throws NumberFormatException {
        if (vector.startsWith(Calc.lBrace) && vector.endsWith(Calc.rBrace)) {
            if (vector.length() > 3) {
                vector = vector.substring(1, vector.length() - 1);
                String[] components = vector.split(Calc.sep);
                int[] numericComps = new int[components.length];
                for (int i = 0; i < components.length; i++) {
                    numericComps[i] = Integer.parseInt(components[i]);
                }
                return new Vector3d(numericComps[0], numericComps[1], numericComps[2]);
            }
        }
        return null;
    }


    @Override
    public String toString() {
        return Calc.lBrace + xComp + Calc.sep + yComp + Calc.sep + zComp + Calc.rBrace;
    }


    @Override
    public int compareTo(Vector3d o) {
        return (int) (this.getMagnitude() - o.getMagnitude());
    }
}
