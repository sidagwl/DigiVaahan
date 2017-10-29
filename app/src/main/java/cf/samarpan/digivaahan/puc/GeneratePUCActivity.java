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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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

public class GeneratePUCActivity extends AppCompatActivity {

    private ProgressDialog pDialog;
    String LicenseNumber;
    String PUCNumber;
    String customeruidai;
    String vehiclenumber;
    String testdate;
    String contactnumber;
    String certificateprice;
    String psCO2;
    String mlCO2;
    String psHC;
    String mlHC;
    String psCO;
    String mlCO;
    String psO2;
    String mlO2;

    String issueruidai;
    String vendor_name;
    String firm_name;
    String firm_address;
    String state;
    String rto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.puc_activity_generate_puc);

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        final TextView PUCCertificateUIDAIText = (TextView) findViewById(R.id.PUCCertificateUIDAIText);
        final EditText PUCCertificateLicenseNumberText = (EditText) findViewById(R.id.PUCCertificateLicenseNumberText);
        final TextView PUCCertificateCenterNameText = (TextView) findViewById(R.id.PUCCertificateCenterNameText);
        final TextView PUCCertificateAddressText = (TextView) findViewById(R.id.PUCCertificateAddressText);
        final EditText PUCCertificatePUCNoText = (EditText) findViewById(R.id.PUCCertificatePUCNoText);
        final EditText PUCCertificateCustomerUIDAIText = (EditText) findViewById(R.id.PUCCertificateCustomerUIDAIText);
        final EditText PUCCertificateVehicleNumberText = (EditText) findViewById(R.id.PUCCertificateVehicleNumberText);
        final EditText PUCCertificateCustomerTestDateText = (EditText) findViewById(R.id.PUCCertificateCustomerTestDateText);
        final EditText PUCCertificateCustomerContactNumberText = (EditText) findViewById(R.id.PUCCertificateCustomerContactNumberText);

        final EditText psCO2text = (EditText) findViewById(R.id.psCO2text);
        final EditText mlCO2text = (EditText) findViewById(R.id.mlCO2text);
        final EditText psHCtext = (EditText) findViewById(R.id.psHCtext);
        final EditText mlHCtext = (EditText) findViewById(R.id.mlHCtext);
        final EditText psCOtext = (EditText) findViewById(R.id.psCOtext);
        final EditText mlCOtext = (EditText) findViewById(R.id.mlCOtext);
        final EditText psO2text = (EditText) findViewById(R.id.psO2text);
        final EditText mlO2text = (EditText) findViewById(R.id.mlO2text);
        final EditText PUCCertificatePriceText = (EditText) findViewById(R.id.PUCCertificatePriceText);
        final Button PUCGenerateButton = (Button) findViewById(R.id.PUCGenerateButton);
        SharedPreferences settings = getSharedPreferences("pucprofile",
                Context.MODE_PRIVATE);
        issueruidai = settings.getString( "uidai" , "");
        vendor_name = settings.getString( "vendor_name" , "");
        firm_name = settings.getString( "firm_name" , "");
        firm_address = settings.getString( "firm_address" , "");
        state = settings.getString( "state" , "");
        rto = settings.getString( "rto" , "");

        PUCCertificateUIDAIText.setText(issueruidai);
        PUCCertificateCenterNameText.setText(firm_name);
        PUCCertificateAddressText.setText(firm_address);


        PUCGenerateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Tag used to cancel the request
                String tag_string_req = "req_issuepuc";

                LicenseNumber = PUCCertificateLicenseNumberText.getText().toString().trim();
                PUCNumber = PUCCertificatePUCNoText.getText().toString().trim();
                customeruidai = PUCCertificateCustomerUIDAIText.getText().toString().trim();
                vehiclenumber = PUCCertificateVehicleNumberText.getText().toString().trim();
                testdate = PUCCertificateCustomerTestDateText.getText().toString().trim();
                contactnumber = PUCCertificateCustomerContactNumberText.getText().toString().trim();
                certificateprice = PUCCertificatePriceText.getText().toString().trim();
                psCO2 = psCO2text.getText().toString().trim();
                mlCO2 = mlCO2text.getText().toString().trim();
                psHC = psHCtext.getText().toString().trim();
                mlHC = mlHCtext.getText().toString().trim();
                psCO = psCOtext.getText().toString().trim();
                mlCO = mlCOtext.getText().toString().trim();
                psO2 = psO2text.getText().toString().trim();
                mlO2 = mlO2text.getText().toString().trim();


                Button PUCGenerateButton = (Button) findViewById(R.id.PUCGenerateButton);




                pDialog.setMessage("Issuing PUC Certificate ...");
                showDialog();
                StringRequest strReq = new StringRequest(Request.Method.POST,
                        AppConfig.USER_URL_REGISTER+"dv_"+state+"/"+state+"-"+rto+"_puc", new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "Register Response: " + response.toString());
                        hideDialog();

                        try {
                            if (!response.equals("null")) {
                                
                                Toast.makeText(getApplicationContext(), "PUC Generated!", Toast.LENGTH_LONG).show();

                                Intent intent = new Intent(GeneratePUCActivity.this, cf.samarpan.digivaahan.puc.MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {

                                // Error occurred in registration. Get the error
                                // message
                                Toast.makeText(getApplicationContext(),
                                        "Error occured in issuing PUC!", Toast.LENGTH_LONG).show();
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
                        // Posting params to register url
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("issuer_uidai", issueruidai);
                        params.put("customer_uidai", customeruidai);
                        params.put("license_no", LicenseNumber);
                        params.put("centre_name", firm_name);
                        params.put("centre_address", firm_address);
                        params.put("puc_no", PUCNumber);
                        params.put("vehicle_no", vehiclenumber);
                        params.put("test_date", testdate);
                        params.put("valid_till", contactnumber);
                        params.put("contact_no", psCO2);
                        params.put("fuel_type", mlCO2);
                        params.put("certificate_price", certificateprice);
                        params.put("co_std", psCO);
                        params.put("hc_std", mlHC);
                        params.put("co2_std",psCO2 );
                        params.put("o2_std", mlO2);
                        params.put("co_measured", mlCO);
                        params.put("hc_measured", psHC);
                        params.put("co2_measured", mlCO2);
                        params.put("o2_measured", mlO2);

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
                .setTitle("Go to user Profile")
                .setMessage("Are you sure you want to go to user profile")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(cf.samarpan.digivaahan.puc.GeneratePUCActivity.this, cf.samarpan.digivaahan.puc.MainActivity.class);
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
