package com.onee.rusty;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.onee.rusty.glue.PropertyList;

import java.util.ArrayList;

public class RustReadableArray implements ReadableArray {
    RustReadableArray(PropertyList propertyList) {
    }
    public static RustReadableArray of (PropertyList propertyList) {
        return new RustReadableArray(propertyList);
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isNull(int index) {
        return false;
    }

    @Override
    public boolean getBoolean(int index) {
        return false;
    }

    @Override
    public double getDouble(int index) {
        return 0;
    }

    @Override
    public int getInt(int index) {
        return 0;
    }

    @NonNull
    @Override
    public String getString(int index) {
        return null;
    }

    @NonNull
    @Override
    public ReadableArray getArray(int index) {
        return null;
    }

    @NonNull
    @Override
    public ReadableMap getMap(int index) {
        return null;
    }

    @NonNull
    @Override
    public Dynamic getDynamic(int index) {
        return null;
    }

    @NonNull
    @Override
    public ReadableType getType(int index) {
        return null;
    }

    @NonNull
    @Override
    public ArrayList<Object> toArrayList() {
        return null;
    }
}
