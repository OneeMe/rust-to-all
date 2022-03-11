package com.onee.rusty;

public class NativeLib {

    // Used to load the 'rusty' library on application startup.
    static {
        System.loadLibrary("rustlib");
    }

    /**
     * A native method that is implemented by the 'rusty' native library,
     * which is packaged with this application.
     */
    public static native String hello(String input);
    public static native String helloDirect(String input);
}