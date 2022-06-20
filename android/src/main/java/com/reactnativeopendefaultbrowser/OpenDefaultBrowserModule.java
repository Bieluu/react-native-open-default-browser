package com.reactnativeopendefaultbrowser;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.content.pm.PackageManager;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;

@ReactModule(name = OpenDefaultBrowserModule.NAME)
public class OpenDefaultBrowserModule extends ReactContextBaseJavaModule {
    public static final String NAME = "OpenDefaultBrowser";

    public OpenDefaultBrowserModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    @NonNull
    public String getName() {
        return NAME;
    }


    @ReactMethod
    public void openBrowser(String packageId, Promise promise) {
        Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://"));
        ResolveInfo resolveInfo = getPackageManager().resolveActivity(browserIntent,PackageManager.MATCH_DEFAULT_ONLY);
        String packageName = resolveInfo.activityInfo.packageName;
        PackageManager packageManager = getReactApplicationContext().getPackageManager();
        try {
            Intent launchIntent = packageManager.getLaunchIntentForPackage(packageName);
            getReactApplicationContext().startActivity(launchIntent);
            promise.resolve(true);
        } catch (Exception e) {
            promise.reject(e.getMessage(), "Package not found");
        }
    }
}
