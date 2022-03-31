package com.onee.rusty;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.onee.rusty.glue.FromRustToJavaBench;
import com.onee.rusty.glue.ViewProperty;

import java.nio.ByteBuffer;

public class Bench implements FromRustToJavaBench {
    private static String TAG = "FromRustToJavaBench";

    private void log(String content) {
//        Log.d(TAG, content);
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
    public void callUseFlexbuffer(@NonNull byte[] args, boolean read) {
        if (read) {
            ByteBuffer buffer = ByteBuffer.wrap(args);
            com.onee.rusty.model.ViewProperty viewProperty = com.onee.rusty.model.ViewProperty.getRootAsViewProperty(buffer);
            log("callUseFlexbuffer-width: " + viewProperty.width());
            log("callUseFlexbuffer-height: " + viewProperty.height());
            log("callUseFlexbuffer-marginLeft: " + viewProperty.marginLeft());
            log("callUseFlexbuffer-marginRight: " + viewProperty.marginRight());
            log("callUseFlexbuffer-marginTop: " + viewProperty.marginTop());
            log("callUseFlexbuffer-marginBottom: " + viewProperty.marginBottom());
            log("callUseFlexbuffer-flex: " + viewProperty.flex());
            log("callUseFlexbuffer-display: " + viewProperty.display());
            log("callUseFlexbuffer-flexDirection: " + viewProperty.flexDirection());
            log("callUseFlexbuffer-backgroundColor: " + viewProperty.backgroundColor());
            log("callUseFlexbuffer-flexWrap: " + viewProperty.flexWrap());
            log("callUseFlexbuffer-content: " + viewProperty.content());
            log("callUseFlexbuffer-a:" + viewProperty.a());
log("callUseFlexbuffer-b:" + viewProperty.b());
log("callUseFlexbuffer-c:" + viewProperty.c());
log("callUseFlexbuffer-d:" + viewProperty.d());
log("callUseFlexbuffer-e:" + viewProperty.e());
log("callUseFlexbuffer-f:" + viewProperty.f());
log("callUseFlexbuffer-g:" + viewProperty.g());
log("callUseFlexbuffer-h:" + viewProperty.h());
log("callUseFlexbuffer-i:" + viewProperty.i());
log("callUseFlexbuffer-j:" + viewProperty.j());
log("callUseFlexbuffer-k:" + viewProperty.k());
log("callUseFlexbuffer-l:" + viewProperty.l());
log("callUseFlexbuffer-m:" + viewProperty.m());
log("callUseFlexbuffer-n:" + viewProperty.n());
log("callUseFlexbuffer-o:" + viewProperty.o());
        }
    }
}
