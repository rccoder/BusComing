package hit.edu.cn.buscoming.BusStatsObj;

/**
 * Created by Kimi on 2016/7/12.
 */
import java.util.Set;

public class bus {
    private String _id;
    private String info;
    private Set<stats> stats;
    public String get_id() {
        return _id;
    }
    public void set_id(String _id) {
        this._id = _id;
    }
    public String getInfo() {
        return info;
    }
    public void setInfo(String info) {
        this.info = info;
    }
    public Set<stats> getStats() {
        return stats;
    }
    public void setStats(Set<stats> stats) {
        this.stats = stats;
    }
}
