package hit.edu.cn.buscoming.DB;

/**
 * Created by rccoder on 2016/7/11.
 */

public class User {
    public int _id;
    public String email;
    public String password;



    public User() {

    }

    public  User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

}
