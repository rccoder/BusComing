package hit.edu.cn.buscoming.Busstation;



/**
 * Created by 18645 on 2016/7/14.
 */
public class bus {
        private String _id;
        private String lat;
        private String lng;
        private String[] lines;
        public String get_id() {
            return _id;
        }
        public void set_id(String _id) {
            this._id = _id;
        }

        public String[] getLines() {
            return lines;
        }
        public void setLines(String[] lines) {
            this.lines = lines;
        }
        public String getLat() {
            return lat;
        }
        public void setLat(String lat) {
            this.lat = lat;
        }
        public String getLng() {
            return lng;
        }
        public void setLng(String lng) {
            this.lng = lng;
        }
}
