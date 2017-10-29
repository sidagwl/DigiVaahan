package cf.samarpan.digivaahan.user;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import cf.samarpan.digivaahan.R;
import cf.samarpan.digivaahan.app.AppConfig;
import cf.samarpan.digivaahan.app.AppController;

import static android.R.id.list;
import static android.content.ContentValues.TAG;

public class RegisterActivity extends Activity {

    private int step=0;
    private Button submit;
    private Calendar myCalendar;
    private DatePickerDialog.OnDateSetListener date;


    private TextView CreateAccountText;

    private TextInputLayout CreateAccountUIDAIField;
    private TextView CreateAccountUIDAI;

    private TextInputLayout CreateAccountNameField;
    private TextView CreateAccountName;

    private TextInputLayout CreateAccountContactField;
    private TextView CreateAccountContact;

    private TextInputLayout CreateAccountAddressField;
    private TextView CreateAccountAddress;

    private TextInputLayout CreateAccountStateField;
    private Spinner CreateAccountState;

    private TextInputLayout CreateAccountDOBField;
    private TextView CreateAccountDOB;

    private TextInputLayout CreateAccountPasswordField;
    private TextView CreateAccountPassword;

    private TextInputLayout CreateAccountConfirmPasswordField;
    private TextView CreateAccountConfirmPassword;

    private Button CreateAccountButton1;
    private Button CreateAccountButton2;
    private Button CreateAccountButton3;

    private String UIDAI;
    private String Name;
    private String Contact;
    private String Address;
    private String SelectedState;
    private String DOB;
    private String Password;
    private String ConfirmPassword;
    private ProgressDialog pDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_register);

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        CreateAccountText = (TextView) findViewById(R.id.CreateAccountText);

        CreateAccountUIDAIField = (TextInputLayout) findViewById(R.id.CreateAccountUIDAIField);
        CreateAccountUIDAI = (TextView) findViewById(R.id.CreateAccountUIDAI);

        CreateAccountNameField = (TextInputLayout) findViewById(R.id.CreateAccountNameField);
        CreateAccountName = (TextView) findViewById(R.id.CreateAccountName);

        CreateAccountContactField = (TextInputLayout) findViewById(R.id.CreateAccountContactField);
        CreateAccountContact = (TextView) findViewById(R.id.CreateAccountContact);

        CreateAccountAddressField = (TextInputLayout) findViewById(R.id.CreateAccountAddressField);
        CreateAccountAddress = (TextView) findViewById(R.id.CreateAccountAddress);

        CreateAccountStateField = (TextInputLayout) findViewById(R.id.CreateAccountStateField);
         CreateAccountState = (Spinner) findViewById(R.id.CreateAccountStates);

        CreateAccountDOBField = (TextInputLayout) findViewById(R.id.CreateAccountDOBField);
        CreateAccountDOB = (TextView) findViewById(R.id.CreateAccountDOB);

        CreateAccountPasswordField = (TextInputLayout) findViewById(R.id.CreateAccountPasswordField);
        CreateAccountPassword = (TextView) findViewById(R.id.CreateAccountPassword);

        CreateAccountConfirmPasswordField = (TextInputLayout) findViewById(R.id.CreateAccountConfirmPasswordField);
        CreateAccountConfirmPassword = (TextView) findViewById(R.id.CreateAccountConfirmPassword);

        CreateAccountButton1 = (Button) findViewById(R.id.CreateAccountNextButton1);
        CreateAccountButton2 = (Button) findViewById(R.id.CreateAccountNextButton2);
        CreateAccountButton3 = (Button) findViewById(R.id.CreateAccountNextButton3);


        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.states, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        CreateAccountState.setAdapter(adapter);

        CreateAccountButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                step=1;
                CreateAccountText.setVisibility(View.GONE);
                CreateAccountButton1.setVisibility(View.GONE);
                CreateAccountUIDAIField.setVisibility(View.VISIBLE);
                CreateAccountButton2.setVisibility(View.VISIBLE);
            }
        });
        CreateAccountButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                step=2;
                CreateAccountButton2.setVisibility(View.GONE);
                CreateAccountUIDAIField.setVisibility(View.GONE);
                CreateAccountNameField.setVisibility(View.VISIBLE);
                CreateAccountContactField.setVisibility(View.VISIBLE);
                CreateAccountAddressField.setVisibility(View.VISIBLE);
                CreateAccountStateField.setVisibility(View.VISIBLE);
                CreateAccountDOBField.setVisibility(View.VISIBLE);
                CreateAccountPasswordField.setVisibility(View.VISIBLE);
                CreateAccountConfirmPasswordField.setVisibility(View.VISIBLE);
                CreateAccountButton3.setVisibility(View.VISIBLE);
            }
        });
        CreateAccountButton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                UIDAI = CreateAccountUIDAI.getText().toString().trim();
                Name = CreateAccountName.getText().toString().trim();
                Contact = CreateAccountContact.getText().toString().trim();
                Address = CreateAccountAddress.getText().toString().trim();
                SelectedState = CreateAccountState.getSelectedItem().toString().trim();
                DOB = CreateAccountDOB.getText().toString().trim();
                Password = CreateAccountPassword.getText().toString().trim();
                ConfirmPassword = CreateAccountConfirmPassword.getText().toString().trim();
                // Tag used to cancel the request
                String tag_string_req = "req_register";

                pDialog.setMessage("Registering ...");
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

                StringRequest strReq = new StringRequest(Request.Method.POST,
                        AppConfig.USER_URL_REGISTER+"dv_"+SelectedState+"/users", new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "Register Response: " + response.toString());
                        hideDialog();

                        try {
                            if (response.equals("0")) {

                                Toast.makeText(getApplicationContext(), "User successfully registered. Try login now!", Toast.LENGTH_LONG).show();

                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            } else {

                                // Error occurred in registration. Get the error
                                // message
                                Toast.makeText(getApplicationContext(),
                                        "Error occured in Registration!", Toast.LENGTH_LONG).show();
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
                        params.put("uidai", UIDAI);
                        params.put("name", Name);
                        params.put("contact_no", Contact);
                        params.put("address", Address);
                        params.put("dob", DOB);
                        params.put("password", Password);

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

        myCalendar = Calendar.getInstance();
        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub

                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "yyyy-MM-dd"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                CreateAccountDOB.setText(sdf.format(myCalendar.getTime()));
                DOB=sdf.format(myCalendar.getTime());
            }

        };
        CreateAccountDOB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // TODO Auto-generated method stub
                new DatePickerDialog(RegisterActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }
    @Override
    public void onBackPressed() {
        switch(step) {
            case 0 :
                new AlertDialog.Builder(this)
                        .setTitle("Go to Home Screen")
                        .setMessage("Are you sure you want to go to Home Screen")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(cf.samarpan.digivaahan.user.RegisterActivity.this, cf.samarpan.digivaahan.MainActivity.class);
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

                break;
            case 1 :
                new AlertDialog.Builder(this)
                        .setTitle("Quit Registration")
                        .setMessage("Are you sure you want to quit registration?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                                CreateAccountButton2.setVisibility(View.GONE);
                                CreateAccountUIDAIField.setVisibility(View.GONE);
                                CreateAccountButton1.setVisibility(View.VISIBLE);
                                CreateAccountText.setVisibility(View.VISIBLE);
                                step--;
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

                break;

            case 2 :
                CreateAccountButton3.setVisibility(View.GONE);
                CreateAccountNameField.setVisibility(View.GONE);
                CreateAccountContactField.setVisibility(View.GONE);
                CreateAccountAddressField.setVisibility(View.GONE);
                CreateAccountStateField.setVisibility(View.GONE);
                CreateAccountDOBField.setVisibility(View.GONE);
                CreateAccountPasswordField.setVisibility(View.GONE);
                CreateAccountConfirmPasswordField.setVisibility(View.GONE);
                CreateAccountUIDAIField.setVisibility(View.VISIBLE);
                CreateAccountButton2.setVisibility(View.VISIBLE);
                step--;
                break;
        }
    }

}
