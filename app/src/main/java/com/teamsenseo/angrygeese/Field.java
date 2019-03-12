package com.teamsenseo.angrygeese;

public class Field {
    private String veldID;
    private String perceelNaam;
    private String VeldSchade;

    public Field() {

    }

    public Field(String veldID, String perceelNaam, String veldSchade) {
        this.veldID = veldID;
        this.perceelNaam = perceelNaam;
        VeldSchade = veldSchade;
    }

    public String getveldID() {
        return veldID;
    }

    public String getperceelNaam() {
        return perceelNaam;
    }

    public String getVeldSchade() {
        return VeldSchade;
    }
}