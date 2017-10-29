package cf.samarpan.digivaahan.puc;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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

import java.lang.reflect.Array;
import java.util.ArrayList;

import cf.samarpan.digivaahan.R;
import cf.samarpan.digivaahan.app.AppConfig;
import cf.samarpan.digivaahan.app.AppController;
import cf.samarpan.digivaahan.user.ProfileActivity;

import static android.content.ContentValues.TAG;

public class LoginActivity extends AppCompatActivity {

    private Spinner PUCLoginState;
    private Spinner PUCLoginRTO;
    private String UIDAI;
    private String Password;
    private String SelectedState;
    private String SelectedRTO;
    private ProgressDialog pDialog;

    private Button PUCLoginButton;

    private TextView PUCLoginUIDAI;
    private TextView PUCLoginPassword;
    private Spinner UserLoginState;
    private String RTOs[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.puc_activity_login);
        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        PUCLoginState = (Spinner) findViewById(R.id.PUCLoginStates);
        PUCLoginRTO = (Spinner) findViewById(R.id.PUCLoginRTOs);
        PUCLoginUIDAI = (TextView) findViewById(R.id.PUCLoginUIDAI);
        PUCLoginPassword = (TextView) findViewById(R.id.PUCLoginPassword);
        PUCLoginButton = (Button) findViewById(R.id.PUCLoginButton);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.states, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        PUCLoginState.setAdapter(adapter);

        ArrayAdapter adapter1 = ArrayAdapter.createFromResource(this, R.array.rto, android.R.layout.simple_spinner_item);

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        PUCLoginRTO.setAdapter(adapter1);

        PUCLoginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                UIDAI = PUCLoginUIDAI.getText().toString().trim();
                Password = PUCLoginPassword.getText().toString().trim();
                SelectedState = PUCLoginState.getSelectedItem().toString().trim();
                SelectedRTO = PUCLoginRTO.getSelectedItem().toString().trim();

                // Tag used to cancel the request
                String tag_string_req = "req_login";

                pDialog.setMessage("Logging In ...");
                showDialog();

                switch (SelectedState) {
                    case "Andhra Pradesh" : SelectedState = "ap";
                        break;

                    case "Arunachal Pradesh" : SelectedState = "ar";
                        break;

                    case "Assam" : SelectedState = "as";
                        break;

                    case "Bihar" : SelectedState = "br";
                        break;

                    case "Chhattisgarh" : SelectedState = "cg";
                        break;

                    case "Goa" : SelectedState = "ga";
                        break;

                    case "Gujarat" : SelectedState = "gj";
                        break;

                    case "Haryana" : SelectedState = "hr";
                        break;

                    case "Himachal Pradesh" : SelectedState = "hp";
                        break;

                    case "Jammu and Kashmir" : SelectedState = "jk";
                        break;

                    case "Jharkhand" : SelectedState = "jh";
                        break;

                    case "Karnataka" : SelectedState = "ka";
                        break;

                    case "Kerala" : SelectedState = "kl";
                        break;

                    case "Madhya Pradesh" : SelectedState = "mp";
                        break;

                    case "Maharashtra" : SelectedState = "mh";
                        break;

                    case "Manipur" : SelectedState = "mn";
                        break;

                    case "Meghalaya" : SelectedState = "ml";
                        break;

                    case "Mizoram" : SelectedState = "mz";
                        break;

                    case "Nagaland" : SelectedState = "nl";
                        break;

                    case "Orissa" : SelectedState = "or";
                        break;

                    case "Punjab" : SelectedState = "pb";
                        break;

                    case "Rajasthan" : SelectedState = "rj";
                        break;

                    case "Sikkim" : SelectedState = "sk";
                        break;

                    case "Tamil Nadu" : SelectedState = "tn";
                        break;

                    case "Tripura" : SelectedState = "tr";
                        break;

                    case "Uttarakhand" : SelectedState = "uk";
                        break;

                    case "Uttar Pradesh" : SelectedState = "up";
                        break;

                    case "West Bengal" : SelectedState = "wb";
                        break;

                    case "Andaman & Nicobar Islands" : SelectedState = "an";
                        break;

                    case "Chandigarh" : SelectedState = "ch";
                        break;

                    case "Dadra and Nagar Haveli" : SelectedState = "dh";
                        break;

                    case "Daman and Diu" : SelectedState = "dd";
                        break;

                    case "Delhi" : SelectedState = "dl";
                        break;

                    case "Lakshadweep" : SelectedState = "ld";
                        break;

                    case "Pondicherry" : SelectedState = "py";
                        break;

                }

                StringRequest strReq = new StringRequest(Request.Method.GET,
                        AppConfig.USER_URL_REGISTER+"dv_"+SelectedState+"/"+SelectedState+"-"+SelectedRTO+
                                "_vendors?transform=1&columns=uidai,vendor_name,firm_name,firm_address,city,pincode,phone,email&filter=uidai,eq,"+UIDAI+"&filter=password,eq,"+Password, new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "Login Response: " + response);
                        hideDialog();

                        try {
                            JSONObject jObj = new JSONObject(response);

                            JSONArray users = jObj.getJSONArray(SelectedState+"-"+SelectedRTO+"_vendors");
                            JSONObject user = users.getJSONObject(0);
                            String uidai = user.getString("uidai");
                            String vendor_name = user.getString("vendor_name");
                            String firm_name = user.getString("firm_name");
                            String firm_address = user.getString("firm_address");
                            String city = user.getString("city");
                            String pincode = user.getString("pincode");
                            String phone = user.getString("phone");
                            String email = user.getString("email");

                            SharedPreferences settings = getSharedPreferences("pucprofile",Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = settings.edit();
                            editor.putString("uidai", uidai);
                            editor.putString("rto", SelectedRTO);
                            editor.putString("state", SelectedState);
                            editor.putString("vendor_name", vendor_name);
                            editor.putString("firm_name", firm_name);
                            editor.putString("firm_address", firm_address);
                            editor.putString("city", city);
                            editor.putString("pincode", pincode);
                            editor.putString("phone", phone);
                            editor.putString("email", email);
                            editor.commit();
                            if(!response.equals("")) {
                                Intent intent = new Intent(LoginActivity.this, cf.samarpan.digivaahan.puc.MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
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
                .setTitle("Go to home screen")
                .setMessage("Are you sure you want to go to home screen")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(cf.samarpan.digivaahan.puc.LoginActivity.this, cf.samarpan.digivaahan.MainActivity.class);
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
