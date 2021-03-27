package com.example.application;

public class Reseau {

    private String NomRegion;
    private String CodePostale ;
    private String Technology ;
    private String Operateur ;
    private String Latitude ;
    private String Longitude ;


    public Reseau(String nomregion , String codepostale , String technologies , String nomoperateur,String latitude , String longitude)
    {
        NomRegion = nomregion;
        CodePostale = codepostale;
        Technology = technologies ;
        Operateur = nomoperateur;
        Latitude = latitude ;
        Longitude = longitude ;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public void setTechnology(String technology) {
        Technology = technology;
    }

    public void setOperateur(String operateur) {
        Operateur = operateur;
    }

    public void setCodePostale(String codePostale) {
        CodePostale = codePostale;
    }

    public void setNomRegion(String nomRegion) {
        NomRegion = nomRegion;
    }

    public String getTechnology() {
        return Technology;
    }

    public String getOperateur() {
        return Operateur;
    }

    public String getCodePostale() {
        return CodePostale;
    }

    public String getNomRegion() {
        return NomRegion;
    }

    public String getLatitude() {
        return Latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    @Override
    public String toString() {
        return
                "NomRegion='" + NomRegion + '\'' +
                ", CodePostale='" + CodePostale + '\'' +
                ", Technology='" + Technology + '\'' +
                ", Operateur='" + Operateur + '\'' +
                ", Latitude='" + Latitude + '\'' +
                ", Longitude='" + Longitude + '\'' +  '\n'
                ;
    }
}
