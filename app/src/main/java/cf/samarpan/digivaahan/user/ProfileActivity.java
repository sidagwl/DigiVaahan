package cf.samarpan.digivaahan.user;

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

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_profile);
        TextView UserProfileUIDAIText = (TextView) findViewById(R.id.UserProfileUIDAIText);
        TextView UserProfileNameText = (TextView) findViewById(R.id.UserProfileNameText);
        TextView UserProfileContactNumberText = (TextView) findViewById(R.id.UserProfileContactNumberText);
        TextView UserProfileAddressText = (TextView) findViewById(R.id.UserProfileAddressText);
        TextView UserProfileDOBText = (TextView) findViewById(R.id.UserProfileDOBText);
        Button UserProfileEditButton = (Button) findViewById(R.id.UserProfileEditButton);
        Button UserProfilePUCButton = (Button) findViewById(R.id.UserProfilePUCButton);
        Button UserProfileInsuranceButton = (Button) findViewById(R.id.UserProfileInsuranceButton);

        SharedPreferences settings = getSharedPreferences("profile",
                Context.MODE_PRIVATE);
        String uidai = settings.getString( "uidai" , "");
        String name = settings.getString( "name" , "");
        String contact = settings.getString( "contact" , "");
        String address = settings.getString( "address" , "");
        String dob = settings.getString( "dob" , "");

        UserProfileUIDAIText.setText(uidai);
        UserProfileNameText.setText(name);
        UserProfileContactNumberText.setText(contact);
        UserProfileAddressText.setText(address);
        UserProfileDOBText.setText(dob);

        UserProfileEditButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });
        UserProfilePUCButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, ViewPUCActivity.class);
                startActivity(intent);
                finish();
            }
        });
        UserProfileInsuranceButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, ViewInsuranceActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Log out ")
                .setMessage("Are you sure you want to Log out")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(cf.samarpan.digivaahan.user.ProfileActivity.this, cf.samarpan.digivaahan.user.LoginActivity.class);
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
