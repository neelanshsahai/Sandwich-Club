package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

public class JsonUtils {

    private final static String NAME = "name";
    private final static String MAIN_NAME = "mainName";
    private final static String OTHER_NAMES = "alsoKnownAs";
    private final static String ORIGIN = "placeOfOrigin";
    private final static String DESC = "description";
    private final static String IMAGE = "image";
    private final static String INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = new Sandwich();
        try {
            JSONObject jsonObject = new JSONObject(json);
/*
            String mainName = jsonObject.getJSONObject(NAME).getString(MAIN_NAME);
            String imageUrl = jsonObject.getString(IMAGE);
            String desc = jsonObject.getString(DESC);

            sandwich.setMainName(mainName);
            sandwich.setImage(imageUrl);
            sandwich.setDescription(desc);
*/

            JSONObject nameObject = jsonObject.getJSONObject(NAME);

            List<String> list1 = new LinkedList<>();
            JSONArray otherNameJsonArray = nameObject.getJSONArray(OTHER_NAMES);
            for(int i=0; i<otherNameJsonArray.length(); i++)
                list1.add(otherNameJsonArray.getString(i));

            List<String> list2 = new LinkedList<>();
            JSONArray ingredientsJsonArray = jsonObject.getJSONArray(INGREDIENTS);
            for(int i=0; i<ingredientsJsonArray.length(); i++)
                list2.add(ingredientsJsonArray.getString(i));

            sandwich.setMainName(nameObject.getString(MAIN_NAME));
            sandwich.setPlaceOfOrigin(jsonObject.getString(ORIGIN));
            sandwich.setAlsoKnownAs(list1);
            sandwich.setDescription(jsonObject.getString(DESC));
            sandwich.setImage(jsonObject.getString(IMAGE));
            sandwich.setIngredients(list2);

        } catch (JSONException e) {
            Log.e("json-utils-catch", String.valueOf(e));
        }
        return sandwich;
    }
}
