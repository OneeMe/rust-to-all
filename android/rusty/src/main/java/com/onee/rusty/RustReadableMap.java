package com.onee.rusty;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.DynamicFromMap;
import com.facebook.react.bridge.NoSuchKeyException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.UnexpectedNativeTypeException;
import com.onee.rusty.glue.CollectionMap;
import com.onee.rusty.glue.CollectionValue;
import com.onee.rusty.glue.ValueType;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
// Fork From com.facebook.react.bridge.ReadableNativeMap
public class RustReadableMap implements ReadableMap {
    private final CollectionMap collectionMap;
    private @Nullable String[] mKeys;
    private @Nullable HashMap<String, Object> mLocalMap;
    private @Nullable HashMap<String, ValueType> mLocalTypeMap;
    private static int mJniCallCounter;

    private RustReadableMap(CollectionMap mPropertyMap) {
        this.collectionMap = mPropertyMap;
    }

    public static RustReadableMap of(CollectionMap propertyMap) {
        return new RustReadableMap(propertyMap);
    }

    public static int getJNIPassCounter() {
        return mJniCallCounter;
    }

    private HashMap<String, Object> getLocalMap() {
        if (mLocalMap != null) {
            return mLocalMap;
        }
        synchronized (this) {
            if (mKeys == null) {
                mKeys = Assertions.assertNotNull(importKeys());
                mJniCallCounter++;
            }
            if (mLocalMap == null) {
                CollectionValue[] values = Assertions.assertNotNull(importValues());
                mJniCallCounter++;
                int length = mKeys.length;
                mLocalMap = new HashMap<>(length);
                for (int i = 0; i < length; i++) {
                    mLocalMap.put(mKeys[i], values[i].getValue());
                }
            }
        }
        return mLocalMap;
    }

    private String[] importKeys() {
        return collectionMap.importKeys();
    }

    private CollectionValue[] importValues() {
    return collectionMap.importValues();
    }

    private @NonNull HashMap<String, ValueType> getLocalTypeMap() {
        if (mLocalTypeMap != null) {
            return mLocalTypeMap;
        }
        synchronized (this) {
            if (mKeys == null) {
                mKeys = Assertions.assertNotNull(importKeys());
                mJniCallCounter++;
            }
            // check that no other thread has already updated
            if (mLocalTypeMap == null) {
                ValueType[] types = Assertions.assertNotNull(importTypes());
                mJniCallCounter++;
                int length = mKeys.length;
                mLocalTypeMap = new HashMap<>(length);
                for (int i = 0; i < length; i++) {
                    mLocalTypeMap.put(mKeys[i], types[i] );
                }
            }
        }
        return mLocalTypeMap;
    }

    private ValueType[] importTypes() {
        return collectionMap.importTypes();
    }

    @Override
    public boolean hasKey(@NonNull String name) {
        return getLocalMap().containsKey(name);
    }

    @Override
    public boolean isNull(@NonNull String name) {
        if (getLocalMap().containsKey(name)) {
            return getLocalMap().get(name) == null;
        }
        throw new NoSuchKeyException(name);
    }

    private @NonNull Object getValue(@NonNull String name) {
        if (hasKey(name) && !(isNull(name))) {
            return Assertions.assertNotNull(getLocalMap().get(name));
        }
        throw new NoSuchKeyException(name);
    }

    private <T> T getValue(String name, Class<T> type) {
        Object value = getValue(name);
        checkInstance(name, value, type);
        return (T) value;
    }

    private @Nullable Object getNullableValue(String name) {
        if (hasKey(name)) {
            return getLocalMap().get(name);
        }
        return null;
    }

    private @Nullable <T> T getNullableValue(String name, Class<T> type) {
        Object value = getNullableValue(name);
        checkInstance(name, value, type);
        return (T) value;
    }

    private void checkInstance(String name, Object value, Class type) {
        if (value != null && !type.isInstance(value)) {
            throw new UnexpectedNativeTypeException(
                    "Value for "
                            + name
                            + " cannot be cast from "
                            + value.getClass().getSimpleName()
                            + " to "
                            + type.getSimpleName());
        }
    }

    @Override
    public boolean getBoolean(@NonNull String name) {
        return getValue(name, Boolean.class).booleanValue();
    }

    @Override
    public double getDouble(@NonNull String name) {
        return getValue(name, Double.class).doubleValue();
    }

    @Override
    public int getInt(@NonNull String name) {
        // All numbers coming out of native are doubles, so cast here then truncate
        return getValue(name, Double.class).intValue();
    }

    @Override
    public @Nullable String getString(@NonNull String name) {
        return getNullableValue(name, String.class);
    }

    @Override
    public @Nullable ReadableArray getArray(@NonNull String name) {
        return getNullableValue(name, ReadableArray.class);
    }

    @Override
    public @Nullable RustReadableMap getMap(@NonNull String name) {
        return getNullableValue(name, RustReadableMap.class);
    }

    @Override
    public @NonNull ReadableType getType(@NonNull String name) {
        if (getLocalTypeMap().containsKey(name)) {
            ValueType type = Assertions.assertNotNull(getLocalTypeMap().get(name));
            switch (type) {
                case Null:
                    return ReadableType.Null;
                case Number:
                    return ReadableType.Number;
                case Boolean:
                    return ReadableType.Boolean;
                case String:
                    return ReadableType.String;
                case Map:
                    return ReadableType.Map;
                case Array:
                    return ReadableType.Array;
                default:
                    throw new IllegalStateException("Unexpected value: " + type);
            }
        }
        throw new NoSuchKeyException(name);
    }

    @Override
    public @NonNull Dynamic getDynamic(@NonNull String name) {
        return DynamicFromMap.create(this, name);
    }

    @Override
    public @NonNull Iterator<Map.Entry<String, Object>> getEntryIterator() {
        // TODO: 在 RN 的原本视线中，这里是还继续调用 JNI 来获取内容的，不知道为什么
        Map<String, Object> localMap =  getLocalMap();
        return localMap.entrySet().iterator();
    }

    @Override
    public @NonNull ReadableMapKeySetIterator keySetIterator() {
        if (mKeys == null) {
            mKeys = Assertions.assertNotNull(importKeys());
        }
        final String[] iteratorKeys = mKeys;
        return new ReadableMapKeySetIterator() {
            int currentIndex = 0;

            @Override
            public boolean hasNextKey() {
                return currentIndex < iteratorKeys.length;
            }

            @Override
            public String nextKey() {
                return iteratorKeys[currentIndex++];
            }
        };
    }

    @Override
    public int hashCode() {
        return getLocalMap().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RustReadableMap)) {
            return false;
        }
        RustReadableMap other = (RustReadableMap) obj;
        return getLocalMap().equals(other.getLocalMap());
    }

    @Override
    public @NonNull HashMap<String, Object> toHashMap() {
        // we can almost just return getLocalMap(), but we need to convert nested arrays and maps to the
        // correct types first
        HashMap<String, Object> hashMap = new HashMap<>(getLocalMap());
        Iterator iterator = hashMap.keySet().iterator();

        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            switch (getType(key)) {
                case Null:
                case Boolean:
                case Number:
                case String:
                    break;
                case Map:
                    hashMap.put(key, Assertions.assertNotNull(getMap(key)).toHashMap());
                    break;
                case Array:
                    hashMap.put(key, Assertions.assertNotNull(getArray(key)).toArrayList());
                    break;
                default:
                    throw new IllegalArgumentException("Could not convert object with key: " + key + ".");
            }
        }
        return hashMap;
    }

    private static class RustReadableMapKeySetIterator implements ReadableMapKeySetIterator {
        private final Iterator<String> mIterator;

        public RustReadableMapKeySetIterator(RustReadableMap readableNativeMap) {
            mIterator = readableNativeMap.getLocalMap().keySet().iterator();
        }

        @Override
        public boolean hasNextKey() {
            return mIterator.hasNext();
        }

        @Override
        public String nextKey() {
            return mIterator.next();
        }
    }
}
