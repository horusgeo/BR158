package horusgeo.br158;


import java.util.ArrayList;

public class Benfeitorias {

    private ArrayList<String> photos;

    private Integer id;

    private Boolean construcoes;
    private Boolean equipamentos;
    private Boolean croquis;

    private String constucoesText;
    private String equipamentosText;
    private String croquisText;


    Benfeitorias(){


    }

    public Boolean getConstrucoes() {
        return construcoes;
    }

    public void setConstrucoes(Boolean construcoes) {
        this.construcoes = construcoes;
    }

    public Boolean getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(Boolean equipamentos) {
        this.equipamentos = equipamentos;
    }

    public Boolean getCroquis() {
        return croquis;
    }

    public void setCroquis(Boolean croquis) {
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

    public ArrayList<String> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<String> photos) {
        this.photos = photos;
    }
}
