package horusgeo.br158;

public class Regiao {

    private String coletaLixo;
    private String transporte;
    private String saude;
    private String escola;
    private String comercio;
    private String lazer;
    private String seguranca;
    private String uso;
    private Integer id;

    Regiao(){

    }

    public String getColetaLixo() {
        return coletaLixo;
    }

    public void setColetaLixo(String coletaLixo) {
        this.coletaLixo = coletaLixo;
    }

    public String getTransporte() {
        return transporte;
    }

    public void setTransporte(String transporte) {
        this.transporte = transporte;
    }

    public String getSaude() {
        return saude;
    }

    public void setSaude(String saude) {
        this.saude = saude;
    }

    public String getEscola() {
        return escola;
    }

    public void setEscola(String escola) {
        this.escola = escola;
    }

    public String getComercio() {
        return comercio;
    }

    public void setComercio(String comercio) {
        this.comercio = comercio;
    }

    public String getLazer() {
        return lazer;
    }

    public void setLazer(String lazer) {
        this.lazer = lazer;
    }

    public String getSeguranca() {
        return seguranca;
    }

    public void setSeguranca(String seguranca) {
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
