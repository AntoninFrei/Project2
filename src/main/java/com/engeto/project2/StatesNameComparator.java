package com.engeto.project2;

import java.util.Comparator;

public class StatesNameComparator implements Comparator<State> {
    @Override
    public int compare(State first, State second) {
        return first.getName().compareTo(second.getName());
    }
}
