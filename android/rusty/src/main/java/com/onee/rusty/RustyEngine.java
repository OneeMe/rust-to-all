package com.onee.rusty;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.JavaOnlyArray;
import com.facebook.react.bridge.JavaOnlyMap;
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
import com.onee.rusty.glue.Command;
import com.onee.rusty.glue.CommandList;
import com.onee.rusty.glue.Engine;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class RustyEngine {
    static {
//        System.loadLibrary("rustlib");
    }

    private static String TAG = "RustyEngine";
    private UIManagerModule uiManagerModule;
    private ReactApplicationContext reactContext;
    private Engine engine;

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

    public void run(ReactRootView rootView) {
        System.loadLibrary("rustlib");
        engine = new Engine();
        engine.launch();
        int rootTag = uiManagerModule.addRootView(rootView);
        uiManagerModule.updateRootLayoutSpecs(rootTag, View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), 0, 0);
        Log.d(TAG, "root tag is " + rootTag);
        reactContext.runOnNativeModulesQueueThread(new Runnable() {
            @Override
            public void run() {
                CommandList commandList = engine.runApp(0);
                for (int i = 0; i< commandList.length(); i++) {
                    Command command = commandList.get(i);
                    switch (command.commandType()) {
                        case SetChild:
                            Log.d(TAG, "Type is create view, view id is " + command.tag() + ", child is " + command.rootViewTag());
                            uiManagerModule.setChildren((int)command.tag(), JavaOnlyArray.of((int)command.rootViewTag()));
                            break;
                        case CreateView:
                            Log.d(TAG, "Type is create view, view id is " + command.tag() + ", viewName is " + command.className() + ", prop is " + command.properties().toMap());
                            uiManagerModule.createView((int)command.tag(), command.className(), (int)command.rootViewTag(), command.properties().toMap());
                            break;
                        default:
                    }
                }
                uiManagerModule.onBatchComplete();
            }
        });
    }
}
