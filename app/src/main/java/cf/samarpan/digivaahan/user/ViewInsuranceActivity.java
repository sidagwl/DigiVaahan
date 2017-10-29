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
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cf.samarpan.digivaahan.R;
import cf.samarpan.digivaahan.app.AppConfig;
import cf.samarpan.digivaahan.app.AppController;
import cf.samarpan.digivaahan.puc.*;

import static android.content.ContentValues.TAG;


public class ViewInsuranceActivity extends AppCompatActivity {
    private ProgressDialog pDialog;
    private Button GenerateInsurance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_view_insurance);


        final TextView PolicyNo = (TextView) findViewById(R.id.policy_noText);
        final TextView BranchCode = (TextView) findViewById(R.id.Branch_codeText);
        final TextView HolderName = (TextView) findViewById(R.id.Holders_NameText);
        final TextView HoldersUIDAI = (TextView) findViewById(R.id.Holders_UIDAIText);
        final TextView HoldersAddress = (TextView) findViewById(R.id.Holders_AddressText);
        final TextView City = (TextView) findViewById(R.id.CityText);
        final TextView State = (TextView) findViewById(R.id.StateText);
        final TextView Pincode = (TextView) findViewById(R.id.PincodeText);
        final TextView Holderphone = (TextView) findViewById(R.id.HolderPhoneNoText);
        final TextView Holderemail = (TextView) findViewById(R.id.Holders_EmailText);
        final TextView Hypothecation = (TextView) findViewById(R.id.HypothecationText);
        final TextView VehicleRegNo = (TextView) findViewById(R.id.VehicleRegNOText);
        final TextView VehicleYear = (TextView) findViewById(R.id.VehicleYearText);
        final TextView VehicleMake = (TextView) findViewById(R.id.VehicleMakeText);
        final TextView VehicleModel = (TextView) findViewById(R.id.VehicleModelText);
        final TextView VehicleFuel = (TextView) findViewById(R.id.VehicleFuelText);
        final TextView IDV = (TextView) findViewById(R.id.VehicleIDVText);
        final TextView VehicleSeat = (TextView) findViewById(R.id.VehicleSeatText);
        final TextView VehicleChasisNO = (TextView) findViewById(R.id.VehicleChasisNoText);
        final TextView VehicleEngNo = (TextView) findViewById(R.id.VehicleEngineNOText);
        final TextView VehicleCC = (TextView) findViewById(R.id.VehicleCCText);
        final TextView PolicyType = (TextView) findViewById(R.id.VehiclePolicyTypeText);
        final TextView PolicyPremium = (TextView) findViewById(R.id.PolicyPremiumText);
        final TextView PolicySdate = (TextView) findViewById(R.id.PolicyStartDateText);
        final TextView PolcyEDate = (TextView) findViewById(R.id.PolicyEndDateText);

        final SharedPreferences settings = getSharedPreferences("profile",
                Context.MODE_PRIVATE);
        final String insurance_no = settings.getString("insurance_no", "");
        final String uidai = settings.getString("uidai", "");

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        String tag_string_req = "puc";
        pDialog.setMessage("Loading...");
        showDialog();
        StringRequest strReq = new StringRequest(Request.Method.GET,
                AppConfig.USER_URL_REGISTER + "dv_insurance/" +  "sbi_insurance?transform=1&filter=uidai,eq," + uidai, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Login Response: " + response);
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);

                    JSONArray users = jObj.getJSONArray(insurance_no+"sbi_insurance");
                    JSONObject user = users.getJSONObject(0);
                    String policyno = user.getString("policy_num");
                    String branchcode = user.getString("branch_code");
                    String customeruidai = user.getString("uidai");
                    String holdername = user.getString("holder_name");
                    String holderaddress = user.getString("holder_address");
                    String pincode = user.getString("pincode");
                    String holderemail = user.getString("holder_email");
                    String city = user.getString("city");
                    String state = user.getString("state");
                    String holderphone = user.getString("holder_phone");
                    String hypothecation = user.getString("hypothecation");
                    String vehicleregno = user.getString("veh_reg_num");
                    String vehicleyear = user.getString("veh_year");
                    String vehiclemake = user.getString("veh_make");
                    String vehiclemodel = user.getString("veh_model");
                    String vehiclefuel = user.getString("veh_fuel");
                    String vehicleidv = user.getString("veh_idv");
                    String vehicleseat = user.getString("veh_seat");
                    String vehiclechasisno = user.getString("veh_chassis_num");
                    String vehicleengineno = user.getString("veh_engine_num");
                    String vehiclecc = user.getString("veh_cc");
                    String policytype = user.getString("policy_type");
                    String policyprmium = user.getString("policy_premium");
                    String policysdate = user.getString("policy_sdate");
                    String policyedate = user.getString("policy_edate");

                    PolicyNo.setText(policyno);
                    BranchCode.setText(branchcode);
                    HolderName.setText(holdername);
                    HoldersUIDAI.setText(customeruidai);
                    HoldersAddress.setText(holderaddress);
                    State.setText(state);
                    City.setText(city);
                    Holderphone.setText(holderphone);
                    Holderemail.setText(holderemail);
                    Hypothecation.setText(hypothecation);
                    VehicleRegNo.setText(vehicleregno);
                    VehicleYear.setText(vehicleyear);
                    VehicleMake.setText(vehiclemake);
                    VehicleModel.setText(vehiclemodel);
                    VehicleFuel.setText(vehiclefuel);
                    IDV.setText(vehicleidv);
                    VehicleSeat.setText(vehicleseat);
                    VehicleChasisNO.setText(vehiclechasisno);
                    VehicleEngNo.setText(vehicleengineno);
                    VehicleCC.setText(vehiclecc);
                    PolicyType.setText(policytype);
                    PolicyPremium.setText(policyprmium);
                    PolicySdate.setText(policysdate);
                    PolcyEDate.setText(policyedate);
                    Pincode.setText(pincode);


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

        GenerateInsurance = (Button) findViewById(R.id.PUCProfileEditButton);

        GenerateInsurance.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ViewInsuranceActivity.this, PDFfromServerActivity.class);
                startActivity(intent);
                finish();
            }
        });




    }


    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Go to user Profile")
                .setMessage("Are you sure you want to go to user profile")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(cf.samarpan.digivaahan.user.ViewInsuranceActivity.this, cf.samarpan.digivaahan.user.ProfileActivity.class);
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