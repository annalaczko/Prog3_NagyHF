package Files;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * stores the data of the two players
 */

public class BattleData implements Serializable {
    private final ArrayList<String> names;
    private final int [] points;

    public BattleData(String name0,String name1,int point0, int point1){
        names= new ArrayList<>();
        names.add(name0);
        names.add(name1);
        points= new int[2];
        points[0]=point0;
        points[1]=point1;
    }

    /**
     * gives one point for the winner
     * @param idx
     */
    public void winFor(int idx) {
        points[idx]++;
    }

    /**
     * getters
     */
    public ArrayList<String> getNames() {
        return names;
    }

    public int[] getPoints() {
        return points;
    }


}