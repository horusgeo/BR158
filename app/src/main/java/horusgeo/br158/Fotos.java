package horusgeo.br158;


/**
 * Created by rafael on 11/06/2017.
 */

public class Fotos {

    private Integer id;
    private Integer tipo;
    private String file;
    private String path;

    private Integer nova;

    Fotos(){
        this.id = 0;
        this.tipo = 0;
        this.file = "";
        this.path = "";
        this.nova = 0;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Integer getNova() {
        return nova;
    }

    public void setNova(Integer nova) {
        this.nova = nova;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
