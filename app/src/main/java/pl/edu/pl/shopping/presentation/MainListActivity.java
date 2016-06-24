package pl.edu.pl.shopping.presentation;


import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import pl.edu.pl.shopping.R;
import pl.edu.pl.shopping.presentation.fragment.ShoppingListsFragment;

public class MainListActivity extends AppCompatActivity implements OnShoppingListFragmentSelectedListener {

    boolean dualPane;

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

    @Override
    public void onItemSelected(String item) {

    }
}
