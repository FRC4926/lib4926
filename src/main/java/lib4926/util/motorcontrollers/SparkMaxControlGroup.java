package lib4926.util.motorcontrollers;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ExternalFollower;
import com.revrobotics.CANSparkMax.IdleMode;

public class SparkMaxControlGroup {
    
    ArrayList<CANSparkMax> sparkMaxList;
    
    public SparkMaxControlGroup(CANSparkMax... sparks) {
        sparkMaxList = new ArrayList<>(Arrays.asList(sparks));
    }

    /**
     * Disables follower mode and restores factory default settings on all Spark Maxes
     */
    public void resetSettings() {
        for(CANSparkMax i : sparkMaxList) {
            i.follow(ExternalFollower.kFollowerDisabled, 0);
            i.restoreFactoryDefaults();
        }
    }

    public void setOpenLoopRampRate(int rampRate) {
        for(CANSparkMax i : sparkMaxList) {
            i.setOpenLoopRampRate(rampRate);
        }
    }

    public void setIdleModes(IdleMode mode) {
        for(CANSparkMax i : sparkMaxList) {
            i.setIdleMode(mode);
        }
    }

    public void setCurrentLimits(int limit) {
        for(CANSparkMax i : sparkMaxList) {
            i.setSmartCurrentLimit(limit);
        }
    }

    /**
     * Applies an action or set of actions to all Spark Maxes in this control group.
     * 
     * <p>
     * Example usage:  
     * <pre>{@code
     *      leftDriveMotors.applyToAllControllers(x -> x.set(0.4));
     * }</pre>
     * <p>
     * Example usage with multiple commands:
     * <pre>{@code
     *      leftDriveMotors.applyToAllControllers(x -> {
     *          x.setSmartCurrentLimit(30);
     *          x.setIdleMode(IdleMode.kBrake);
     *      });
     * }</pre>
     * 
     * @param action Consumer that specifies the actions to apply to each Spark MAx
     */
    public void applyToAllControllers(Consumer<CANSparkMax> action) {
        for(CANSparkMax i : sparkMaxList) {
            action.accept(i);
        }
    }

}
