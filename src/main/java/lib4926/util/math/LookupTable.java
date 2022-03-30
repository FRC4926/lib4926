package lib4926.util.math;

import java.util.List;

public class LookupTable {
    
    List<Double> distances;
    List<Double> rpms;

    public LookupTable(List<Double> distances, List<Double> rpms) {
        this.distances = distances;
        this.rpms = rpms; 
    }

    public int getRPM(double distance) {
        int fin = -1; 

        if(distance == distances.get(distances.size() -1)) {
            return (int) (rpms.get(rpms.size() - 1) + 0.0);
        }


        for(int i = 0; i < rpms.size(); i++) {
            if(distances.get(i) > distance) {
                fin = i -1; 
                break;
            }
        }
        if(fin == -1 || (fin + 1 > rpms.size() - 1) ) {
            return (int) (5.39302*distance + 959.6977);
        } else {
            double firstVal = rpms.get(fin);
            double secondVal = rpms.get(fin + 1);
            double distanceOne = distances.get(fin);
            double distanceTwo = distances.get(fin+1);

            double effort = firstVal + (((distance - distanceOne)/(distanceTwo - distanceOne)) * (secondVal-firstVal));
            return (int) effort;
        }


    }


}
