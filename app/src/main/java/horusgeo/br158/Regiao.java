package horusgeo.br158;

public class Regiao {

    private Boolean coletaLixo;
    private Boolean transporte;
    private Boolean saude;
    private Boolean escola;
    private Boolean comercio;
    private Boolean lazer;
    private Boolean seguranca;
    private String uso;
    private Integer id;

    Regiao(){

    }

    public Boolean getColetaLixo() {
        return coletaLixo;
    }

    public void setColetaLixo(Boolean coletaLixo) {
        this.coletaLixo = coletaLixo;
    }

    public Boolean getTransporte() {
        return transporte;
    }

    public void setTransporte(Boolean transporte) {
        this.transporte = transporte;
    }

    public Boolean getSaude() {
        return saude;
    }

    public void setSaude(Boolean saude) {
        this.saude = saude;
    }

    public Boolean getEscola() {
        return escola;
    }

    public void setEscola(Boolean escola) {
        this.escola = escola;
    }

    public Boolean getComercio() {
        return comercio;
    }

    public void setComercio(Boolean comercio) {
        this.comercio = comercio;
    }

    public Boolean getLazer() {
        return lazer;
    }

    public void setLazer(Boolean lazer) {
        this.lazer = lazer;
    }

    public Boolean getSeguranca() {
        return seguranca;
    }

    public void setSeguranca(Boolean seguranca) {
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
