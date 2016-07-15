package hit.edu.cn.buscoming.Transfer;

import java.lang.reflect.Array;
import java.util.List;

/**
 * Created by kengdie on 2016/7/13.
 */

public class schema {
    private int dist;
    private int footDist;
    private int lastFootDist;
    private int time;
    private List<schemaObject> segs;

    public List<schemaObject> getSegs() {
        return segs;
    }

    public int getDist() {
        return dist;
    }

    public int getFootDist() {
        return footDist;
    }

    public int getLastFootDist() {
        return lastFootDist;
    }

    public int getTime() {
        return time;
    }

    public void setDist(int dist) {
        this.dist = dist;
    }

    public void setFootDist(int footDist) {
        this.footDist = footDist;
    }

    public void setLastFootDist(int lastFootDist) {
        this.lastFootDist = lastFootDist;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setSegs(List<schemaObject> segs) {
        this.segs = segs;
    }
}

