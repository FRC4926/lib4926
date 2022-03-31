package lib4926.vision;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 * The update method of this class should be called periodically.
 */
public class PeriodicLimelightRetriever {

    private NetworkTable limelight;
    private double validTarget, limelightX, limelightY, limelightArea, tshort, tlong, ta0, thoriz, tvert, tskew;

    /**
     * Constructs the network table object and takes an initial reading.
     * 
     * @param limelightName the name of the limelight as set in the web dashboard
     */
    public PeriodicLimelightRetriever(String limelightName) {
        limelight = NetworkTableInstance.getDefault().getTable(limelightName);
    }

    public NetworkTable getLimelight() {
        return this.limelight;
    }

    public double getValidTarget() {
        return this.validTarget;
    }

    /**
     * @return horizontal offset in degrees
     */
    public double getLimelightX() {
        return this.limelightX;
    }

    /**
     * @return vertical offset in degrees
     */
    public double getLimelightY() {
        return this.limelightY;
    }

    public void setToDriveCam(boolean drive) {
        limelight.getEntry("camMode").setNumber(drive ? 1 : 0);

    }

    /**
     * Calculates the horizontal distance (parallel to the ground) between the Limelight camera and the 
     * center of the target box.
     * 
     * @param pitch the angle that the camera is pitched up, in degrees
     * @param cameraHeight the height at which the camera lens is located, in inches
     * @param targetHeight the height at which the center of the target box is located, in inches
     * @return the horizontal distance
     */
    public double calcHorizontalDistance(double pitch, double cameraHeight, double targetHeight) {
        if (limelight.getEntry("tv").getDouble(0) == 0) {
            return -1;
        }

        double ty = Math.toRadians(limelightY);
        double dh = targetHeight - cameraHeight; // target height - camera height

        return dh / Math.tan(Math.toRadians(pitch) + ty);
    }

    /**
     * Calculates the horizontal distance (parallel to the ground) between the Limelight camera and the 
     * center of the target box, with an additional specified offset added to the calculation.
     * 
     * @param pitch the angle that the camera is pitched up, in degrees
     * @param cameraHeight the height at which the camera lens is located, in inches
     * @param targetHeight the height at which the center of the target box is located, in inches
     * @param offset the offset to add to the distance calculation
     * @return the horizontal distance
     */
    public double calcHorizontalDistance(double pitch, double cameraHeight, double targetHeight, double offset) {
        return calcHorizontalDistance(pitch, cameraHeight, targetHeight) + offset;
    }

    /**
     * Turns the Limelight's light on or off.
     * 
     * @param on true to turn light on, false to turn off
     */
    public void lightOn(boolean on) {
        if (on) {
            limelight.getEntry("ledMode").setNumber(3); // ON
        } else {
            limelight.getEntry("ledMode").setNumber(1); // OFF
        }
    }

    public double getLimelightArea() {
        return this.limelightArea;
    }

    public double getTshort() {
        return this.tshort;
    }

    public double getTlong() {
        return this.tlong;
    }

    public double getTa0() {
        return this.ta0;
    }

    public double getThoriz() {
        return this.thoriz;
    }

    public double getTvert() {
        return this.tvert;
    }

    public double getTskew() {
        return this.tskew;
    }

    /**
     * Updates all Limelight values.
     * 
     * Be sure to call this method periodically!
     */
    public void update() {
        validTarget = limelight.getEntry("tv").getDouble(0);
        limelightX = limelight.getEntry("tx").getDouble(0);
        limelightY = limelight.getEntry("ty").getDouble(0);
        limelightArea = limelight.getEntry("ta").getDouble(0);
        tshort = limelight.getEntry("tshort").getDouble(0);
        tskew = limelight.getEntry("ts").getDouble(0);
        tlong = limelight.getEntry("tlong").getDouble(0);
        ta0 = limelight.getEntry("ta0").getDouble(0);
        thoriz = limelight.getEntry("thoriz").getDouble(0);
        tvert = limelight.getEntry("tvert").getDouble(0);
    }

}
