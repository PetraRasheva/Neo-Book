package com.service.school_service.enums;

import java.time.LocalTime;

public enum TimeSlot {
    SLOT_1(LocalTime.of(8, 0), LocalTime.of(8, 45)),
    SLOT_2(LocalTime.of(8, 50), LocalTime.of(9, 35)),
    SLOT_3(LocalTime.of(9, 40), LocalTime.of(10, 25)),
    SLOT_4(LocalTime.of(10, 30), LocalTime.of(11, 15)),
    SLOT_5(LocalTime.of(11, 20), LocalTime.of(12, 5)),
    SLOT_6(LocalTime.of(12, 10), LocalTime.of(12, 55)),
    SLOT_7(LocalTime.of(13, 00), LocalTime.of(13, 45)),
    SLOT_8(LocalTime.of(13, 50), LocalTime.of(14, 35)),
    SLOT_9(LocalTime.of(14, 40), LocalTime.of(15, 25)),
    SLOT_10(LocalTime.of(15, 30), LocalTime.of(16, 15)),
    SLOT_11(LocalTime.of(16, 20), LocalTime.of(17, 5)),
    SLOT_12(LocalTime.of(17, 10), LocalTime.of(17, 55)),
    SLOT_13(LocalTime.of(18, 00), LocalTime.of(18, 45));

    private final LocalTime start;
    private final LocalTime end;

    TimeSlot(LocalTime start, LocalTime end) {
        this.start = start;
        this.end = end;
    }

    public LocalTime getStart() {
        return start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public String getLabel() {
        return start + " - " + end;
    }
}
