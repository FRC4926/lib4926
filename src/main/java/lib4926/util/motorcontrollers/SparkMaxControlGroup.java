package lib4926.util.motorcontrollers;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ExternalFollower;
import com.revrobotics.CANSparkMax.IdleMode;

public class SparkMaxControlGroup {
    
    ArrayList<CANSparkMax> sparkMaxList;
    
    public SparkMaxControlGroup(CANSparkMax...sparks) {
        sparkMaxList = new ArrayList<>( Arrays.asList(sparks));
    }

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
     * Example usage:  leftDriveMotors.applyToAllControllers(x -> x.set(0.4)); sets an effort of 0.4 to all motors
     * @param action Pass in a lambda in order to apply an action to all spark maxes
     */
    public void applyToAllControllers(Consumer<CANSparkMax> action) {
        for(CANSparkMax i : sparkMaxList) {
            action.accept(i);
        }
    }

}
