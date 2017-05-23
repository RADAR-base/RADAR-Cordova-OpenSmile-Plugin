package com.audeering.opensmile.androidtemplate;

public class SmileJNI {

    /**
     * load the JNI interface
     */
    static {
        System.loadLibrary("smile_jni");
    }

    /**
     * method to execute openSMILE binary from the Android app activity, see smile_jni.cpp.
     * @param configfile
     * @param externalStoragePath
     * @param updateProfile
     * @return
     */
    public static native String SMILExtractJNI(String configfile, int updateProfile, String outputfile);
    public static native String SMILEndJNI();

    /**
     * process the messages from openSMILE (redirect to app activity etc.)
     */
    public interface Listener {
        void onSmileMessageReceived(String text);
    }
    private static Listener listener_;
    public static void registerListener (Listener listener) {
        listener_ = listener;
    }

    /**
     * this is the first method called by openSMILE binary. it redirects the call to the Android
     * app activity.
     * @param text JSON encoded string
     */
    static void receiveText(String text) {
        if (listener_ != null)
            listener_.onSmileMessageReceived(text);
    }
}
