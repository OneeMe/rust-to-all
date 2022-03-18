package com.onee.rustapp;


import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.JavaOnlyArray;
import com.facebook.react.bridge.JavaOnlyMap;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.queue.MessageQueueThreadSpec;
import com.facebook.react.bridge.queue.QueueThreadExceptionHandler;
import com.facebook.react.bridge.queue.ReactQueueConfiguration;
import com.facebook.react.bridge.queue.ReactQueueConfigurationImpl;
import com.facebook.react.bridge.queue.ReactQueueConfigurationSpec;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.modules.core.PermissionAwareActivity;
import com.facebook.react.modules.core.PermissionListener;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.views.text.ReactRawTextManager;
import com.facebook.react.views.text.ReactTextViewManager;
import com.facebook.react.views.view.ReactViewManager;

import java.util.ArrayList;
import java.util.List;

public class RustHostActivity extends AppCompatActivity implements DefaultHardwareBackBtnHandler, PermissionAwareActivity {
    private UIManagerModule uiManagerModule;
    private static String TAG = "RustHostActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rust_host);
        ViewGroup root = findViewById(R.id.root_layout);

        ReactRootView reactRootView = new ReactRootView(this);
        reactRootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        reactRootView.setBackgroundColor(Color.GRAY);
        root.addView(reactRootView);

        ReactApplicationContext reactContext = new ReactApplicationContext(getApplicationContext());
        ReactQueueConfigurationSpec spec =
                ReactQueueConfigurationSpec.builder()
                        .setJSQueueThreadSpec(MessageQueueThreadSpec.mainThreadSpec())
                        .setNativeModulesQueueThreadSpec(MessageQueueThreadSpec.mainThreadSpec())
                        .build();
        ReactQueueConfiguration queueConfiguration = ReactQueueConfigurationImpl.create(
                spec,
                new QueueThreadExceptionHandler() {
                    @Override
                    public void handleException(Exception e) {
                        Log.e(TAG, e.getLocalizedMessage());
                    }
                }
        );
        RustCatalystInstance rustCatalystInstance = new RustCatalystInstance(queueConfiguration);
        reactContext.initializeWithInstance(rustCatalystInstance);
        List<ViewManager> viewManagers = new ArrayList<>();
        viewManagers.add(new ReactViewManager());
        viewManagers.add(new ReactTextViewManager());
        viewManagers.add(new ReactRawTextManager());
        uiManagerModule = new UIManagerModule(reactContext, viewManagers, 0);
        uiManagerModule.onHostResume();
        int rootTag = uiManagerModule.addRootView(reactRootView);
        uiManagerModule.updateRootLayoutSpecs(rootTag, View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), 0, 0);
        Log.d(TAG, "root tag is " + rootTag);


        reactContext.runOnNativeModulesQueueThread(new Runnable() {
            @Override
            public void run() {
                uiManagerModule.createView(rootTag + 1, "RCTView", rootTag, JavaOnlyMap.of("backgroundColor", -65536, "width", 100, "height", 100, "left", 10.0, "top", 20.0, "collapsable", false));
                uiManagerModule.createView(rootTag + 2, "RCTView", rootTag, JavaOnlyMap.of("width", 50, "height", 50, "backgroundColor", Color.BLACK));
                uiManagerModule.setChildren(rootTag, JavaOnlyArray.of(rootTag + 1));
                uiManagerModule.setChildren(rootTag  + 1, JavaOnlyArray.of(rootTag + 2));
                uiManagerModule.onBatchComplete();
            }
        });
    }

    @Override
    public void invokeDefaultOnBackPressed() {
        // TODO
    }

    @Override
    public void requestPermissions(String[] permissions, int requestCode, PermissionListener listener) {
        // TODO
    }
}
