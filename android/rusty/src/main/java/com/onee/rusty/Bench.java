package com.onee.rusty;

import androidx.annotation.NonNull;

import com.onee.rusty.glue.FromRustToJavaBench;
import com.onee.rusty.glue.ViewProperty;

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
}
