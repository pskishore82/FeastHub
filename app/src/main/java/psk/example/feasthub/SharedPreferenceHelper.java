package psk.example.feasthub;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceHelper {
    private static final String PREF_NAME = "MyPrefs";
    private static final String KEY_STAY_SIGNED_IN = "staySignedIn";

    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;

    public SharedPreferenceHelper(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setStaySignedIn(boolean staySignedIn) {
        editor.putBoolean(KEY_STAY_SIGNED_IN, staySignedIn);
        editor.apply();
    }

    public boolean getStaySignedIn() {
        return sharedPreferences.getBoolean(KEY_STAY_SIGNED_IN, false);
    }
}
