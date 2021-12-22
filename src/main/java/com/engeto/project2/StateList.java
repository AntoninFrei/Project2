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
        for (State item : States) out += new SetLength().setLength(25, item.name) + "\t" + item.shortcut + "\n";



        return "Výpis všech státu a jejich zkratek:\n" + out;
    }


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

        Collections.sort(StatesSorted, new StatesVATComparator());




        return StatesSorted;

    }



}