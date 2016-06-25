package pl.edu.pl.shopping.presentation;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


import pl.edu.pl.shopping.R;
import pl.edu.pl.shopping.ShoppingApplication;


public class SettingsActivity extends AppCompatActivity {
    public static final String PREFS_NAME = "ShoppingNumbers";
    public static final String PHONE_NO_1 = "phone_no_1";
    public static final String PHONE_NO_2 = "phone_no_2";
    public static final String PHONE_NO_3 = "phone_no_3";
    public EditText phone1;
    public EditText phone2;
    public EditText phone3;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        phone1 = (EditText)findViewById(R.id.phone_no_1);
        phone2 = (EditText)findViewById(R.id.phone_no_2);
        phone3 = (EditText)findViewById(R.id.phone_no_3);
        prefs = ShoppingApplication.provideSharedPreferences();

        readPreferences();
    }

    private void readPreferences() {
        phone1.setText(prefs.getString(PHONE_NO_1, null));
        phone2.setText(prefs.getString(PHONE_NO_2, null));
        phone3.setText(prefs.getString(PHONE_NO_3, null));
    }

    public void saveSettings(View view) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PHONE_NO_1, phone1.getText().toString());
        editor.putString(PHONE_NO_2, phone2.getText().toString());
        editor.putString(PHONE_NO_3, phone3.getText().toString());

        editor.commit();

        Intent myIntent = new Intent(this, MainListActivity.class);
        this.startActivity(myIntent);
    }
}
