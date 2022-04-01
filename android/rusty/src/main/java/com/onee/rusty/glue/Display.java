// Automatically generated by flapigen
package com.onee.rusty.glue;


public enum Display {
    flex(0),
    none(1);

    private final int value;
    Display(int value) {
        this.value = value;
    }
    public final int getValue() { return value; }
    /*package*/ static Display fromInt(int x) {
        switch (x) {
            case 0: return flex;
            case 1: return none;
            default: throw new Error("Invalid value for enum Display: " + x);
        }
    }
}