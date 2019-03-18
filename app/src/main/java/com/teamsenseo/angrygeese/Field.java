package com.teamsenseo.angrygeese;

public final class Field {
    private final String veldID;
    private final String perceelNaam;
    private final String veldSchade;

    public Field(final String veldID, final String perceelNaam, final String veldSchade) {
        this.veldID = veldID;
        this.perceelNaam = perceelNaam;
        this.veldSchade = veldSchade;
    }

    public String getveldID() {
        return this.veldID;
    }

    public String getperceelNaam() {
        return this.perceelNaam;
    }

    public String getveldSchade() {
        return this.veldSchade;
    }
}