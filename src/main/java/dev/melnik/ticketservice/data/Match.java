package dev.melnik.ticketservice.data;

public enum Match {
    DORTMUND("ctl00_ContentMiddle_EventListImages1_GridView1_ctl03_SELECTEVENT"),
    MANCHESTER_CITY("ctl00_ContentMiddle_EventListImages1_GridView1_ctl0_SELECTEVENT");
    //another matches
//        DORTMUND("ctl00_ContentMiddle_EventListImages1_GridView1_ctl04_SELECTEVENT"),
//    MANCHESTER_CITY("ctl00_ContentMiddle_EventListImages1_GridView1_ctl05_SELECTEVENT");

    public final String id;

    Match(String id) {
        this.id = id;
    }
}
