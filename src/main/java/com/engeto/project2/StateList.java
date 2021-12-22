package com.engeto.project2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.*;

public class StateList {
    private List<State> States = new ArrayList<>();

    public void add(State state) {States.add(state);}



    public String toString(int start, int end, List<State> StatesSorted) {
        String out = "";

        for(int i = start; i<end; i++) {
            out += "Pořadí:\t\t\t\t\t\t" + (i+1) + "\n";
            out += StatesSorted.get(i).getDescrtion();
        }
        return out;
    }

//    public int length() {
//        return States.toArray().length;
//    }

    public String getMin() {
        List<State> StatesSorted = getSorted();
        return "Státy z nejnižší základní sazbou:\n" + toString(0, 3, StatesSorted);
    }

    public String getMax() {
        List<State> StatesSorted = getSorted();
        return "Státy z nejvyšší základní sazbou:\n" + toString(StatesSorted.toArray().length - 3,
                StatesSorted.toArray().length, StatesSorted);
    }

    public String getAllNames() {
        String out = "";
        for (State item : States) out += item.name + "\t" + item.shortcut + "\n";



        return "Výpis všech státu:\n" + out;
    }

//    public void setAltShortCut(State state) {
//        for (State item : States) {
//            if (state.getName().equals(item.getName())) item.addShorCut(state.getFirstShortCut());
//        }
//
//
//    }

    public State getItemWithName(State state) {
        for (State item : States) {
            if (state.getName().equals(item.getName())) return item;
        }
        return null;
    }

    public String getWithShort(String shortCut) {
        for (State item : States) {
            if (item.isShortCutThisCoutry(shortCut)) return item.getDescrtion();
        }
        return "Nenalezena země se zkratkou " + shortCut + " !\n";
    }

    public boolean isDuplicate(State state) {
        for (State item : States) {
            if (state.getName().equals(item.getName())) return true;
        }
        return false;
    }

    public List<State> getSorted() {
        List<State> StatesSorted = (new ArrayList<State>(States));
        //Collections.copy(StatesSorted, States);
//        Collections.sort(StatesSorted, new StatesVATComparator());
//
//        String out = "";
//        for(int i = 0; i<10; i++) {
//            out += "Pořadí: " + (i+1) + "\n";
//            out += StatesSorted.get(i).getDescrtion();
        Collections.sort(StatesSorted, new StatesVATComparator());

//        for (State item : StatesSorted) out += item.getDescrtion() + "\n";
//        out += "========================================================";


        return StatesSorted;

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