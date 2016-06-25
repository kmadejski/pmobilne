package pl.edu.pl.shopping.presentation;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import pl.edu.pl.shopping.R;
import pl.edu.pl.shopping.data.SmsParser;
import pl.edu.pl.shopping.presentation.fragment.DetailsFragment;
import pl.edu.pl.shopping.presentation.fragment.ShoppingListsFragment;

public class MainListActivity extends AppCompatActivity implements OnShoppingListFragmentSelectedListener {

    boolean dualPane;

    @Override
    public MenuInflater getMenuInflater() {
        return super.getMenuInflater();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ShoppingListsFragment masterFragment = null;
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
        if (frameLayout != null) {

            dualPane = false;
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            masterFragment = (ShoppingListsFragment) getSupportFragmentManager().findFragmentByTag("MASTER");
            if (masterFragment == null) {
                masterFragment = new ShoppingListsFragment();
                fragmentTransaction.add(R.id.frameLayout, masterFragment, "MASTER");
            }
            fragmentTransaction.commit();

        } else {

        }




    }

    public void createShoppingList(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Podaj nazwe listy zakupów");
        final EditText input = new EditText(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        builder.setView(input);
        builder.setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.setPositiveButton("Zapisz", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MainListActivity.this, ShoppingListActivity.class);
                intent.putExtra(DetailsFragment.SHOPPING_NAME, input.getText().toString());

                startActivity(intent);
            }
        });

        builder.show();

    }

    @Override
    public void onItemSelected(String item) {

    }


    public void goToSettings(View view) {
        Intent myIntent = new Intent(MainListActivity.this, SettingsActivity.class);
        MainListActivity.this.startActivity(myIntent);
    }
}
