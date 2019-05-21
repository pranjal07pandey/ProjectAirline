package com.example.projectairline.Datamodel;

public class ScheduleCrewFrag  {

    private int id;
    private String date;
    private String route;
    private String eta;
    private String etd;
    private String flightno;
    private String remarks;
    private String[] captaincrew;
    private String[] cabincrew;


    public ScheduleCrewFrag(int id, String date, String route, String eta, String etd, String flightno, String remarks, String[] captaincrew, String[] cabincrew) {
        this.id = id;
        this.date = date;
        this.route = route;
        this.eta = eta;
        this.etd = etd;
        this.flightno = flightno;
        this.remarks = remarks;
        this.captaincrew = captaincrew;
        this.cabincrew = cabincrew;
    }

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

    public String[] getCaptaincrew() {
        return captaincrew;
    }

    public String[] getCabincrew() {
        return cabincrew;
    }
}
