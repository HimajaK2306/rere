package com.example.rere;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class MedicationStorage {

    private static final String PREF_NAME = "medication_store";
    private static final String KEY_LIST = "medications";

    private static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static List<String> getMedications(Context context) {
        SharedPreferences prefs = getPrefs(context);
        String json = prefs.getString(KEY_LIST, null);
        List<String> result = new ArrayList<>();
        if (json == null || json.isEmpty()) {
            return result;
        }
        try {
            JSONArray arr = new JSONArray(json);
            for (int i = 0; i < arr.length(); i++) {
                result.add(arr.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void saveMedications(Context context, List<String> medications) {
        JSONArray arr = new JSONArray();
        for (String m : medications) {
            arr.put(m);
        }
        getPrefs(context).edit().putString(KEY_LIST, arr.toString()).apply();
    }

    public static void addMedication(Context context, String medication) {
        List<String> list = getMedications(context);
        list.add(medication);
        saveMedications(context, list);
    }

    public static void removeMedication(Context context, String medication) {
        List<String> list = getMedications(context);
        if (list.remove(medication)) {
            saveMedications(context, list);
        }
    }
}
