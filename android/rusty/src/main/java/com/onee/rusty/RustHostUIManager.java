package com.onee.rusty;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.JavaOnlyArray;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.uimanager.ViewManagerResolver;
import com.onee.rusty.glue.CollectionMap;
import com.onee.rusty.glue.UIManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RustHostUIManager extends UIManagerModule implements UIManager {
    public RustHostUIManager(ReactApplicationContext reactContext, List<ViewManager> viewManagersList, int minTimeLeftInFrameForNonBatchedOperationMs) {
        super(reactContext, viewManagersList, minTimeLeftInFrameForNonBatchedOperationMs);
    }

    @Override
    public void createView(int tag, @NonNull String class_name, int root_view_tag, @NonNull CollectionMap properties) {
        super.createView(tag, class_name, root_view_tag, RustReadableMap.of(properties));
    }

    @Override
    public void setChildren(int tag, @NonNull int[] children) {
        // TODO: RN 居然这里都要使用动态类型，后续得想办法改掉
        List<Integer> intList = new ArrayList<Integer>(children.length);
        for (int i : children)
        {
            intList.add(i);
        }
        super.setChildren(tag, JavaOnlyArray.from(intList));
    }
}
