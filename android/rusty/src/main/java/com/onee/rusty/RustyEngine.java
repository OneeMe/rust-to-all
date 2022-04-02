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
    static {
//        System.loadLibrary("rustlib");
    }

    private static String TAG = "RustyEngine";
    private RustHostUIManager uiManager;
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
        uiManager = new RustHostUIManager(reactContext, viewManagers, 0);
    }

    public void run(ReactRootView rootView) {
        System.loadLibrary("rustlib");
        engine = new Engine(uiManager);
        engine.launch();
        Bench bench = new Bench();
        FromJavaToRustBench fromJavaToRustBench =  engine.run_bench(bench);


        this.benchCall(3000, "empty", new Runnable() {
            @Override
            public void run() {
                fromJavaToRustBench.call_empty();
            }
        });
        this.benchCall(3000, "json-no-read", new Runnable() {
            @Override
            public void run() {
                com.onee.rusty.json.ViewProperty viewPropertyJson = getViewPropertyJson();
                String json = JSON.toJSONString(viewPropertyJson);
                fromJavaToRustBench.call_use_json(json, false);
            }
        });
        this.benchCall(3000, "json", new Runnable() {
            @Override
            public void run() {
                com.onee.rusty.json.ViewProperty viewPropertyJson = getViewPropertyJson();
                String json = JSON.toJSONString(viewPropertyJson);
                fromJavaToRustBench.call_use_json(json, true);
            }
        });
        this.benchCall(3000, "flapigen-no-read", new Runnable() {
            @Override
            public void run() {
                ViewProperty viewPropertyFlapigen = getViewPropertyFlapigen();
                fromJavaToRustBench.call_use_flapigen(viewPropertyFlapigen, false);
            }
        });
        this.benchCall(3000, "flapigen", new Runnable() {
            @Override
            public void run() {
                ViewProperty viewPropertyFlapigen = getViewPropertyFlapigen();
                fromJavaToRustBench.call_use_flapigen(viewPropertyFlapigen, true);
            }
        });
        this.benchCall(3000, "flatbuffer-no-read", new Runnable() {
            @Override
            public void run() {
                FlatBufferBuilder builder = getFlatBufferBuilder();
                ByteBuffer byteBuffer = builder.dataBuffer();
                fromJavaToRustBench.call_use_flatbuffer(byteBuffer.compact().array(), false);
            }
        });
        this.benchCall(3000, "flatbuffer", new Runnable() {
            @Override
            public void run() {
                FlatBufferBuilder builder = getFlatBufferBuilder();
                ByteBuffer byteBuffer = builder.dataBuffer();
                fromJavaToRustBench.call_use_flatbuffer(byteBuffer.compact().array(), true);
            }
        });
        int rootTag = uiManager.addRootView(rootView);
        Log.d(TAG, "root tag is " + rootTag);
        reactContext.runOnNativeModulesQueueThread(new Runnable() {
            @Override
            public void run() {
                engine.runApp(0);
            }
        });
    }

    @NonNull
    private FlatBufferBuilder getFlatBufferBuilder() {
        FlatBufferBuilder builder = new FlatBufferBuilder(0);
        int viewPropertyFlatbuffer = com.onee.rusty.model.ViewProperty.createViewProperty(
            builder,
            100.0,
            100.0,
            10.0,
            10.0,
            10.0,
            10.0,
            1,
            com.onee.rusty.model.Display.flex,
            com.onee.rusty.model.FlexDirection.column,
            -1,
            com.onee.rusty.model.FlexWrap.nowrap,
            builder.createString("hello_world"),
            builder.createString("a"),
            builder.createString("b"),
            builder.createString("c"),
            builder.createString("d"),
            builder.createString("e"),
            builder.createString("f"),
            builder.createString("g"),
            builder.createString("h"),
            builder.createString("i"),
            builder.createString("j"),
            builder.createString("k"),
            builder.createString("l"),
            builder.createString("m"),
            builder.createString("n"),
            builder.createString("o")
        );
        builder.finish(viewPropertyFlatbuffer);
        return builder;
    }

    @NonNull
    private com.onee.rusty.json.ViewProperty getViewPropertyJson() {
        com.onee.rusty.json.ViewProperty viewPropertyJson = new com.onee.rusty.json.ViewProperty(
                100.1d,
                100.1d,
                10.1d,
                10.1d,
                10.1d,
                10.1d,
                1,
                "flex",
                "column",
                -1,
                "nowrap",
                "hello_world",
                "a",
                "b",
                "c",
                "d",
                "e",
                "f",
                "g",
                "h",
                "i",
                "j",
                "k",
                "l",
                "m",
                "n",
                "o"
        );
        return viewPropertyJson;
    }

    @NonNull
    private ViewProperty getViewPropertyFlapigen() {
        ViewProperty viewPropertyFlapigen = new ViewProperty(
                100.0,
                100.0,
                10.0,
                10.0,
                10.0,
                10.0,
                1,
                Display.flex,
                FlexDirection.column,
                -1,
                FlexWrap.nowrap,
                "hello_world",
                "a",
                "b",
                "c",
                "d",
                "e",
                "f",
                "g",
                "h",
                "i",
                "j",
                "k",
                "l",
                "m",
                "n",
                "o"
        );
        return viewPropertyFlapigen;
    }

    private void benchCall(int count, String name, Runnable runnable) {
        long startTime = System.currentTimeMillis();
        runnable.run();
        long endTime = System.currentTimeMillis();
        Log.d(TAG, "name : " + name + " total time:" + (endTime - startTime) + "ms");
    }
}
