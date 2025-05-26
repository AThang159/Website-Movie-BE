package com.athang159.iuh.website_movie.enums;

public enum TimeSlot {
    TIME_09_00("09:00"),
    TIME_11_00("11:00"),
    TIME_13_00("13:00"),
    TIME_15_00("15:00"),
    TIME_17_00("17:00"),
    TIME_19_00("19:00"),
    TIME_21_00("21:00");

    private final String label;

    TimeSlot(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

