package com.onee.rusty;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.gson.Gson;
import com.onee.rusty.glue.Display;
import com.onee.rusty.glue.FlexDirection;
import com.onee.rusty.glue.FlexWrap;
import com.onee.rusty.glue.FromJavaToRustBench;
import com.onee.rusty.glue.FromRustToJavaBench;
import com.onee.rusty.glue.ViewProperty;

import java.nio.ByteBuffer;
import java.time.Duration;
import java.util.ArrayList;
import java.util.function.Consumer;

public class Bench implements FromRustToJavaBench {
    private static String TAG = "FromRustToJavaBench";
    private Context context;

    public Bench(Context context) {
        this.context = context;
    }

    private void log(String content) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, content);
        }
    }

    private int getBenchIndex() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("rust-bench", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        int value = sharedPreferences.getInt("index", -1);
        value += 1;
        if (value >= 14) {
            value = 0;
        }
        editor.putInt("index", value);
        editor.commit();
        return value;
    }

    public void run() {
        FromJavaToRustBench fromJavaToRustBench = new FromJavaToRustBench();
        int value = getBenchIndex();
        fromJavaToRustBench.run_bench(value, this);
        switch (value) {
            case 7:
                this.benchCall(17000000, "empty", new Runnable() {
                    @Override
                    public void run() {
                        fromJavaToRustBench.call_empty();
                    }
                });
                break;
            case 8:
                this.benchCall(135000,"flatbuffer-no-read", new Runnable() {
                    @Override
                    public void run() {
                        FlatBufferBuilder builder = getFlatBufferBuilder();
                        ByteBuffer byteBuffer = builder.dataBuffer();
                        fromJavaToRustBench.call_use_flatbuffer(byteBuffer.compact().array(), false);
                    }
                });
                break;
            case 9:
                this.benchCall(100000,"flatbuffer", new Runnable() {
                    @Override
                    public void run() {
                        FlatBufferBuilder builder = getFlatBufferBuilder();
                        ByteBuffer byteBuffer = builder.dataBuffer();
                        fromJavaToRustBench.call_use_flatbuffer(byteBuffer.compact().array(), true);
                    }
                });
            break;
            case 10:
                this.benchCall(50000,"json-no-read", new Runnable() {
                    @Override
                    public void run() {
                        com.onee.rusty.json.ViewProperty viewPropertyJson = getViewPropertyJson();
                        String json = JSON.toJSONString(viewPropertyJson);
                        fromJavaToRustBench.call_use_json(json, false);
                    }
                });
                break;
            case 11:
                this.benchCall(40000,"json", new Runnable() {
                    @Override
                    public void run() {
                        com.onee.rusty.json.ViewProperty viewPropertyJson = getViewPropertyJson();
                        String json = JSON.toJSONString(viewPropertyJson);
                        fromJavaToRustBench.call_use_json(json, true);
                    }
                });
                break;
            case 12:
                this.benchCall(160000,"flapigen-no-read", new Runnable() {
                    @Override
                    public void run() {
                        ViewProperty viewPropertyFlapigen = getViewPropertyFlapigen();
                        fromJavaToRustBench.call_use_flapigen(viewPropertyFlapigen, false);
                    }
                });
                break;
            case 13:
                this.benchCall(130000,"flapigen", new Runnable() {
                    @Override
                    public void run() {
                        ViewProperty viewPropertyFlapigen = getViewPropertyFlapigen();
                        fromJavaToRustBench.call_use_flapigen(viewPropertyFlapigen, true);
                    }
                });
                break;
            default:
                Log.d(TAG, "not run in index:" + value);
        }
    }

    @Override
    public void callEmpty() {

    }

    @Override
    public void callUseFlapigen(@NonNull ViewProperty args, boolean read) {
        if (read) {
            ViewProperty viewProperty = args;
            log("callUseFlapigen-width: " +viewProperty.width());
            log("callUseFlapigen-height: " +viewProperty.height());
            log("callUseFlapigen-marginLeft: " +viewProperty.marginLeft());
            log("callUseFlapigen-marginRight: " + viewProperty.marginRight());
            log("callUseFlapigen-marginTop: " + viewProperty.marginTop());
            log("callUseFlapigen-marginBottom: " + viewProperty.marginBottom());
            log("callUseFlapigen-flex: " + viewProperty.flex());
            log("callUseFlapigen-display: " + viewProperty.display());
            log("callUseFlapigen-flexDirection: " + viewProperty.flexDirection());
            log("callUseFlapigen-backgroundColor: " + viewProperty.backgroundColor());
            log("callUseFlapigen-flexWrap: " + viewProperty.flexWrap());
            log("callUseFlapigen-content: " + viewProperty.content());
            log("callUseFlapigen-a:" + viewProperty.a());
            log("callUseFlapigen-b:" + viewProperty.b());
            log("callUseFlapigen-c:" + viewProperty.c());
            log("callUseFlapigen-d:" + viewProperty.d());
            log("callUseFlapigen-e:" + viewProperty.e());
            log("callUseFlapigen-f:" + viewProperty.f());
            log("callUseFlapigen-g:" + viewProperty.g());
            log("callUseFlapigen-h:" + viewProperty.h());
            log("callUseFlapigen-i:" + viewProperty.i());
            log("callUseFlapigen-j:" + viewProperty.j());
            log("callUseFlapigen-k:" + viewProperty.k());
            log("callUseFlapigen-l:" + viewProperty.l());
            log("callUseFlapigen-m:" + viewProperty.m());
            log("callUseFlapigen-n:" + viewProperty.n());
            log("callUseFlapigen-o:" + viewProperty.o());
        }
    }

    @Override
    public void callUseJson(@NonNull String args, boolean read) {
        if (read) {
            Gson gson = new Gson();
            com.onee.rusty.json.ViewProperty viewProperty = gson.fromJson(args, com.onee.rusty.json.ViewProperty.class);
            log("callUseJson-width: " + viewProperty.getWidth());
            log("callUseJson-height: " + viewProperty.getHeight());
            log("callUseJson-marginLeft: " + viewProperty.getMarginLeft());
            log("callUseJson-marginRight: " + viewProperty.getMarginRight());
            log("callUseJson-marginTop: " + viewProperty.getMarginTop());
            log("callUseJson-marginBottom: " + viewProperty.getMarginBottom());
            log("callUseJson-flex: " + viewProperty.getFlex());
            log("callUseJson-display: " + viewProperty.getDisplay());
            log("callUseJson-flexDirection: " + viewProperty.getFlexDirection());
            log("callUseJson-backgroundColor: " + viewProperty.getBackgroundColor());
            log("callUseJson-flexWrap: " + viewProperty.getFlexWrap());
            log("callUseJson-flexWrap: " + viewProperty.getFlexWrap());
            log("callUseJson-content: " + viewProperty.getContent());
            log("callUseJson-a:" + viewProperty.getA());
            log("callUseJson-b:" + viewProperty.getB());
            log("callUseJson-c:" + viewProperty.getC());
            log("callUseJson-d:" + viewProperty.getD());
            log("callUseJson-e:" + viewProperty.getE());
            log("callUseJson-f:" + viewProperty.getF());
            log("callUseJson-g:" + viewProperty.getG());
            log("callUseJson-h:" + viewProperty.getH());
            log("callUseJson-i:" + viewProperty.getI());
            log("callUseJson-j:" + viewProperty.getJ());
            log("callUseJson-k:" + viewProperty.getK());
            log("callUseJson-l:" + viewProperty.getL());
            log("callUseJson-m:" + viewProperty.getM());
            log("callUseJson-n:" + viewProperty.getN());
            log("callUseJson-o:" + viewProperty.getO());
        }
    }

    @Override
    public void callUseFlatbuffer(@NonNull byte[] args, boolean read) {
        if (read) {
            ByteBuffer buffer = ByteBuffer.wrap(args);
            com.onee.rusty.model.ViewProperty viewProperty = com.onee.rusty.model.ViewProperty.getRootAsViewProperty(buffer);
            log("callUseFlatbuffers-width: " + viewProperty.width());
            log("callUseFlatbuffers-height: " + viewProperty.height());
            log("callUseFlatbuffers-marginLeft: " + viewProperty.marginLeft());
            log("callUseFlatbuffers-marginRight: " + viewProperty.marginRight());
            log("callUseFlatbuffers-marginTop: " + viewProperty.marginTop());
            log("callUseFlatbuffers-marginBottom: " + viewProperty.marginBottom());
            log("callUseFlatbuffers-flex: " + viewProperty.flex());
            log("callUseFlatbuffers-display: " + viewProperty.display());
            log("callUseFlatbuffers-flexDirection: " + viewProperty.flexDirection());
            log("callUseFlatbuffers-backgroundColor: " + viewProperty.backgroundColor());
            log("callUseFlatbuffers-flexWrap: " + viewProperty.flexWrap());
            log("callUseFlatbuffers-content: " + viewProperty.content());
            log("callUseFlatbuffers-a:" + viewProperty.a());
            log("callUseFlatbuffers-b:" + viewProperty.b());
            log("callUseFlatbuffers-c:" + viewProperty.c());
            log("callUseFlatbuffers-d:" + viewProperty.d());
            log("callUseFlatbuffers-e:" + viewProperty.e());
            log("callUseFlatbuffers-f:" + viewProperty.f());
            log("callUseFlatbuffers-g:" + viewProperty.g());
            log("callUseFlatbuffers-h:" + viewProperty.h());
            log("callUseFlatbuffers-i:" + viewProperty.i());
            log("callUseFlatbuffers-j:" + viewProperty.j());
            log("callUseFlatbuffers-k:" + viewProperty.k());
            log("callUseFlatbuffers-l:" + viewProperty.l());
            log("callUseFlatbuffers-m:" + viewProperty.m());
            log("callUseFlatbuffers-n:" + viewProperty.n());
            log("callUseFlatbuffers-o:" + viewProperty.o());
        }
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
        long localStartTime = System.nanoTime();
        for (int i = 0; i < count; i++) {
            runnable.run();
        }
        long localEndtTime = System.nanoTime();
        long totalNanos = (localEndtTime - localStartTime);
        Log.d(TAG, "[Bench-Java-to-Rust] " + name + ", call count is: " + count + ", average call duration: " + (totalNanos / count) / 1000.0 + "??s, total duration: " + totalNanos / 1_000_000 + "ms, TPS: " + (count / (totalNanos / 1_000_000_000.0)));
    }
}
