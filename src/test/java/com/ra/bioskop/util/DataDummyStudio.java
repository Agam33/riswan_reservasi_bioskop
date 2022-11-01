package com.ra.bioskop.util;

import com.ra.bioskop.model.film.Studio;

import java.util.ArrayList;
import java.util.List;

public class DataDummyStudio {

    private List<Studio> STUDIOS = new ArrayList<>();

    public DataDummyStudio() {
        Studio st1 = new Studio();
        st1.setName("A");
        st1.setMaxSeat(30);
        st1.setSchedules(new ArrayList<>());

        Studio st2 = new Studio();
        st2.setName("B");
        st2.setMaxSeat(30);
        st2.setSchedules(new ArrayList<>());

        Studio st3 = new Studio();
        st3.setName("C");
        st3.setMaxSeat(30);
        st3.setSchedules(new ArrayList<>());

        STUDIOS.add(st1);
        STUDIOS.add(st2);
        STUDIOS.add(st3);
    }

    public List<Studio> getAllStudio() {
        return STUDIOS;
    }
}
