package com.fourtsoft.bvc.utils;

import android.util.Log;

public class LogUtils {

    public static final String LOG_SEQUENCE_METHOD = "LOG_SEQUENCE_METHOD";
    public static final String LOG_SEQUENCE_LIFE_CYCLE = "LOG_SEQUENCE_LIFE_CYCLE";

    public static void setLogSequenceMethod() {
        StackTraceElement elementCalled = Thread.currentThread().getStackTrace()[3];
        String mMethodCaller = "";
        String mMethodCalled = "";
        String mClassCaller  = "";
        if (elementCalled.getClassName().lastIndexOf(".") > 0) {
            mMethodCalled = elementCalled.getMethodName();
        }

        StackTraceElement elementCaller = Thread.currentThread().getStackTrace()[4];
        if (elementCalled.getClassName().lastIndexOf(".") > 0) {
            mMethodCaller = elementCaller.getMethodName();
            mClassCaller = elementCalled.getClassName().substring(elementCalled.getClassName().lastIndexOf(".") + 1);
        }

        Log.d(LOG_SEQUENCE_METHOD, mClassCaller + " :: " + mMethodCaller + " => " + mMethodCalled);
    }

}
