package com.engeto.project2;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class State {

        String name;
        List<String> shortcut = new ArrayList<>();
        TaxRate standardRate;
        TaxRate reducedRate;
        TaxRate reducedRateAlt;
        TaxRate superReducedRate;
        TaxRate parkingRate;

        public State(String name, String shortcut, TaxRate standardRate, TaxRate reducedRate, TaxRate reducedRateAlt,
                     TaxRate superReducedRate, TaxRate parkingRate) {
                this.name = name;
                this.shortcut.add(shortcut);
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

        public String getName() {
                return name;
        }

        public String getFirstShortCut() {
                return  this.shortcut.get(0);
        }

        public void addShorCut(String shortCut) {
                this.shortcut.add(shortCut);
        }

        public boolean isShortCutThisCoutry(String shortCut) {
                for (String shortC : this.shortcut) {
                        if (shortC.equals(shortCut.toUpperCase())) return true;

                }
                return false;
        }
}
