package com.example.rere;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Central storage for medications.
 * All activities must use only this class to read/write medications.
 */
public class MedicationStorage {

    private static final String PREFS_NAME = "medication_prefs";
    private static final String KEY_MED_LIST = "med_list";

    // Read all medications as a List<String>
    public static List<String> getMedications(Context context) {
        SharedPreferences prefs =
                context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        String json = prefs.getString(KEY_MED_LIST, "[]");

        List<String> result = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                result.add(array.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Overwrite stored list
    public static void saveMedications(Context context, List<String> medications) {
        JSONArray array = new JSONArray();
        for (String med : medications) {
            array.put(med);
        }

        SharedPreferences prefs =
                context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        prefs.edit()
                .putString(KEY_MED_LIST, array.toString())
                .apply();
    }

    // Append single entry
    public static void addMedication(Context context, String entry) {
        List<String> meds = getMedications(context);
        meds.add(entry);
        saveMedications(context, meds);
    }

    // Optional helper to clear everything
    public static void clearAll(Context context) {
        SharedPreferences prefs =
                context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        prefs.edit().remove(KEY_MED_LIST).apply();
    }
}
