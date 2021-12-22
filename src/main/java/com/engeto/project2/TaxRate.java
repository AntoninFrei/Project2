package com.engeto.project2;

import org.json.JSONObject;

import java.math.BigDecimal;

public class TaxRate {
    Boolean isHas;
    BigDecimal rate;
    String czName;

    public Boolean getHas() {
        return isHas;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public TaxRate(JSONObject readedData, String country, String key, String czName) {

        try {
            this.rate = readedData.getJSONObject(country).getBigDecimal(key);
            this.isHas = true;

        }
        catch (org.json.JSONException e) {
            this.rate = new BigDecimal(0);
            this.isHas = false;
        }
        this.czName = czName;
    }

    public String getDescription() {
        String out = this.czName;
        //for(int i = out.length(); i < 24; i++) out += " ";
        out = new SetLength().setLength(24, out);

        if (this.isHas) {
            out += "Ano\t\t";
            if (this.rate.compareTo(new BigDecimal(10)) == -1) out += " ";
            return out + this.rate + " %";
        } else {
            return out + "Ne";
        }
    }






}
