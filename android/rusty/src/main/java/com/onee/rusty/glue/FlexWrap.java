// Automatically generated by flapigen
package com.onee.rusty.glue;


public enum FlexWrap {
    nowrap(0),
    wrap(1);

    private final int value;
    FlexWrap(int value) {
        this.value = value;
    }
    public final int getValue() { return value; }
    /*package*/ static FlexWrap fromInt(int x) {
        switch (x) {
            case 0: return nowrap;
            case 1: return wrap;
            default: throw new Error("Invalid value for enum FlexWrap: " + x);
        }
    }
}
