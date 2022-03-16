package com.onee.rustapp;


import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.react.ReactRootView;
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
        reactRootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
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
        viewManagers.add(new ReactViewManager());
        viewManagers.add(new ReactViewManager());
        uiManagerModule = new UIManagerModule(reactContext, viewManagers, 0);
        int rootTag = uiManagerModule.addRootView(reactRootView);

        reactContext.runOnNativeModulesQueueThread(new Runnable() {
            @Override
            public void run() {
                uiManagerModule.createView(1, "RCTView", rootTag, JavaOnlyMap.of("backgroundColor", Color.RED));
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
