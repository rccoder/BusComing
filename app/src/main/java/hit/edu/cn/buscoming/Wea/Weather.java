package hit.edu.cn.buscoming.Wea;

import java.util.List;

/**
 * Created by Kimi on 2016/7/18.
 */

public class Weather {
    private int resultcode;
    private String reason;

    public Result result = new Result();

    public List<Future> future;

    public List<hit.edu.cn.buscoming.Wea.Future> getFuture() {
        return future;
    }

    public void setFuture(List<hit.edu.cn.buscoming.Wea.Future> future) {
        future = future;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public int getResultcode() {
        return resultcode;
    }

    public void setResultcode(int resultcode) {
        this.resultcode = resultcode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
