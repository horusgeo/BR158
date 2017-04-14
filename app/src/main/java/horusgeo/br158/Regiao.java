package horusgeo.br158;

public class Regiao {

    private Integer coletaLixo;
    private Integer transporte;
    private Integer saude;
    private Integer escola;
    private Integer comercio;
    private Integer lazer;
    private Integer seguranca;
    private String uso;
    private Integer id;

    Regiao(){

    }

    public Integer getColetaLixo() {
        return coletaLixo;
    }

    public void setColetaLixo(Integer coletaLixo) {
        this.coletaLixo = coletaLixo;
    }

    public Integer getTransporte() {
        return transporte;
    }

    public void setTransporte(Integer transporte) {
        this.transporte = transporte;
    }

    public Integer getSaude() {
        return saude;
    }

    public void setSaude(Integer saude) {
        this.saude = saude;
    }

    public Integer getEscola() {
        return escola;
    }

    public void setEscola(Integer escola) {
        this.escola = escola;
    }

    public Integer getComercio() {
        return comercio;
    }

    public void setComercio(Integer comercio) {
        this.comercio = comercio;
    }

    public Integer getLazer() {
        return lazer;
    }

    public void setLazer(Integer lazer) {
        this.lazer = lazer;
    }

    public Integer getSeguranca() {
        return seguranca;
    }

    public void setSeguranca(Integer seguranca) {
        this.seguranca = seguranca;
    }

    public String getUso() {
        return uso;
    }

    public void setUso(String uso) {
        this.uso = uso;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
