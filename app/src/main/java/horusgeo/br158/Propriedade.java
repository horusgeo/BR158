package horusgeo.br158;


public class Propriedade {

    private String zoneamento;
    private String topografia;
    private String manancial;
    private String situacao;
    private Boolean redeEletrica;
    private Boolean sinalTelefone;
    private Boolean abastecimentoAgua;
    private Integer id;

    Propriedade(){

    }


    public String getZoneamento() {
        return zoneamento;
    }

    public void setZoneamento(String zoneamento) {
        this.zoneamento = zoneamento;
    }

    public String getTopografia() {
        return topografia;
    }

    public void setTopografia(String topografia) {
        this.topografia = topografia;
    }

    public String getManancial() {
        return manancial;
    }

    public void setManancial(String manancial) {
        this.manancial = manancial;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Boolean getRedeEletrica() {
        return redeEletrica;
    }

    public void setRedeEletrica(Boolean redeEletrica) {
        this.redeEletrica = redeEletrica;
    }

    public Boolean getSinalTelefone() {
        return sinalTelefone;
    }

    public void setSinalTelefone(Boolean sinalTelefone) {
        this.sinalTelefone = sinalTelefone;
    }

    public Boolean getAbastecimentoAgua() {
        return abastecimentoAgua;
    }

    public void setAbastecimentoAgua(Boolean abastecimentoAgua) {
        this.abastecimentoAgua = abastecimentoAgua;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
