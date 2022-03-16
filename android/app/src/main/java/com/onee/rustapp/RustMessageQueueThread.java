package com.onee.rustapp;

import com.facebook.react.bridge.queue.MessageQueueThread;
import com.facebook.react.bridge.queue.MessageQueueThreadPerfStats;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

class RustMessageQueueThread implements MessageQueueThread {
   @Override
   public void runOnQueue(Runnable runnable) {

   }

   @Override
   public <T> Future<T> callOnQueue(Callable<T> callable) {
      return null;
   }

   @Override
   public boolean isOnThread() {
      return false;
   }

   @Override
   public void assertIsOnThread() {

   }

   @Override
   public void assertIsOnThread(String message) {

   }

   @Override
   public void quitSynchronous() {

   }

   @Override
   public MessageQueueThreadPerfStats getPerfStats() {
      return null;
   }

   @Override
   public void resetPerfStats() {

   }
}
