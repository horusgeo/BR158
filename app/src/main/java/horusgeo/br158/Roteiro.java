package horusgeo.br158;

/**
 * Created by rafael on 27/05/2017.
 */

public class Roteiro {

    private Integer id;
    private String roteiro;

    Roteiro(){
        this.roteiro = "";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoteiro() {
        return roteiro;
    }

    public void setRoteiro(String roteiro) {
        this.roteiro = roteiro;
    }
}
