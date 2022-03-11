package com.rn;


import com.facebook.react.fabric.ReactNativeConfig;

/**
 * An empty {@link ReactNativeConfig} that is returning empty responses and false for all the
 * requested keys.
 */
public class EmptyReactNativeConfig implements ReactNativeConfig {

    @Override
    public boolean getBool(final String s) {
        return false;
    }

    @Override
    public int getInt64(final String s) {
        return 0;
    }

    @Override
    public String getString(final String s) {
        return "";
    }

    @Override
    public double getDouble(final String s) {
        return 0;
    }
}
