package com.onee.rusty;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.uimanager.ViewManagerResolver;

import java.util.List;

public class RustHostUIManager extends UIManagerModule {
    public RustHostUIManager(ReactApplicationContext reactContext, List<ViewManager> viewManagersList, int minTimeLeftInFrameForNonBatchedOperationMs) {
        super(reactContext, viewManagersList, minTimeLeftInFrameForNonBatchedOperationMs);
    }
}
