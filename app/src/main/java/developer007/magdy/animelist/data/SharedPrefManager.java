package developer007.magdy.animelist.data;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {

    public static SharedPreferences getAuthPref(Context mContext) {

        return mContext.getSharedPreferences("settings", Context.MODE_PRIVATE);
    }

    public static void setPrefVal(Context mContext, String key, String value) {
        if (key != null) {
            SharedPreferences.Editor authEdit = getAuthPref(mContext).edit();
            authEdit.putString(key, value);
            authEdit.apply();
        }
    }
}

/*
hints

1) name: name
 */
