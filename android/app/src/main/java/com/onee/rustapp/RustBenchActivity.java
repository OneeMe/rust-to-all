package com.onee.rustapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.onee.rusty.Bench;
import com.onee.rusty.RustyEngine;

public class RustBenchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        bench();
    }

    private void bench() {
        RustyEngine engine = new RustyEngine(this);
        Bench bench = new Bench(this);
        bench.run();
    }
}