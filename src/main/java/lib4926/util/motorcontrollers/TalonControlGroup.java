package lib4926.util.motorcontrollers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class TalonControlGroup {

    private ArrayList<WPI_TalonSRX> talonList;

    public TalonControlGroup(WPI_TalonSRX... talons) {
        talonList = new ArrayList<>(Arrays.asList(talons));
    }





    public void applyToAllControllers(Consumer<WPI_TalonSRX> action) {
        for(WPI_TalonSRX i : talonList) {
            action.accept(i);
        }
    }

}
