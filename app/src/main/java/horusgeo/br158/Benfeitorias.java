package horusgeo.br158;

public class Benfeitorias {

    private Integer id;

    private String construcoes;
    private String equipamentos;
    private String croquis;

    private String constucoesText;
    private String equipamentosText;
    private String croquisText;


    Benfeitorias(){


    }

    public String getConstrucoes() {
        return construcoes;
    }

    public void setConstrucoes(String construcoes) {
        this.construcoes = construcoes;
    }

    public String getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(String equipamentos) {
        this.equipamentos = equipamentos;
    }

    public String getCroquis() {
        return croquis;
    }

    public void setCroquis(String croquis) {
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
