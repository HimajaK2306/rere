package com.example.rere;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class MedicationStorage {

    private static final String KEY_MED_LIST = "med_list";

    private static SharedPreferences prefsForUser(Context context) {
        SharedPreferences session =
                context.getSharedPreferences("session", Context.MODE_PRIVATE);

        String active = session.getString("active_user", "default");

        String prefsName = "medication_prefs_" + active;

        return context.getSharedPreferences(prefsName, Context.MODE_PRIVATE);
    }

    public static List<String> getMedications(Context context) {
        SharedPreferences prefs = prefsForUser(context);
        String json = prefs.getString(KEY_MED_LIST, "[]");

        List<String> result = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                result.add(array.getString(i));
            }
        } catch (JSONException ignored) {}

        return result;
    }

    public static void saveMedications(Context context, List<String> medications) {
        JSONArray array = new JSONArray();
        for (String med : medications) array.put(med);

        SharedPreferences prefs = prefsForUser(context);
        prefs.edit().putString(KEY_MED_LIST, array.toString()).apply();
    }

    public static void addMedication(Context context, String entry) {
        List<String> meds = getMedications(context);
        meds.add(entry);
        saveMedications(context, meds);
    }
}
