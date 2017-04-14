package horusgeo.br158;

/**
 * Created by HORUS03 on 23/03/2017.
 */

public class Plantacoes {

    private Integer id;
    private String tipo;
    private String idade;
    private String compl;

    Plantacoes(){}


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getCompl() {
        return compl;
    }

    public void setCompl(String compl) {
        this.compl = compl;
    }
}
