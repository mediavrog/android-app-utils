package net.mediavrog.common;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import net.lingio.yondle_browse.App;

/**
 * Created by maikvlcek on 1/13/16.
 */
public class SharedSettingUtil {
    public static final String TAG = SharedSettingUtil.class.getSimpleName();

    public static SharedPreferences getDefaultPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(App.getInstance());
    }

    public static void saveSetting(String settingName, String settingValue) {
        getDefaultPreferences().edit().putString(settingName, settingValue).apply();
    }

    public static String readSetting(String settingName, String defaultValue) {
        return getDefaultPreferences().getString(settingName, defaultValue);
    }

    public static void saveSetting(String settingName, boolean settingValue) {
        getDefaultPreferences().edit().putBoolean(settingName, settingValue).apply();
    }

    public static boolean readSetting(String settingName, boolean defaultValue) {
        return getDefaultPreferences().getBoolean(settingName, defaultValue);
    }
}
