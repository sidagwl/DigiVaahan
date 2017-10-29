package cf.samarpan.digivaahan.puc;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cf.samarpan.digivaahan.R;

public class MainActivity extends AppCompatActivity {

    Button PUCMainProfile;
    Button PUCMainIssue;
    Button PUCMainChangePassword;
    Button PUCMainLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.puc_activity_main);

        PUCMainProfile = (Button) findViewById(R.id.button2);
        PUCMainIssue = (Button) findViewById(R.id.Button1);
        PUCMainChangePassword = (Button) findViewById(R.id.PUCMainChangePassword);
        PUCMainLogout = (Button) findViewById(R.id.PUCMainLogout);

        PUCMainProfile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, cf.samarpan.digivaahan.puc.ProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });
        PUCMainIssue.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, cf.samarpan.digivaahan.puc.GeneratePUCActivity.class);
                startActivity(intent);
                finish();
            }
        });
        PUCMainChangePassword.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, cf.samarpan.digivaahan.puc.EditProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });
        PUCMainLogout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, cf.samarpan.digivaahan.MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Log Out")
                .setMessage("Are you sure you want to Log out from your issuer profile")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(cf.samarpan.digivaahan.puc.MainActivity.this, cf.samarpan.digivaahan.MainActivity.class);
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

