package com.frannzg.temperatura;

public class Temperatura {
    private String key;
    private String cel;
    private String data;
    private int humitat;
    private double temperatura;

    public Temperatura() {
    }

    public Temperatura(String cel, String data, int humitat, double temperatura) {
        this.cel = cel;
        this.data = data;
        this.humitat = humitat;
        this.temperatura = temperatura;
    }

    public String getCel() {
        return cel;
    }

    public void setCel(String cel) {
        this.cel = cel;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getHumitat() {
        return humitat;
    }

    public void setHumitat(int humitat) {
        this.humitat = humitat;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Temperatura{" +
                "cel='" + cel + '\'' +
                ", data='" + data + '\'' +
                ", humitat=" + humitat +
                ", temperatura=" + temperatura +
                '}';
    }
}
