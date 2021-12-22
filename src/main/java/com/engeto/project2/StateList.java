package com.engeto.project2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.*;

public class StateList {
    private List<State> States = new ArrayList<>();

    public void add(State state) {States.add(state);}

    public String toString(int start, int end) {
        String out = "";
        for(int i = start; i<end; i++) {
            out += "Pořadí: " + (i+1) + "\n";
            out += States.get(i).getDescrtion();
        }
        return out;
    }

    public int length() {
        return States.toArray().length;
    }

    public String getMin() {
        List<State> StatesOver = new ArrayList<>();
        Collections.sort(StatesOver, new StatesVATComparator());

        return "Státy z nejnižší základní sazbou:\n:" + toString(0, 3);
    }

    public String getSorted() {
        List<State> StatesOver = (new ArrayList<State>(States));
        //Collections.copy(StatesOver, States);
//        Collections.sort(StatesOver, new StatesVATComparator());
//
        String out = "";
//        for(int i = 0; i<10; i++) {
//            out += "Pořadí: " + (i+1) + "\n";
//            out += StatesOver.get(i).getDescrtion();
        Collections.sort(StatesOver, new StatesVATComparator());

        for (State item : StatesOver) out += item.getDescrtion() + "\n";
        out += "========================================================";


        return "Seřazeno podle základní sazby:\n:" + out;

    }



//    public void exportToFile(BigDecimal maxVAT) throws StateException {
//        String fileName = "vat-over-" + maxVAT;
//        fileName = fileName.replace('.', '_') + ".txt";
//        try (PrintWriter writer = new PrintWriter(new File(fileName))) {
//            writer.println(this.getOverValue(maxVAT));
//        } catch (FileNotFoundException e) {
//            throw  new StateException("Chyba při zápisu do souboru: " + fileName +"!");
//        }
//    }
//
//
//    public String getOverValue(BigDecimal maxVAT) {
//
//        List<State> StatesOver = new ArrayList<>();
//        String otherState = "\nSazba VAT " + maxVAT + " % nebo nižší nebo používají speciální sazbu: ";
//        String out = "";
//        for (State item : States) {
//            int res = item.fullVAT.compareTo(maxVAT);
//            if (res > 0 && ! item.hasSpecialVAT) {
//                StatesOver.add(item);
//            }
//            else {
//                otherState += item.shortcut + ", ";
//            }
//
//        }
//
//        Collections.sort(StatesOver, new StatesVATComparator());
//
//        for (State item : StatesOver) out += item.getStateBothInfo() + "\n";
//        out += "========================================================";
//
//
//
//        return out + otherState;
//    }
//
//    public String getAllStatesBasicInfo() {
//        String out = "";
//        for (State item : States) out += item.getStateBasicInfo() + "\n";
//        return out;
//    }

}