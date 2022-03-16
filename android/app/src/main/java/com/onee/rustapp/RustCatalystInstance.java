package com.onee.rustapp;

import android.content.res.AssetManager;

import androidx.annotation.Nullable;

import com.facebook.react.bridge.CatalystInstance;
import com.facebook.react.bridge.JSIModule;
import com.facebook.react.bridge.JSIModuleSpec;
import com.facebook.react.bridge.JSIModuleType;
import com.facebook.react.bridge.JavaScriptContextHolder;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeArray;
import com.facebook.react.bridge.NativeArrayInterface;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.NativeModuleRegistry;
import com.facebook.react.bridge.NotThreadSafeBridgeIdleDebugListener;
import com.facebook.react.bridge.RuntimeExecutor;
import com.facebook.react.bridge.RuntimeScheduler;
import com.facebook.react.bridge.queue.ReactQueueConfiguration;
import com.facebook.react.turbomodule.core.interfaces.CallInvokerHolder;

import java.util.Collection;
import java.util.List;

class RustCatalystInstance implements CatalystInstance {
   private ReactQueueConfiguration reactQueueConfiguration;

   public RustCatalystInstance(ReactQueueConfiguration reactQueueConfiguration) {
      this.reactQueueConfiguration = reactQueueConfiguration;
   }

   @Override
   public void runJSBundle() {

   }

   @Override
   public boolean hasRunJSBundle() {
      return false;
   }

   @Nullable
   @Override
   public String getSourceURL() {
      return null;
   }

   @Override
   public void invokeCallback(int callbackID, NativeArrayInterface arguments) {

   }

   @Override
   public void callFunction(String module, String method, NativeArray arguments) {

   }

   @Override
   public void destroy() {

   }

   @Override
   public boolean isDestroyed() {
      return false;
   }

   @Override
   public void initialize() {

   }

   @Override
   public ReactQueueConfiguration getReactQueueConfiguration() {
      return reactQueueConfiguration;
   }

   @Override
   public <T extends JavaScriptModule> T getJSModule(Class<T> jsInterface) {
      return null;
   }

   @Override
   public <T extends NativeModule> boolean hasNativeModule(Class<T> nativeModuleInterface) {
      return false;
   }

   @Nullable
   @Override
   public <T extends NativeModule> T getNativeModule(Class<T> nativeModuleInterface) {
      return null;
   }

   @Nullable
   @Override
   public NativeModule getNativeModule(String moduleName) {
      return null;
   }

   @Override
   public JSIModule getJSIModule(JSIModuleType moduleType) {
      return null;
   }

   @Override
   public Collection<NativeModule> getNativeModules() {
      return null;
   }

   @Override
   public void extendNativeModules(NativeModuleRegistry modules) {

   }

   @Override
   public void addBridgeIdleDebugListener(NotThreadSafeBridgeIdleDebugListener listener) {

   }

   @Override
   public void removeBridgeIdleDebugListener(NotThreadSafeBridgeIdleDebugListener listener) {

   }

   @Override
   public void registerSegment(int segmentId, String path) {

   }

   @Override
   public void setGlobalVariable(String propName, String jsonValue) {

   }

   @Override
   public JavaScriptContextHolder getJavaScriptContextHolder() {
      return null;
   }

   @Override
   public RuntimeExecutor getRuntimeExecutor() {
      return null;
   }

   @Override
   public RuntimeScheduler getRuntimeScheduler() {
      return null;
   }

   @Override
   public void addJSIModules(List<JSIModuleSpec> jsiModules) {

   }

   @Override
   public CallInvokerHolder getJSCallInvokerHolder() {
      return null;
   }

   @Override
   public CallInvokerHolder getNativeCallInvokerHolder() {
      return null;
   }

   @Override
   public void setTurboModuleManager(JSIModule getter) {

   }

   @Override
   public void loadScriptFromAssets(AssetManager assetManager, String assetURL, boolean loadSynchronously) {

   }

   @Override
   public void loadScriptFromFile(String fileName, String sourceURL, boolean loadSynchronously) {

   }

   @Override
   public void loadSplitBundleFromFile(String fileName, String sourceURL) {

   }

   @Override
   public void setSourceURLs(String deviceURL, String remoteURL) {

   }

   @Override
   public void handleMemoryPressure(int level) {

   }
}
