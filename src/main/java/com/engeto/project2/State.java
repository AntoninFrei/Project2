package com.engeto.project2;

import java.math.BigDecimal;

public class State {


        String shortcut;
        String name;
        TaxRate standardRate;
        TaxRate reducedRate;
        TaxRate reducedRateAlt;
        TaxRate superReducedRate;
        TaxRate parkingRate;

        public State(String name, String shortcut, TaxRate standardRate, TaxRate reducedRate, TaxRate reducedRateAlt,
                     TaxRate superReducedRate, TaxRate parkingRate) {
                this.name = name;
                this.shortcut = shortcut;
                this.standardRate = standardRate;
                this.reducedRate = reducedRate;
                this.reducedRateAlt = reducedRateAlt;
                this.superReducedRate = superReducedRate;
                this.parkingRate = parkingRate;
        }

        public String getDescrtion() {
                String out = "Země:\t" + this.name;
                out += "\nZákladní sazba:\t" + standardRate.getDescription();
                out += "\n1. snížená sazba:\t" + reducedRate.getDescription();
                out += "\n2. snížená sazba:\t" + reducedRateAlt.getDescription();
                out += "\nSpeciální snížená sazba:\t" + superReducedRate.getDescription();
                out += "\nxxx:\t" + parkingRate.getDescription();
                out += "\n------------------------------------\n";


                return out;
        }

        public TaxRate getStandardRate() {
                return standardRate;
        }
}
