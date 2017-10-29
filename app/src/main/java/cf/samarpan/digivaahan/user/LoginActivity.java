package cf.samarpan.digivaahan.user;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cf.samarpan.digivaahan.MainActivity;
import cf.samarpan.digivaahan.R;
import cf.samarpan.digivaahan.app.AppConfig;
import cf.samarpan.digivaahan.app.AppController;

import static android.content.ContentValues.TAG;
import static cf.samarpan.digivaahan.R.id.UserLoginField;

public class LoginActivity extends AppCompatActivity {

    private TextView UserLoginUIDAI;
    private TextView UserLoginPassword;
    private Spinner UserLoginState;

    private String UIDAI;
    private String Password;
    private String SelectedState;
    private ProgressDialog pDialog;

    private Button UserLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_login);


        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        UserLoginUIDAI = (TextView) findViewById(R.id.UserLoginUIDAI);
        UserLoginPassword = (TextView) findViewById(R.id.UserLoginPassword);
        UserLoginState = (Spinner) findViewById(R.id.UserLoginStates);
        UserLoginButton = (Button) findViewById(R.id.UserLoginButton);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.states, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        UserLoginState.setAdapter(adapter);







        UserLoginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                UIDAI = UserLoginUIDAI.getText().toString().trim();
                Password = UserLoginPassword.getText().toString().trim();
                SelectedState = UserLoginState.getSelectedItem().toString().trim();



                // Tag used to cancel the request
                String tag_string_req = "req_login";

                pDialog.setMessage("Logging In ...");
                showDialog();

                switch (SelectedState) {
                    case "Andhra Pradesh" : SelectedState = "AP";
                        break;

                    case "Arunachal Pradesh" : SelectedState = "AR";
                        break;

                    case "Assam" : SelectedState = "AS";
                        break;

                    case "Bihar" : SelectedState = "BR";
                        break;

                    case "Chhattisgarh" : SelectedState = "CG";
                        break;

                    case "Goa" : SelectedState = "GA";
                        break;

                    case "Gujarat" : SelectedState = "GJ";
                        break;

                    case "Haryana" : SelectedState = "HR";
                        break;

                    case "Himachal Pradesh" : SelectedState = "HP";
                        break;

                    case "Jammu and Kashmir" : SelectedState = "JK";
                        break;

                    case "Jharkhand" : SelectedState = "JH";
                        break;

                    case "Karnataka" : SelectedState = "KA";
                        break;

                    case "Kerala" : SelectedState = "KL";
                        break;

                    case "Madhya Pradesh" : SelectedState = "MP";
                        break;

                    case "Maharashtra" : SelectedState = "mh";
                        break;

                    case "Manipur" : SelectedState = "MN";
                        break;

                    case "Meghalaya" : SelectedState = "ML";
                        break;

                    case "Mizoram" : SelectedState = "MZ";
                        break;

                    case "Nagaland" : SelectedState = "NL";
                        break;

                    case "Orissa" : SelectedState = "OR";
                        break;

                    case "Punjab" : SelectedState = "PB";
                        break;

                    case "Rajasthan" : SelectedState = "RJ";
                        break;

                    case "Sikkim" : SelectedState = "SK";
                        break;

                    case "Tamil Nadu" : SelectedState = "TN";
                        break;

                    case "Tripura" : SelectedState = "TR";
                        break;

                    case "Uttarakhand" : SelectedState = "UK";
                        break;

                    case "Uttar Pradesh" : SelectedState = "UP";
                        break;

                    case "West Bengal" : SelectedState = "WB";
                        break;

                    case "Andaman & Nicobar Islands" : SelectedState = "AN";
                        break;

                    case "Chandigarh" : SelectedState = "CH";
                        break;

                    case "Dadra and Nagar Haveli" : SelectedState = "DH";
                        break;

                    case "Daman and Diu" : SelectedState = "DD";
                        break;

                    case "Delhi" : SelectedState = "DL";
                        break;

                    case "Lakshadweep" : SelectedState = "LD";
                        break;

                    case "Pondicherry" : SelectedState = "PY";
                        break;

                }

                StringRequest strReq = new StringRequest(Request.Method.GET,
                        AppConfig.USER_URL_REGISTER+"dv_"+SelectedState+"/users?transform=1&columns=uidai,name,contact_no,address,dob,puc_no,insurance_no&filter=uidai,eq,"+UIDAI+"&filter=password,eq,"+Password, new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "Login Response: " + response);
                        hideDialog();

                        try {
                            JSONObject jObj = new JSONObject(response);

                            JSONArray users = jObj.getJSONArray("users");
                            JSONObject user = users.getJSONObject(0);
                            String uidai = user.getString("uidai");
                            String name = user.getString("name");
                            String contact = user.getString("contact_no");
                            String address = user.getString("address");
                            String dob = user.getString("dob");
                            String puc_no = user.getString("puc_no");


                            SharedPreferences settings = getSharedPreferences("profile",Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = settings.edit();
                            editor.putString("uidai", uidai);
                            editor.putString("name", name);
                            editor.putString("contact", contact);
                            editor.putString("address", address);
                            editor.putString("dob", dob);
                            editor.putString("state", SelectedState);
                            editor.putString("puc_no",puc_no);
                            editor.commit();

                            Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                            startActivity(intent);
                            finish();
                        }


                        catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                            if (UIDAI.length() < 12)
                                Toast.makeText(getApplicationContext(), "UIDAI is less than 12 digits.", Toast.LENGTH_LONG).show();
                            else if (Password.length() < 8)
                                Toast.makeText(getApplicationContext(), "Password is less than 8 characters.", Toast.LENGTH_LONG).show();
                            else if (UIDAI.matches(".*[a-z].*"))
                                Toast.makeText(getApplicationContext(), "UIDAI should only have numerals. ", Toast.LENGTH_LONG).show();
                            else if (Password.isEmpty()==true)
                                Toast.makeText(getApplicationContext(), "Password can't be empty. Please enter Password.", Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "Registration Error: " + error.getMessage());
                        Toast.makeText(getApplicationContext(),
                                error.getMessage(), Toast.LENGTH_LONG).show();
                        hideDialog();
                    }
                }) {



                };

                // Adding request to request queue
                AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
            }
            private void showDialog() {
                if (!pDialog.isShowing())
                    pDialog.show();
            }

            private void hideDialog() {
                if (pDialog.isShowing())
                    pDialog.dismiss();
            }
        });

    }

    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Go to Home Screen.")
                .setMessage("Are you sure you want to go to home screen?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(cf.samarpan.digivaahan.user.LoginActivity.this, cf.samarpan.digivaahan.MainActivity.class);
                        startActivity(intent);
                        finish();

                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }




}
