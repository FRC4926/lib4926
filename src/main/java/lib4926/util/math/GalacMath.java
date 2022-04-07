package lib4926.util.math;

public final class GalacMath {

    /**
     * Applies the specified deadband to the output passed in.
     * 
     * @param output   the output value
     * @param deadband the deadband -- make sure this is positive!
     * @return output if greater in magnitude than the deadband, otherwise 0
     */
    public static double deadband(double output, double deadband) {
        return Math.abs(output) >= deadband ? output : 0;
    }

    /**
     * Fits the specified degree measure to its corresponding angle between 0
     * (inclusive) and 360 (exclusive).
     * 
     * @param degrees the angle measure to fit, in degrees
     * @return the corresponding angle between 0 (inclusive) and 360 (exclusive)
     */
    public static double clipTo360Degrees(double degrees) {
        double angle = degrees % 360;
        if (angle < 0) {
            return angle + 360;
        } else {
            return angle;
        }
    }

    /**
     * Fits the specified degree measure to its corresponding angle between -180
     * (exclusive) and 180 (inclusive).
     * 
     * @param degrees the angle measure to fit, in degrees
     * @return the corresponding angle between -180 (exclusive) and 180 (inclusive).
     */
    public static double clipTo180Degrees(double degrees) {
        double angle = clipTo360Degrees(degrees);
        if (angle > 180) {
            return angle - 360;
        } else {
            return angle;
        }
    }

}
