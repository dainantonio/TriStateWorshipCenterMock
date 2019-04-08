package com.example.dainr.tristateworshipcentermock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataSource {

    public static HashMap <String, List <String>> getData() {
        HashMap <String, List <String>> listDataChild = new HashMap <>();


        // Adding child data
        List <String> ministry = new ArrayList <>();
        ministry.add("Girl's Night Out");
        ministry.add("Journey Student Ministry");
        ministry.add("Men's Fellowship");
        ministry.add("Michael's Grace Place");
        ministry.add("Operation Christmas Child");
        ministry.add("Power House Kidz");
        ministry.add("SHIELD...Christian Singles Group");
        ministry.add("Women's Bible Study");
        ministry.add("W.I.N.G.S");

        listDataChild.put(ministry.get(0), Collections.singletonList("Girl's Night Out"));

        return listDataChild;
    }
}
