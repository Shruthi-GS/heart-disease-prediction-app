package com.example.heartdiseasepredictor;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText age,sex,exang,ca,thal,cp, thalach, slope, restecg, chol, trestbps, fbs, oldpeak;
    private Button predict;
    private ImageButton info1, info2, info3, info4, info5, info6, info7, info8, info9, info10, info11, info12, info13;
    private TextView result;
    private Button tips;
    String url = "https://heart-disease-prediction-app-1-iufn.onrender.com/predict";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        age = findViewById(R.id.age);
        sex = findViewById(R.id.sex);
        cp = findViewById(R.id.cp);
        trestbps = findViewById(R.id.trestbps);
        chol = findViewById(R.id.chol);
        fbs = findViewById(R.id.fbs);
        restecg = findViewById(R.id.restecg);
        thalach = findViewById(R.id.thalach);
        exang = findViewById(R.id.exang);
        oldpeak = findViewById(R.id.oldpeak);
        slope = findViewById(R.id.slope);
        ca = findViewById(R.id.ca);
        thal = findViewById(R.id.thal);
        predict = findViewById(R.id.predict);
        info1 = findViewById(R.id.info1);
        info2 = findViewById(R.id.info2);
        info3 = findViewById(R.id.info3);
        info4 = findViewById(R.id.info4);
        info5 = findViewById(R.id.info5);
        info6 = findViewById(R.id.info6);
        info7 = findViewById(R.id.info7);
        info8 = findViewById(R.id.info8);
        info9 = findViewById(R.id.info9);
        info10 = findViewById(R.id.info10);
        info11 = findViewById(R.id.info11);
        info12 = findViewById(R.id.info12);
        info13 = findViewById(R.id.info13);
        result = findViewById(R.id.result);
        tips = findViewById(R.id.tips);

        predict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (age.getText().toString().isEmpty() || Integer.parseInt(age.getText().toString()) < 0) {
                    age.setError("Cannot be Empty");
                }else if (sex.getText().toString().isEmpty() || (!sex.getText().toString().equals("0") && !sex.getText().toString().equals("1"))) {
                    sex.setError("Should be 0 or 1");
                }else if (cp.getText().toString().isEmpty() || (!cp.getText().toString().equals("0")
                        && !cp.getText().toString().equals("1") && !cp.getText().toString().equals("2") && !cp.getText().toString().equals("3"))){
                    cp.setError("Should be in 0-3 range");
                }else if (trestbps.getText().toString().isEmpty() || Integer.parseInt(trestbps.getText().toString()) < 0){
                    trestbps.setError("Cannot be Empty");
                }else if (chol.getText().toString().isEmpty() || Integer.parseInt(chol.getText().toString()) < 0){
                    chol.setError("Cannot be Empty");
                }else if (fbs.getText().toString().isEmpty() || (!fbs.getText().toString().equals("0") && !fbs.getText().toString().equals("1"))){
                    fbs.setError("Should be in 0-1 range");
                }else if (restecg.getText().toString().isEmpty() || (!restecg.getText().toString().equals("0")
                        && !restecg.getText().toString().equals("1") && !restecg.getText().toString().equals("2"))){
                    restecg.setError("Should be in 0-2 range");
                }else if (thalach.getText().toString().isEmpty() || Integer.parseInt(thalach.getText().toString()) < 0){
                    thalach.setError("Cannot be Empty");
                } else if (exang.getText().toString().isEmpty() || (!exang.getText().toString().equals("0") && !exang.getText().toString().equals("1"))) {
                    exang.setError("Should be 0 or 1");
                }else if (oldpeak.getText().toString().isEmpty() || Float.parseFloat(oldpeak.getText().toString()) < 0){
                    oldpeak.setError("Cannot be Empty");
                }else if (slope.getText().toString().isEmpty() || (!slope.getText().toString().equals("0")
                        && !slope.getText().toString().equals("1") && !slope.getText().toString().equals("2"))) {
                    slope.setError("Should be in 0-2 range");
                } else if (ca.getText().toString().isEmpty() || Integer.parseInt(ca.getText().toString()) < 0) {
                    ca.setError("Should be in 0-4 range");
                } else if (thal.getText().toString().isEmpty() || (!thal.getText().toString().equals("0") && !thal.getText().toString().equals("1") && !thal.getText().toString().equals("2") && !thal.getText().toString().equals("3"))) {
                    thal.setError("Should be in 0-3 range");
                }else {
                    //API -> Volley
                    sendApiRequest();
//                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
//                            new Response.Listener<String>() {
//                                @SuppressLint("WrongConstant")
//                                @Override

                }

            }
        });
        info9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String info = "Age of the patient.";
                    infoDialog("Age:", info);
                }
            });
        info10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String info = "Sex of the patient (0 = female, 1 = male).";
                    infoDialog("Sex:", info);
                }
            });
        info1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String info = "It should display the type of chest-pain experienced by the individual using the following format :\n" +
                        "0 = typical angina\n" +
                        "1 = atypical angina\n" +
                        "2 = non — anginal pain\n" +
                        "3 = asymptotic";
                infoDialog("Chest-pain type:",info);
            }
        });
        info6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String info = "It should display the resting blood pressure value of an individual in mmHg (unit)";
                    infoDialog("Resting Blood Pressure:",info);
                }
            });
        info5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String info = "It should display the serum cholesterol in mg/dl (unit)";
                    infoDialog("Cholestrol:",info);
                }
            });
        info7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String info = "It compares the fasting blood sugar value of an individual with 120mg/dl.\n" +
                            "If fasting blood sugar > 120mg/dl then : 1 (true)\n" +
                            "else : 0 (false)";
                    infoDialog("Fasting Blood Sugar:",info);
                }
            });
        info4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String info = "It should display resting electrocardiographic results\n" +
                        "0 = normal\n" +
                        "1 = having ST-T wave abnormality\n" +
                        "2 = left ventricular hyperthrophy";
                infoDialog("Resting ECG:",info);
            }
        });
        info2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String info = "It should display the max heart rate achieved by an individual.";
                    infoDialog("Max heart rate:",info);
                }
            });
        info11.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String info = "Exercise induced angina (0 = no, 1 = yes).";
                    infoDialog("Exercise Induced Angina:", info);
                }
            });
        info8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String info = "ST depression induced by exercise relative to rest, should display the value which is an integer or float. Write zero (0) if nothing.";
                infoDialog("ST depression:",info);
            }
        });
        info3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String info = "Peak exercise ST segment :\n" +
                            "0 = upsloping\n" +
                            "1 = flat\n" +
                            "2 = downsloping";
                    infoDialog("Exercise ST:",info);
                }
            });
        info12.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String info = "Number of major vessels (0-4) colored by fluoroscopy.";
                    infoDialog("Number of Major Vessels:", info);
                }
            });
        info13.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String info = "Thalassemia (0 = normal; 1 = fixed defect; 2 = reversible defect; 3 = unknown).";
                    infoDialog("Thalassemia:", info);
                }
            });
        tips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,InfoActivity.class));
                finish();
            }
        });
    }
    private void sendApiRequest() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void onResponse(String response) {
                        Log.d("API Response", response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String data = jsonObject.getString("heart_disease");
                            tips.setVisibility(1);

                            if (data.equals("0")) {
                                result.setTextColor(Color.parseColor("#5bdeac"));
                                result.setText("No Chances of Heart Disease");
                            } else {
                                result.setTextColor(Color.parseColor("#EC4C4C"));
                                result.setText("High Chances of Heart Disease");
                            }
                            age.setText("");
                            sex.setText("");
                            cp.setText("");
                            trestbps.setText("");
                            chol.setText("");
                            fbs.setText("");
                            restecg.setText("");
                            thalach.setText("");
                            exang.setText("");
                            oldpeak.setText("");
                            slope.setText("");
                            ca.setText("");
                            thal.setText("");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String err = (error.getMessage() == null) ? "Failed! Please Try Again" : error.getMessage();
                Toast.makeText(MainActivity.this, err, Toast.LENGTH_SHORT).show();
                Log.d("API ERROR : ", err);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();
                params.put("age", age.getText().toString());
                params.put("sex", sex.getText().toString());
                params.put("cp", cp.getText().toString());
                params.put("trestbps", trestbps.getText().toString());
                params.put("chol", chol.getText().toString());
                params.put("fbs", fbs.getText().toString());
                params.put("restecg", restecg.getText().toString());
                params.put("thalach", thalach.getText().toString());
                params.put("exang", exang.getText().toString());
                params.put("oldpeak", oldpeak.getText().toString());
                params.put("slope", slope.getText().toString());
                params.put("ca", ca.getText().toString());
                params.put("thal", thal.getText().toString());

                return params;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(stringRequest);
    }
        private void infoDialog(String title, String message) {
            Dialog dialog = new Dialog(MainActivity.this);
            dialog.setContentView(R.layout.info_dialog);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            ImageView close = dialog.findViewById(R.id.closeDialog);
            TextView nameDialog = dialog.findViewById(R.id.nameDialog);
            TextView infoDialog = dialog.findViewById(R.id.infoDialog);

            nameDialog.setText(title);
            infoDialog.setText(message);

            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.cancel();
                }
            });
            dialog.show();
        }
    }