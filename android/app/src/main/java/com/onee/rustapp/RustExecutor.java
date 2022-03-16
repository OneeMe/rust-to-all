package com.onee.rustapp;

import android.util.Log;

import com.facebook.jni.HybridData;
import com.facebook.react.bridge.JavaScriptExecutor;
import com.facebook.react.bridge.WritableNativeMap;
import com.onee.rusty.NativeLib;

class RustExecutor extends JavaScriptExecutor {
    private static String TAG = "RustExecutor";
    protected RustExecutor(WritableNativeMap hybridData) {
        super(initData(hybridData));
        String str = NativeLib.hello("onee");
        Log.d(TAG, str);
    }

    @Override
    public String getName() {
        return "Rust";
    }

    private static HybridData initData(WritableNativeMap hybridData) {
        // TODO: fix this
        return null;
    }
}
