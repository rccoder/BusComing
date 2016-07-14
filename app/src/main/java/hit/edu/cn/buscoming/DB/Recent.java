package hit.edu.cn.buscoming.DB;

/**
 * Created by rccoder on 2016/7/12.
 */

public class Recent {

    public int _id;
    public String email;
    public int flag;
    public String line_city;
    public String line_line;
    public String stop_city;
    public String stop_stop;
    public String des_city;
    public String des_src;
    public String des_des;

    public Recent() {

    }

    public Recent(String email, int flag) {
        this.email = email;
        this.flag = flag;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getLine_city() {
        return line_city;
    }

    public void setLine_city(String line_city) {
        this.line_city = line_city;
    }

    public String getLine_line() {
        return line_line;
    }

    public void setLine_line(String line_line) {
        this.line_line = line_line;
    }

    public String getStop_city() {
        return stop_city;
    }

    public void setStop_city(String stop_city) {
        this.stop_city = stop_city;
    }

    public String getStop_stop() {
        return stop_stop;
    }

    public void setStop_stop(String stop_stop) {
        this.stop_stop = stop_stop;
    }

    public String getDes_city() {
        return des_city;
    }

    public void setDes_city(String des_city) {
        this.des_city = des_city;
    }

    public String getDes_src() {
        return des_src;
    }

    public void setDes_src(String des_src) {
        this.des_src = des_src;
    }

    public String getDes_des() {
        return des_des;
    }

    public void setDes_des(String des_des) {
        this.des_des = des_des;
    }


}
