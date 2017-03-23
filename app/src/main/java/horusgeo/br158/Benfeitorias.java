package horusgeo.br158;

public class Benfeitorias {

    private Integer id;

    private Integer construcoes;
    private Integer equipamentos;
    private Integer croquis;

    private String constucoesText;
    private String equipamentosText;
    private String croquisText;


    Benfeitorias(){


    }

    public Integer getConstrucoes() {
        return construcoes;
    }

    public void setConstrucoes(Integer construcoes) {
        this.construcoes = construcoes;
    }

    public Integer getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(Integer equipamentos) {
        this.equipamentos = equipamentos;
    }

    public Integer getCroquis() {
        return croquis;
    }

    public void setCroquis(Integer croquis) {
        this.croquis = croquis;
    }

    public String getConstucoesText() {
        return constucoesText;
    }

    public void setConstucoesText(String constucoesText) {
        this.constucoesText = constucoesText;
    }

    public String getEquipamentosText() {
        return equipamentosText;
    }

    public void setEquipamentosText(String equipamentosText) {
        this.equipamentosText = equipamentosText;
    }

    public String getCroquisText() {
        return croquisText;
    }

    public void setCroquisText(String croquisText) {
        this.croquisText = croquisText;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
