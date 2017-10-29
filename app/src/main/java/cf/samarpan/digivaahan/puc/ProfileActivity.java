package cf.samarpan.digivaahan.puc;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cf.samarpan.digivaahan.R;
import cf.samarpan.digivaahan.user.EditProfileActivity;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.puc_activity_profile);

        TextView PUCProfileUIDAIText = (TextView) findViewById(R.id.PUCProfileUIDAIText);
        TextView PUCProfileNameText = (TextView) findViewById(R.id.PUCProfileNameText);
        TextView PUCProfileContactNumberText = (TextView) findViewById(R.id.PUCProfileContactNumberText);
        TextView PUCProfileAddressText = (TextView) findViewById(R.id.PUCProfileAddressText);
        TextView PUCProfileEmailText = (TextView) findViewById(R.id.PUCProfileEmailText);
        Button PUCProfileEditButton = (Button) findViewById(R.id.PUCProfileEditButton);
        TextView PUCProfileFirmText = (TextView) findViewById(R.id.PUCProfileFirmText);
        TextView PUCProfileCityText = (TextView) findViewById(R.id.PUCProfileCityText);
        TextView PUCProfileStateText = (TextView) findViewById(R.id.PUCProfileStateText);
        TextView PUCProfilePinCodeText = (TextView) findViewById(R.id.PUCProfilePinCodeText);

        SharedPreferences settings = getSharedPreferences("pucprofile",
                Context.MODE_PRIVATE);
        String uidai = settings.getString( "uidai" , "");
        String vendor_name = settings.getString( "vendor_name" , "");
        String firm_name = settings.getString( "firm_name" , "");
        String firm_address = settings.getString( "firm_address" , "");
        String city = settings.getString( "city" , "");
        String state = settings.getString( "state" , "");
        String pincode = settings.getString( "pincode" , "");
        String phone = settings.getString( "phone" , "");
        String email = settings.getString( "email" , "");

        PUCProfileUIDAIText.setText(uidai);
        PUCProfileNameText.setText(vendor_name);
        PUCProfileFirmText.setText(firm_name);
        PUCProfileAddressText.setText(firm_address);
        PUCProfileCityText.setText(city);
        PUCProfileStateText.setText(state);
        PUCProfilePinCodeText.setText(pincode);
        PUCProfileContactNumberText.setText(phone);
        PUCProfileEmailText.setText(email);

        PUCProfileEditButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, cf.samarpan.digivaahan.puc.EditProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Go to user Profile")
                .setMessage("Are you sure you want to go to user profile")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(cf.samarpan.digivaahan.puc.ProfileActivity.this, cf.samarpan.digivaahan.puc.MainActivity.class);
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
