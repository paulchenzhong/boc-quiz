package com.boc.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

@Entity
public class Climate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @CsvBindByName(column = "Station_Name")
    private String stationName;
    
    @CsvBindByName(column = "Province")
    private String province;
    
    @CsvBindByName(column = "Date")
    @CsvDate(value = "dd/MM/yyyy")
    private Date date;
    
    @CsvBindByName(column = "Mean_Temp")
    private double meanTemp;
    
    @CsvBindByName(column = "Highest_Monthly_Maxi_Temp")
    private double highestMonthlyMaxTemp;
    
    @CsvBindByName(column = "Lowest_Monthly_Min_Temp")
    private double lowestMonthlyMaxTemp;

    public Climate() {
    }

    public Climate(String stationName, String province, Date date, double meanTemp, double highestMonthlyMaxTemp, double lowestMonthlyMaxTemp) {
        this.stationName = stationName;
        this.province = province;
        this.date = date;
        this.meanTemp = meanTemp;
        this.highestMonthlyMaxTemp = highestMonthlyMaxTemp;
        this.lowestMonthlyMaxTemp = lowestMonthlyMaxTemp;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getMeanTemp() {
        return meanTemp;
    }

    public void setMeanTemp(double meanTemp) {
        this.meanTemp = meanTemp;
    }

    public double getHighestMonthlyMaxTemp() {
        return highestMonthlyMaxTemp;
    }

    public void setHighestMonthlyMaxTemp(double highestMonthlyMaxTemp) {
        this.highestMonthlyMaxTemp = highestMonthlyMaxTemp;
    }

    public double getLowestMonthlyMaxTemp() {
        return lowestMonthlyMaxTemp;
    }

    public void setLowestMonthlyMaxTemp(double lowestMonthlyMaxTemp) {
        this.lowestMonthlyMaxTemp = lowestMonthlyMaxTemp;
    }

    @Override
    public String toString() {
        return "Climate{" +
                "id=" + id +
                ", stationName='" + stationName + '\'' +
                ", province='" + province + '\'' +
                ", date=" + date +
                ", meanTemp=" + meanTemp +
                ", highestMonthlyMaxTemp=" + highestMonthlyMaxTemp +
                ", lowestMonthlyMaxTemp=" + lowestMonthlyMaxTemp +
                '}';
    }
}
