package hit.edu.cn.buscoming.Transfer;

import java.util.List;

/**
 * Created by kengdie on 2016/7/13.
 */

public class Res_transfer {
    private int error_code;
    private String reason;
    private List<schema> result;

    public int getError_code() {
        return error_code;
    }

    public String getReason() {
        return reason;
    }

    public List<schema> getResult() {
        return result;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setResult(List<schema> result) {
        this.result = result;
    }
}
