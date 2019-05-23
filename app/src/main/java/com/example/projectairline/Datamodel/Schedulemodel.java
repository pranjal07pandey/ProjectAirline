package com.example.projectairline.Datamodel;

import com.google.gson.annotations.SerializedName;

public class Schedulemodel {

    private int id;
    private String date;
    private String route;
    private String eta;
    private String etd;
    private String flightno;
    private String remarks;
    private String pilot;
    @SerializedName("co-pilot")
    private String copilot;
    private String cabincrew[];

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getRoute() {
        return route;
    }

    public String getEta() {
        return eta;
    }

    public String getEtd() {
        return etd;
    }

    public String getFlightno() {
        return flightno;
    }

    public String getRemarks() {
        return remarks;
    }

    public String getPilot() {
        return pilot;
    }

    public String getCopilot() {
        return copilot;
    }

    public String[] getCabincrew() {
        return cabincrew;
    }

    public Schedulemodel(int id, String date, String route, String eta, String etd, String flightno, String remarks, String pilot, String copilot, String[] cabincrew) {
        this.id = id;
        this.date = date;
        this.route = route;
        this.eta = eta;
        this.etd = etd;
        this.flightno = flightno;
        this.remarks = remarks;
        this.pilot = pilot;
        this.copilot = copilot;
        this.cabincrew = cabincrew;



    }
}
