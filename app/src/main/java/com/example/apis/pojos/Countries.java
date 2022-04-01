package com.example.apis.pojos;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Countries {
    private String TotalRecovered;
    private String Country;
    private String TotalConfirmed;

    public Countries(String TotalRecovered, String Country, String TotalConfirmed) {
        this.TotalRecovered = TotalRecovered;
        this.Country = Country;
        this.TotalConfirmed = TotalConfirmed;
    }

    public String getTotalRecovered() {
        return TotalRecovered;
    }

    public void setTotalRecovered(String ID) {
        this.TotalRecovered = TotalRecovered;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getTotalConfirmed() {
        return TotalConfirmed;
    }

    public void setTotalConfirmed(String totalConfirmed) {
        TotalConfirmed = totalConfirmed;
    }


}