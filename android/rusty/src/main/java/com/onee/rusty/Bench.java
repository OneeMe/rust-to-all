package com.onee.rusty;

import androidx.annotation.NonNull;

import com.onee.rusty.glue.FromRustToJavaBench;
import com.onee.rusty.glue.ViewProperty;

import java.nio.ByteBuffer;

public class Bench implements FromRustToJavaBench {
    @Override
    public void callUseFlapigen(@NonNull ViewProperty args) {

    }

    @Override
    public void callUseJson(@NonNull String args) {

    }

    @Override
    public void callUseBson(@NonNull byte[] args) {

    }

    @Override
    public void callUseFlexbuffer(@NonNull byte[] args) {
        ByteBuffer buffer = ByteBuffer.wrap(args);
        com.onee.rusty.model.ViewProperty viewProperty = com.onee.rusty.model.ViewProperty.getRootAsViewProperty(buffer);
        
    }
}
