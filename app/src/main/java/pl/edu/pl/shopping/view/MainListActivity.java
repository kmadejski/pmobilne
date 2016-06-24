package pl.edu.pl.shopping.view;


import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import pl.edu.pl.shopping.R;

public class MainListActivity extends AppCompatActivity {

    boolean dualPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ShoppingListFragment masterFragment = null;
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
        if (frameLayout != null) {
            dualPane = false;
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            masterFragment = (ShoppingListFragment) getSupportFragmentManager().findFragmentByTag("MASTER");
            if (masterFragment == null) {
                masterFragment = new ShoppingListFragment();
                fragmentTransaction.add(R.id.frameLayout, masterFragment, "MASTER");
            }
            fragmentTransaction.commit();
        } else {
            dualPane = true;
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            masterFragment = (ShoppingListFragment) getSupportFragmentManager().findFragmentById(R.id.frameLayoutMaster);
            if (masterFragment == null) {
                masterFragment = new ShoppingListFragment();
                fragmentTransaction.add(R.id.frameLayoutMaster, masterFragment);
            }
            DetailsListFragment detailFragment = (DetailsListFragment) getSupportFragmentManager().findFragmentById(R.id.frameLayoutDetail);
            if (detailFragment == null) {
                detailFragment = new DetailsListFragment();
                fragmentTransaction.add(R.id.frameLayoutDetail, detailFragment);
            }
            fragmentTransaction.commit();
        }
        masterFragment.setOnShoppingListFragmentSelectedListener(new OnShoppingListFragmentSelectedListener() {
            @Override
            public void onItemSelected(String countryName) {
                //TODO wywolanie z listy
                sendListName(countryName);
            }
        });
    }

    private void sendListName(String listName) {
        DetailsListFragment detailFragment;
        if (dualPane) {
            //Two pane layout
            detailFragment = (DetailsListFragment) getSupportFragmentManager().findFragmentById(R.id.frameLayoutDetail);
            detailFragment.showSelectedList(listName);
        } else {
            // Single pane layout
            detailFragment = new DetailsListFragment();
            Bundle bundle = new Bundle();
            bundle.putString(DetailsListFragment.KEY_LIST_NAME, listName);
            detailFragment.setArguments(bundle);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frameLayout, detailFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }
}
