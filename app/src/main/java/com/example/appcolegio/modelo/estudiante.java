package com.example.appcolegio.modelo;

public class estudiante {
    //Declaracion de variables
    private String uId;
    private String apeEst;
    private String dirEst;
    private String docEst;
    private String nomEst;
    private String telEst;

    //Constructor vacio

    public estudiante() {

    }

    //Escapsulamiento
    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getApeEst() {
        return apeEst;
    }

    public void setApeEst(String apeEst) {
        this.apeEst = apeEst;
    }

    public String getDirEst() {
        return dirEst;
    }

    public void setDirEst(String dirEst) {
        this.dirEst = dirEst;
    }

    public String getDocEst() {
        return docEst;
    }

    public void setDocEst(String docEst) {
        this.docEst = docEst;
    }

    public String getNomEst() {
        return nomEst;
    }

    public void setNomEst(String nomEst) {
        this.nomEst = nomEst;
    }

    public String getTelEst() {
        return telEst;
    }

    public void setTelEst(String telEst) {
        this.telEst = telEst;
    }

    @Override
    public String toString() {
        return nomEst + "  " + apeEst;
    }
}
