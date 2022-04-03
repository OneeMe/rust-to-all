package com.onee.rusty;

import android.view.View;

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

public class RustHostUIManager extends UIManagerModule implements UIManager, RustRootView.RustRootViewDelegate {
    public RustHostUIManager(ReactApplicationContext reactContext, List<ViewManager> viewManagersList, int minTimeLeftInFrameForNonBatchedOperationMs) {
        super(reactContext, viewManagersList, minTimeLeftInFrameForNonBatchedOperationMs);
    }

    @Override
    public <T extends View> int addRootView(T rootView) {
        int tag = super.addRootView(rootView);
        if (rootView instanceof RustRootView) {
            ((RustRootView) rootView).setDelegate(this);
            ((RustRootView) rootView).setRustRootViewTag(tag);
        }
        return tag;
    }

    private JavaOnlyArray toArray(int[] array) {
        // TODO: RN 居然这里都要使用动态类型，后续得想办法改掉
        List<Integer> intList = new ArrayList<Integer>(array.length);
        for (int i : array)
        {
            intList.add(i);
        }
        return JavaOnlyArray.from(intList);
    }

    @Override
    public void createView(int tag, @NonNull String class_name, int root_view_tag, @NonNull CollectionMap properties) {
        super.createView(tag, class_name, root_view_tag, RustReadableMap.of(properties));
    }

    @Override
    public void setChildren(int tag, @NonNull int[] children) {
        super.setChildren(tag,toArray(children) );
    }

    @Override
    public void updateView(int tag, @NonNull String class_name, @NonNull CollectionMap properties) {
        super.updateView(tag, class_name, RustReadableMap.of(properties));
    }

    @Override
    public void manageChildren(int tag, @NonNull int[] move_from, @NonNull int[] move_to, @NonNull int[] added_children, @NonNull int[] add_at_indices, @NonNull int[] remove_from) {
        super.manageChildren(tag, toArray(move_from), toArray(move_to), toArray(added_children), toArray(add_at_indices), toArray(remove_from));
    }

    @Override
    public void updateLayoutSpec(int tag, int widthMeasureSpec, int heightMeasureSpec) {
        super.updateRootLayoutSpecs(tag, widthMeasureSpec, heightMeasureSpec, 0, 0);
    }
}
