package hit.edu.cn.buscoming.Wea;

/**
 * Created by Kimi on 2016/7/18.
 */

public class Result {
    public Sk sk = new Sk();
    public Today today= new Today();
    private String dressing_advice;

    public String getDressing_advice() {
        return dressing_advice;
    }

    public void setDressing_advice(String dressing_advice) {
        this.dressing_advice = dressing_advice;
    }

    public String getUv_index() {
        return uv_index;
    }

    public void setUv_index(String uv_index) {
        this.uv_index = uv_index;
    }

    public String getComfort_index() {
        return comfort_index;
    }

    public void setComfort_index(String comfort_index) {
        this.comfort_index = comfort_index;
    }

    public String getDrying_index() {
        return drying_index;
    }

    public void setDrying_index(String drying_index) {
        this.drying_index = drying_index;
    }

    private String uv_index;
    private String comfort_index;
    private String drying_index;
}

