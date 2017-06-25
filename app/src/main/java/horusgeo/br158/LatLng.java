package horusgeo.br158;

/**
 * Created by rafael on 24/06/2017.
 */

public class LatLng {

    private Integer id;
    private String lat;
    private String lng;

    LatLng(){

        this.lat = "";
        this.lng = "";

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
