## Onboarding examples on Android

This repository shows how to provide different experiences of onboarding on Android , avoiding cold starts.

This repository also serves as a reference for this [article](http://saulmm.github.io/avoding-android-cold-starts).

## How to use it?

To check the different examples, modifies the 'AndroidManifest.xml' initial activity by any of the three declared.

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
          package="com.example.saulmm.splashes">

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">

        <activity
            android:name=".OnboardingWithPlaceholderActivity"
            android:theme="@style/AppTheme.CenterAnimation">

            <intent-filter>
                <action android:name="android.intent.action.MAIN"/>
                <category android:name="android.intent.category.LAUNCHER"/>
            </intent-filter>
        </activity>

        <activity
            android:name=".OnboardingWithCenterAnimationActivity"
            android:theme="@style/AppTheme.Placeholder">
        </activity>

        <activity
            android:name=".OnBoardingWithSimpleBackground"
            android:theme="@style/AppTheme.Simple"/>
    </application>

</manifest>
```

### Onboarding with an animation from the center

![](https://github.com/saulmm/onboarding-examples-android/blob/master/art/center.gif?raw=true)

## Onboarding with a placeholder

![](https://github.com/saulmm/onboarding-examples-android/blob/master/art/placeholder.gif?raw=true)

## Onboarding with a simple image

![](https://github.com/saulmm/onboarding-examples-android/blob/master/art/simple.gif?raw=true)