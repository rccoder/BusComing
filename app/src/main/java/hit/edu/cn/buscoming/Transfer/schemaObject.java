package hit.edu.cn.buscoming.Transfer;

/**
 * Created by kengdie on 2016/7/13.
 */

public class schemaObject {
    private String startStat;
    private String endStat;
    private int footDist;
    private int lineDist;
    private String lineName;
    private String stats;

    public String getStartStat() {
        return startStat;
    }

    public String getEndStat() {
        return endStat;
    }

    public int getFootDist() {
        return footDist;
    }

    public int getLineDist() {
        return lineDist;
    }

    public String getLineName() {
        return lineName;
    }

    public String getStats() {
        return stats;
    }

    public void setStartStat(String startStat) {
        this.startStat = startStat;
    }

    public void setEndStat(String endStat) {
        this.endStat = endStat;
    }

    public void setFootDist(int footDist) {
        this.footDist = footDist;
    }

    public void setLineDist(int lineDist) {
        this.lineDist = lineDist;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public void setStats(String stats) {
        this.stats = stats;
    }
}
