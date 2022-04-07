package com.onee.rusty;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.JavaOnlyArray;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.queue.MessageQueueThreadSpec;
import com.facebook.react.bridge.queue.QueueThreadExceptionHandler;
import com.facebook.react.bridge.queue.ReactQueueConfiguration;
import com.facebook.react.bridge.queue.ReactQueueConfigurationImpl;
import com.facebook.react.bridge.queue.ReactQueueConfigurationSpec;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.views.text.ReactRawTextManager;
import com.facebook.react.views.text.ReactTextViewManager;
import com.facebook.react.views.view.ReactViewManager;
import com.google.flatbuffers.FlatBufferBuilder;
import com.onee.rusty.glue.Display;
import com.onee.rusty.glue.Engine;
import com.onee.rusty.glue.FlexDirection;
import com.onee.rusty.glue.FlexWrap;
import com.onee.rusty.glue.FromJavaToRustBench;
import com.onee.rusty.glue.ViewProperty;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class RustyEngine {

    private static String TAG = "RustyEngine";
    private RustHostUIManager uiManager;
    private ReactApplicationContext reactContext;
    private Engine engine;

    static {
        System.loadLibrary("rustlib");
    }

    public RustyEngine(Context context) {
        reactContext = new ReactApplicationContext(context.getApplicationContext());
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
        uiManager = new RustHostUIManager(reactContext, viewManagers, 0);
        engine = new Engine(uiManager);
        engine.launch();
    }

    public void run(ReactRootView rootView) {
        int rootTag = uiManager.addRootView(rootView);
        Log.d(TAG, "root tag is " + rootTag);
        reactContext.runOnNativeModulesQueueThread(new Runnable() {
            @Override
            public void run() {
                engine.runApp(0);
            }
        });
    }
}
