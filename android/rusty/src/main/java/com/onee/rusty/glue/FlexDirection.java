// Automatically generated by flapigen
package com.onee.rusty.glue;


public enum FlexDirection {
    column(0),
    column_reverse(1),
    row(2),
    row_reverse(3);

    private final int value;
    FlexDirection(int value) {
        this.value = value;
    }
    public final int getValue() { return value; }
    /*package*/ static FlexDirection fromInt(int x) {
        switch (x) {
            case 0: return column;
            case 1: return column_reverse;
            case 2: return row;
            case 3: return row_reverse;
            default: throw new Error("Invalid value for enum FlexDirection: " + x);
        }
    }
}
