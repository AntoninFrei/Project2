package com.engeto.project2;

import org.json.JSONObject;

import java.math.BigDecimal;

public class TaxRate {
    Boolean isHas;
    BigDecimal rate;

    public Boolean getHas() {
        return isHas;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public TaxRate(JSONObject readedData, String country, String key) {

        try {
            this.rate = readedData.getJSONObject(country).getBigDecimal(key);
            this.isHas = true;
        }
        catch (org.json.JSONException e) {
            this.rate = new BigDecimal(0);
            this.isHas = false;


        }




    }

    public String getDescription() {
        String out;
        if (this.isHas) {
            return "Ano - " + this.rate + " %";
        } else {
            return "Ne";
        }
    }






}
