package bt.gov.dit.discoverbhutan;


import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by darshan on 6/23/17.
 */

public class QuestionLibrary {

    JSONParser jsonParser;
    private JSONObject jsonObject;
    private String questionString;
    private String optionOne,optionTwo,optionThree,optionFour,answer;


    QuestionLibrary(Context c){


        SharedPreferences sharedPref = c.getSharedPreferences("QuestionaireStage2",MODE_PRIVATE);
        String stringQuestion = sharedPref.getString("jsondata","0");

        if(stringQuestion!="0")
        {

            try{
                JSONObject jsonData = new JSONObject(stringQuestion);
                setJsonObject(jsonData);
            } catch (Exception e){}

        }
    }





    public JSONObject setJsonObject(JSONObject questions){

        this.jsonObject = questions;
        return questions;

    }





    public String getQuestion(int a) {

        try{
            JSONArray questions = jsonObject.getJSONArray("questions");

            JSONObject question = questions.getJSONObject(a);
            questionString = question.getString("question");

        }catch(JSONException e){

        }


        return questionString;
    }


    public String getOptionOne(int a) {
        try{
            JSONArray questions = jsonObject.getJSONArray("questions");

            JSONObject question = questions.getJSONObject(a);
            optionOne = question.getString("option_one");


        }catch(JSONException e){

        }
    return optionOne;

    }


    public String getOptionTwo(int a) {
        try{
            JSONArray questions = jsonObject.getJSONArray("questions");

            JSONObject question = questions.getJSONObject(a);
            optionTwo = question.getString("option_two");

        }catch(JSONException e){

        }

        return optionTwo;
    }

    public String getOptionThree(int a) {
        try{
            JSONArray questions = jsonObject.getJSONArray("questions");

            JSONObject question = questions.getJSONObject(a);
            optionThree = question.getString("option_three");

        }catch(JSONException e){

        }
        return optionThree;
    }

    public String getOptionFour(int a) {
        try{
            JSONArray questions = jsonObject.getJSONArray("questions");

            JSONObject question = questions.getJSONObject(a);
            optionFour = question.getString("option_four");

        }catch(JSONException e){

        }
        return optionFour;
    }

    public String getCorrectAnswer(int a) {
        try{
            JSONArray questions = jsonObject.getJSONArray("questions");

            JSONObject question = questions.getJSONObject(a);
            answer = question.getString("answer");

        }catch(JSONException e){

        }
        return answer;
    }




}
