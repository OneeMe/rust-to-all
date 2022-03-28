package com.onee.rusty;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.uimanager.ViewManagerResolver;
import com.onee.rusty.glue.PropertyList;
import com.onee.rusty.glue.PropertyMap;
import com.onee.rusty.glue.UIManager;

import java.util.List;

public class RustHostUIManager extends UIManagerModule implements UIManager {
    public RustHostUIManager(ReactApplicationContext reactContext, List<ViewManager> viewManagersList, int minTimeLeftInFrameForNonBatchedOperationMs) {
        super(reactContext, viewManagersList, minTimeLeftInFrameForNonBatchedOperationMs);
    }

    @Override
    public void createView(int tag, @NonNull String class_name, int root_view_tag, @NonNull PropertyMap properties) {
        super.createView(tag, class_name, root_view_tag, RustReadableMap.of(properties));
    }

    @Override
    public void setChildren(int tag, @NonNull PropertyList children) {
        super.setChildren(tag, RustReadableArray.of(children));
    }
}
