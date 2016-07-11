package hit.edu.cn.buscoming.DB;

/**
 * Created by rccoder on 2016/7/11.
 */

public class State {
    public int _id;
    public String email;
    public String city;

    public State() {

    }

    public State(String email, String city) {
        this.email = email;
        this.city = city;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
