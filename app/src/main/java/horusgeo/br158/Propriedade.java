package horusgeo.br158;


public class Propriedade {

    private String zoneamento;
    private String topografia;
    private String manancial;
    private String situacao;
    private Integer redeEletrica;
    private Integer sinalTelefone;
    private Integer abastecimentoAgua;
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

    public Integer getRedeEletrica() {
        return redeEletrica;
    }

    public void setRedeEletrica(Integer redeEletrica) {
        this.redeEletrica = redeEletrica;
    }

    public Integer getSinalTelefone() {
        return sinalTelefone;
    }

    public void setSinalTelefone(Integer sinalTelefone) {
        this.sinalTelefone = sinalTelefone;
    }

    public Integer getAbastecimentoAgua() {
        return abastecimentoAgua;
    }

    public void setAbastecimentoAgua(Integer abastecimentoAgua) {
        this.abastecimentoAgua = abastecimentoAgua;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
