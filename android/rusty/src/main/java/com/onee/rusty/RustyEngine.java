package com.onee.rusty;

import android.content.Context;
import android.util.Log;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;
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

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class RustyEngine {
    static {
        System.loadLibrary("rustlib");
    }

    private static String TAG = "RustyEngine";
    private UIManagerModule uiManagerModule;
    private ReactApplicationContext reactContext;

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
        uiManagerModule = new UIManagerModule(reactContext, viewManagers, 0);
    }

    public void run() {
        try {
            Class[] classes = new Class[4];
            classes[0] = int.class;
            classes[1] = String.class;
            classes[2] = int.class;
            classes[3] = ReadableMap.class;
            Method method = uiManagerModule.getClass().getMethod("createView", classes);
            Log.d(TAG, JNIUtil.getJNIMethodSignature(method));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        reactContext.runOnNativeModulesQueueThread(new Runnable() {
            @Override
            public void run() {
                CommandList commandList = launch();
                for (int i = 0; i< commandList.len(); i++) {
                    Command command = commandList.at(i);
                    switch (command.get_commandType()) {
                        case SetChild:
                            break;
                        case CreateView:
                            break;
                        default:
                    }
                }
            }
        });
    }

    private native CommandList launch();

    public static void tapTap() {
        Log.d(TAG, "tapTap hello");
    }
}
