package com.engeto.project2;

import java.util.Comparator;

public class StatesVATComparator implements Comparator<State> {
    @Override
    public int compare(State first, State second) {
        int res = first.getStandardRate().getRate().compareTo(second.getStandardRate().getRate());

        return res;
    }
}
