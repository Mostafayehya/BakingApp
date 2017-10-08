package com.example.android.bakingapp.Utilities;

import android.content.Context;
import android.util.Log;

import com.example.android.bakingapp.DataModels.Ingredient;
import com.example.android.bakingapp.DataModels.Recipe;
import com.example.android.bakingapp.DataModels.Step;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by mostafayehya on 02/10/17.
 */

public class OpenRecipeJsonUtils {

    static final String RECIPE_ID = "id";

    static final String NAME = "name";
    static final String INGREDIENTS_ARRAY = "ingredients";
    static final String QUANTITY = "quantity";
    static final String MEASURE = "measure";
    static final String INGREDIENT = "ingredient";
    static final String STEPS_JSON_ARRAY = "steps";
    static final String STEP_ID = "id";
    static final String SHORT_DESCRIPTION = "shortDescription";
    static final String DESCRIPTION = "description";
    static final String VIDEO_URL = "videoURL";
    static final String THUMBNAIL_URL = "thumbnailURL";

    static final String SERVINGS = "servings";


    public static ArrayList<Recipe> getArrayListOfRecipesFromJson(Context context, String jsonRecipeResponse) throws JSONException {


        JSONArray jsonReponse = new JSONArray(jsonRecipeResponse);

        ArrayList<Recipe> parsedRecipes = new ArrayList<>();

        for (int i = 0; i < jsonReponse.length(); i++) {

            JSONObject currentRecipeJsonObject = jsonReponse.getJSONObject(i);
            Recipe currentRecipeObject = new Recipe();
            currentRecipeObject.id = currentRecipeJsonObject.getInt(RECIPE_ID);
            currentRecipeObject.name = currentRecipeJsonObject.getString(NAME);

            JSONArray IngredientsJsonArray = currentRecipeJsonObject.getJSONArray(INGREDIENTS_ARRAY);

            for (int j = 0; j < IngredientsJsonArray.length(); j++) {
                JSONObject ingredientJsonObject = IngredientsJsonArray.getJSONObject(j);

                Ingredient temporaryIngredient = new Ingredient();
                temporaryIngredient.quantity = ingredientJsonObject.getInt(QUANTITY);
                temporaryIngredient.measurment = ingredientJsonObject.getString(MEASURE);
                temporaryIngredient.name = ingredientJsonObject.getString(INGREDIENT);
                currentRecipeObject.ingredientList.add(j, temporaryIngredient);

            }
            JSONArray stepsJsonArray = currentRecipeJsonObject.getJSONArray(STEPS_JSON_ARRAY);

            for (int k = 0; k < stepsJsonArray.length(); k++) {
                JSONObject stepJsonObject = stepsJsonArray.getJSONObject(k);
                Step temporaryStep = new Step();

                temporaryStep.id = stepJsonObject.getInt(STEP_ID);
                temporaryStep.shortDescription = stepJsonObject.getString(SHORT_DESCRIPTION);
                temporaryStep.description = stepJsonObject.getString(DESCRIPTION);
                temporaryStep.videoUrl = stepJsonObject.getString(VIDEO_URL);
                temporaryStep.thumbnailUrl = stepJsonObject.getString(THUMBNAIL_URL);

                currentRecipeObject.stepsList.add(k, temporaryStep);
            }

            currentRecipeObject.servings = currentRecipeJsonObject.getInt(SERVINGS);

            parsedRecipes.add(currentRecipeObject);

        }

        Log.d("rp:","getArrayListFromJson was invoked " );
        return parsedRecipes;
    }


}
