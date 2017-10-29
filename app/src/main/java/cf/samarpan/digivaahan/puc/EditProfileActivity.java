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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import cf.samarpan.digivaahan.R;
import cf.samarpan.digivaahan.app.AppConfig;
import cf.samarpan.digivaahan.app.AppController;
import cf.samarpan.digivaahan.user.*;

import static android.content.ContentValues.TAG;

public class EditProfileActivity extends AppCompatActivity {
    private ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.puc_activity_edit_profile);
        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        Button PUCProfileEditButton = (Button) findViewById(R.id.PUCProfileEditButton);
        EditText EditProfilePassword = (EditText) findViewById(R.id.EditProfilePassword);
        final EditText EditProfileConfirmPassword = (EditText) findViewById(R.id.EditProfileConfirmPassword);

        final String[] Password = new String[1];
        final String[] ConfirmPassword = new String[1];;

        SharedPreferences settings = getSharedPreferences("pucprofile",
                Context.MODE_PRIVATE);
        final String state = settings.getString( "state" , "");
        final String rto = settings.getString( "rto" , "");
        final String uidai = settings.getString( "uidai", "");

        PUCProfileEditButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Password[0] = EditProfileConfirmPassword.getText().toString();
                ConfirmPassword[0] = EditProfileConfirmPassword.getText().toString();
                // Tag used to cancel the request
                String tag_string_req = "req_update";

                pDialog.setMessage("Updating ...");
                showDialog();
                StringRequest strReq = new StringRequest(Request.Method.PUT,
                        AppConfig.USER_URL_REGISTER+"dv_"+state+"/"+rto+"_vendors/"+uidai, new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "Register Response: " + response.toString());
                        Log.d(TAG, state+"/"+rto+"_vendors/"+uidai);
                        hideDialog();

                        try {
                            if (response.equals("1")) {

                                Toast.makeText(getApplicationContext(), "Password successfully updated", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(EditProfileActivity.this, cf.samarpan.digivaahan.puc.ProfileActivity.class);
                                startActivity(intent);
                                finish();

                            } else {

                                // Error occurred in registration. Get the error
                                // message
                                Toast.makeText(getApplicationContext(),
                                        "Error occured in updating!", Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
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
                    @Override
                    protected Map<String, String> getParams() {
                        // Posting parameters to login url
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("password", Password[0]);

                        return params;
                    }
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
                .setTitle("Go to issuer Profile")
                .setMessage("Are you sure you want to go to issuer profile")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(cf.samarpan.digivaahan.puc.EditProfileActivity.this, cf.samarpan.digivaahan.puc.MainActivity.class);
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
