package cf.samarpan.digivaahan.user;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import cf.samarpan.digivaahan.R;
import cf.samarpan.digivaahan.app.AppConfig;
import cf.samarpan.digivaahan.app.AppController;

import static android.R.attr.filter;
import static android.content.ContentValues.TAG;

public class ViewPUCActivity extends AppCompatActivity {
    private ProgressDialog pDialog;

    TextView PUCCertificateUIDAIText;
    TextView PUCCertificateLicenseNumberText;
    TextView PUCCertificateCenterNameText;
    TextView PUCCertificateAddressText;
    TextView PUCCertificatePUCNoText;
    TextView PUCCertificateCustomerUIDAIText;
    TextView PUCCertificateVehicleNumberText;

    TextView PUCCertificateCustomerTestDateText;
    TextView PUCCertificateCustomerContactNumberText;

    TextView PUCCertificateCustomerValidTillText;
    TextView PUCCertificateFuelTypeText;
    TextView psCO2text;
    TextView mlCO2text;
    TextView psHCtext;
    TextView mlHCtext;
    TextView psCOtext;
    TextView mlCOtext;
    TextView psO2text;
    TextView mlO2text;
    TextView PUCCertificatePriceText;
    String puc_no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_view_puc);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        // Tag used to cancel the request
        String tag_string_req = "req_login";

        pDialog.setMessage("Loading...");
        showDialog();



        SharedPreferences settings = getSharedPreferences("profile",
                Context.MODE_PRIVATE);
        puc_no = settings.getString("puc_no" , "");
        String state = settings.getString("state" , "");
        final String uidai = settings.getString("uidai", "");

        StringRequest strReq = new StringRequest(Request.Method.GET,
                AppConfig.USER_URL_REGISTER+"dv_"+state+"/"+state+"-01_puc"+"?transform=1&filter=customer_uidai,eq,"+uidai, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Login Response: " + response);
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);

                    JSONArray users = jObj.getJSONArray("mh-01_puc");
                    JSONObject user = users.getJSONObject(0);
                    String issuer_uidai = user.getString("issuer_uidai");
                    String customer_uidai = user.getString("customer_uidai");
                    String license_no = user.getString("license_no");
                    String centre_name = user.getString("centre_name");
                    String centre_address = user.getString("centre_address");
                    String puc_no = user.getString("puc_no");
                    String vehicle_no = user.getString("vehicle_no");
                    String vehicle_type=user.getString("vehicle_type");
                    String test_date = user.getString("test_date");
                    String valid_till = user.getString("valid_till");
                    String contact_no = user.getString("contact_no");
                    String fuel_type = user.getString("fuel_type");
                    String certificate_price = user.getString("certificate_price");
                    String co_std = user.getString("co_std");
                    String hc_std = user.getString("hc_std");
                    String co2_std = user.getString("co2_std");
                    String o2_std = user.getString("o2_std");
                    String co_measured = user.getString("co_measured");
                    String hc_measured = user.getString("hc_measured");
                    String co2_measured = user.getString("co2_measured");
                    String o2_measured = user.getString("o2_measured");
                    TextView PUCCertificateUIDAIText = (TextView) findViewById(R.id.PUCCertificateUIDAIText);
                    TextView PUCCertificateLicenseNumberText = (TextView) findViewById(R.id.PUCCertificateLicenseNumberText);
                    TextView PUCCertificateCenterNameText = (TextView) findViewById(R.id.PUCCertificateCenterNameText);
                    TextView PUCCertificateAddressText = (TextView) findViewById(R.id.PUCCertificateAddressText);
                    TextView PUCCertificatePUCNoText = (TextView) findViewById(R.id.PUCCertificatePUCNoText);
                    TextView PUCCertificateCustomerUIDAIText = (TextView) findViewById(R.id.PUCCertificateCustomerUIDAIText);
                    TextView PUCCertificateVehicleNumberText = (TextView) findViewById(R.id.PUCCertificateVehicleNumberText);

                    TextView PUCCertificateCustomerTestDateText = (TextView) findViewById(R.id.PUCCertificateCustomerTestDateText);
                    TextView PUCCertificateCustomerContactNumberText = (TextView) findViewById(R.id.PUCCertificateCustomerContactNumberText);
                    TextView PUCCertificateCustomerValidTillText = (TextView) findViewById(R.id.PUCCertificateCustomerValidTillText);
                    TextView PUCCertificateFuelTypeText = (TextView) findViewById(R.id.PUCCertificateFuelTypeText);
                    TextView psCO2text = (TextView) findViewById(R.id.psCO2text);
                    TextView mlCO2text = (TextView) findViewById(R.id.mlCO2text);
                    TextView psHCtext = (TextView) findViewById(R.id.psHCtext);
                    TextView mlHCtext = (TextView) findViewById(R.id.mlHCtext);
                    TextView psCOtext = (TextView) findViewById(R.id.psCOtext);
                    TextView mlCOtext = (TextView) findViewById(R.id.mlCOtext);
                    TextView psO2text = (TextView) findViewById(R.id.psO2text);
                    TextView mlO2text = (TextView) findViewById(R.id.mlO2text);
                    TextView PUCCertificatePriceText = (TextView) findViewById(R.id.PUCCertificatePriceText);
                    PUCCertificateUIDAIText.setText(issuer_uidai);
                    PUCCertificateLicenseNumberText.setText(license_no);
                    PUCCertificateCenterNameText.setText(centre_name);
                    PUCCertificateAddressText.setText(centre_address);
                    PUCCertificatePUCNoText.setText(puc_no);
                    PUCCertificateCustomerUIDAIText.setText(customer_uidai);
                    PUCCertificateVehicleNumberText.setText(vehicle_no);

                    PUCCertificateCustomerTestDateText.setText(test_date);
                    PUCCertificateCustomerContactNumberText.setText(contact_no);
                    PUCCertificateCustomerValidTillText.setText(valid_till);
                    PUCCertificateFuelTypeText.setText(fuel_type);
                    psCO2text.setText(co2_std);
                    mlCO2text.setText(co2_measured);
                    psHCtext.setText(hc_std);
                    mlHCtext.setText(hc_measured);
                    psCOtext.setText(co_std);
                    mlCOtext.setText(co_measured);
                    psO2text.setText(o2_std);
                    mlO2text.setText(o2_measured);
                    PUCCertificatePriceText.setText(certificate_price);

                } catch (JSONException e) {
                    // JSON error
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

    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Go to user Profile")
                .setMessage("Are you sure you want to go to user profile")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(cf.samarpan.digivaahan.user.ViewPUCActivity.this, cf.samarpan.digivaahan.user.ProfileActivity.class);
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






    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
