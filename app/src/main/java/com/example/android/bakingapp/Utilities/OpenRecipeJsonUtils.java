package com.example.android.bakingapp.Utilities;

import android.content.Context;

import com.example.android.bakingapp.DataModels.Ingredient;
import com.example.android.bakingapp.DataModels.Recipe;
import com.example.android.bakingapp.DataModels.Step;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.os.Build.ID;

/**
 * Created by mostafayehya on 02/10/17.
 */


/*[
  {
    "id": 1,
    "name": "Nutella Pie",
    "ingredients": [
      {
        "quantity": 2,
        "measure": "CUP",
        "ingredient": "Graham Cracker crumbs"
      },
      {
        "quantity": 6,
        "measure": "TBLSP",
        "ingredient": "unsalted butter, melted"
      },
      {
        "quantity": 0.5,
        "measure": "CUP",
        "ingredient": "granulated sugar"
      },
      {
        "quantity": 1.5,
        "measure": "TSP",
        "ingredient": "salt"
      },
      {
        "quantity": 5,
        "measure": "TBLSP",
        "ingredient": "vanilla"
      },
      {
        "quantity": 1,
        "measure": "K",
        "ingredient": "Nutella or other chocolate-hazelnut spread"
      },
      {
        "quantity": 500,
        "measure": "G",
        "ingredient": "Mascapone Cheese(room temperature)"
      },
      {
        "quantity": 1,
        "measure": "CUP",
        "ingredient": "heavy cream(cold)"
      },
      {
        "quantity": 4,
        "measure": "OZ",
        "ingredient": "cream cheese(softened)"
      }
    ],
    "steps": [
      {
        "id": 0,
        "shortDescription": "Recipe Introduction",
        "description": "Recipe Introduction",
        "videoURL": "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4",
        "thumbnailURL": ""
      },
      {
        "id": 1,
        "shortDescription": "Starting prep",
        "description": "1. Preheat the oven to 350\u00b0F. Butter a 9\" deep dish pie pan.",
        "videoURL": "",
        "thumbnailURL": ""
      },
      {
        "id": 2,
        "shortDescription": "Prep the cookie crust.",
        "description": "2. Whisk the graham cracker crumbs, 50 grams (1/4 cup) of sugar, and 1/2 teaspoon of salt together in a medium bowl. Pour the melted butter and 1 teaspoon of vanilla into the dry ingredients and stir together until evenly mixed.",
        "videoURL": "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd9a6_2-mix-sugar-crackers-creampie/2-mix-sugar-crackers-creampie.mp4",
        "thumbnailURL": ""
      },
      {
        "id": 3,
        "shortDescription": "Press the crust into baking form.",
        "description": "3. Press the cookie crumb mixture into the prepared pie pan and bake for 12 minutes. Let crust cool to room temperature.",
        "videoURL": "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd9cb_4-press-crumbs-in-pie-plate-creampie/4-press-crumbs-in-pie-plate-creampie.mp4",
        "thumbnailURL": ""
      },
      {
        "id": 4,
        "shortDescription": "Start filling prep",
        "description": "4. Beat together the nutella, mascarpone, 1 teaspoon of salt, and 1 tablespoon of vanilla on medium speed in a stand mixer or high speed with a hand mixer until fluffy.",
        "videoURL": "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd97a_1-mix-marscapone-nutella-creampie/1-mix-marscapone-nutella-creampie.mp4",
        "thumbnailURL": ""
      },
      {
        "id": 5,
        "shortDescription": "Finish filling prep",
        "description": "5. Beat the cream cheese and 50 grams (1/4 cup) of sugar on medium speed in a stand mixer or high speed with a hand mixer for 3 minutes. Decrease the speed to medium-low and gradually add in the cold cream. Add in 2 teaspoons of vanilla and beat until stiff peaks form.",
        "videoURL": "",
        "thumbnailURL": "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffda20_7-add-cream-mix-creampie/7-add-cream-mix-creampie.mp4"
      },
      {
        "id": 6,
        "shortDescription": "Finishing Steps",
        "description": "6. Pour the filling into the prepared crust and smooth the top. Spread the whipped cream over the filling. Refrigerate the pie for at least 2 hours. Then it's ready to serve!",
        "videoURL": "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffda45_9-add-mixed-nutella-to-crust-creampie/9-add-mixed-nutella-to-crust-creampie.mp4",
        "thumbnailURL": ""
      }
    ],
    "servings": 8,
    "image": ""
  },
  {
    "id": 2,
    "name": "Brownies",
    "ingredients": [
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

//the respose
        JSONArray jsonReponse = new JSONArray(jsonRecipeResponse);

        ArrayList<Recipe> parsedRecipes = new ArrayList<>();

        for (int i = 0; i < jsonReponse.length(); i++) {

            JSONObject currentRecipeJsonObject = jsonReponse.getJSONObject(i);
            Recipe currentRecipeObject = new Recipe();
            currentRecipeObject.id = currentRecipeJsonObject.getInt(ID);
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
                temporaryStep.description = stepJsonObject.getString(VIDEO_URL);
                temporaryStep.thumbnailUrl = stepJsonObject.getString(THUMBNAIL_URL);

                currentRecipeObject.stepsList.add(k, temporaryStep);
            }

            currentRecipeObject.servings = currentRecipeJsonObject.getInt(SERVINGS);

            parsedRecipes.add(currentRecipeObject);

        }

        return parsedRecipes;
    }


}
