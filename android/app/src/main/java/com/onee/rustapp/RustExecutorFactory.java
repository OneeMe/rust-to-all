package com.onee.rustapp;

import com.facebook.react.bridge.JavaScriptExecutor;
import com.facebook.react.bridge.JavaScriptExecutorFactory;
import com.facebook.react.bridge.WritableNativeMap;

class RustExecutorFactory implements JavaScriptExecutorFactory {
   @Override
   public JavaScriptExecutor create() {
      WritableNativeMap data = new WritableNativeMap();
      data.putString("foo", "bar");
      return new RustExecutor(data);
   }

   @Override
   public void startSamplingProfiler() {

   }

   @Override
   public void stopSamplingProfiler(String filename) {

   }
}
