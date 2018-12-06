package model;

import java.util.Comparator;

public class SortHistory implements Comparator<Transactions> {

    @Override
    public int compare(Transactions a, Transactions b) {
        return b.getTimestamp().compareTo(a.getTimestamp());
    }
}
