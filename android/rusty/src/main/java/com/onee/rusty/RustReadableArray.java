package com.onee.rusty;

import androidx.annotation.NonNull;

import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.DynamicFromArray;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableType;
import com.onee.rusty.glue.CollectionList;
import com.onee.rusty.glue.CollectionValue;
import com.onee.rusty.glue.ValueType;

import java.util.ArrayList;
import java.util.Arrays;

public class RustReadableArray implements ReadableArray {
    final private CollectionList collectionList;
    RustReadableArray(CollectionList propertyList) {
        collectionList = propertyList;
    }
    public static RustReadableArray of (CollectionList propertyList) {
        return new RustReadableArray(propertyList);
    }

    // WriteOnce but not in the constructor fields
    private Object[] mLocalArray;
    private ReadableType[] mLocalTypeArray;

    private static int jniPassCounter = 0;

    public static int getJNIPassCounter() {
        return jniPassCounter;
    }

    private Object[] getLocalArray() {
        if (mLocalArray != null) {
            return mLocalArray;
        }
        synchronized (this) {
            // Make sure no concurrent call already updated
            if (mLocalArray == null) {
                jniPassCounter++;
                CollectionValue[] values = Assertions.assertNotNull(importArray());
                mLocalArray = new Object[values.length];
                for (int i = 0; i < values.length; i++) {
                    mLocalArray[i] = values[i].getValue();
                }
            }
        }
        return mLocalArray;
    }

    private CollectionValue[] importArray() {
        return collectionList.importValues();
    }

    private ReadableType[] getLocalTypeArray() {
        if (mLocalTypeArray != null) {
            return mLocalTypeArray;
        }
        synchronized (this) {
            // Make sure no concurrent call already updated
            if (mLocalTypeArray == null) {
                jniPassCounter++;
                ValueType[] tempArray = Assertions.assertNotNull(importTypeArray());
                mLocalTypeArray = Arrays.copyOf(tempArray, tempArray.length, ReadableType[].class);
            }
        }
        return mLocalTypeArray;
    }

    private ValueType[] importTypeArray() {
        return collectionList.importTypes();
    }

    @Override
    public int size() {
        return getLocalArray().length;
    }

    @Override
    public boolean isNull(int index) {
        return getLocalArray()[index] == null;
    }

    @Override
    public boolean getBoolean(int index) {
        return ((Boolean) getLocalArray()[index]).booleanValue();
    }

    @Override
    public double getDouble(int index) {
        return ((Double) getLocalArray()[index]).doubleValue();
    }

    @Override
    public int getInt(int index) {
        return ((Double) getLocalArray()[index]).intValue();
    }

    @Override
    public @NonNull String getString(int index) {
        return (String) getLocalArray()[index];
    }

    @Override
    public @NonNull RustReadableArray getArray(int index) {
        return (RustReadableArray) getLocalArray()[index];
    }

    @Override
    public @NonNull RustReadableMap getMap(int index) {
        return (RustReadableMap) getLocalArray()[index];
    }

    @Override
    public @NonNull ReadableType getType(int index) {
        return getLocalTypeArray()[index];
    }

    @Override
    public @NonNull Dynamic getDynamic(int index) {
        return DynamicFromArray.create(this, index);
    }

    @Override
    public int hashCode() {
        return getLocalArray().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RustReadableArray)) {
            return false;
        }
        RustReadableArray other = (RustReadableArray) obj;
        return Arrays.deepEquals(getLocalArray(), other.getLocalArray());
    }

    @Override
    public @NonNull ArrayList<Object> toArrayList() {
        ArrayList<Object> arrayList = new ArrayList<>();

        for (int i = 0; i < this.size(); i++) {
            switch (getType(i)) {
                case Null:
                    arrayList.add(null);
                    break;
                case Boolean:
                    arrayList.add(getBoolean(i));
                    break;
                case Number:
                    arrayList.add(getDouble(i));
                    break;
                case String:
                    arrayList.add(getString(i));
                    break;
                case Map:
                    arrayList.add(getMap(i).toHashMap());
                    break;
                case Array:
                    arrayList.add(getArray(i).toArrayList());
                    break;
                default:
                    throw new IllegalArgumentException("Could not convert object at index: " + i + ".");
            }
        }
        return arrayList;
    }
}
