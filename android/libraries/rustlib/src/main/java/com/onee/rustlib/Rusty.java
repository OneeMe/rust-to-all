package com.onee.rustlib;

public class Rusty {
   static public void loadRustLib() {
      System.loadLibrary("rustlib");
   }

   public static native String hello(String input);
   public static native String helloDirect(String input);
}
