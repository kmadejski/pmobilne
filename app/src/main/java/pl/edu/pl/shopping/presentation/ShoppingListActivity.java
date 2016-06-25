package pl.edu.pl.shopping.presentation;

import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import pl.edu.pl.shopping.R;
import pl.edu.pl.shopping.presentation.fragment.DetailsFragment;

public class ShoppingListActivity extends AppCompatActivity {

    private static final String SHOPPING_FRAGMENT = "shoppingFragment";

    private long id;
    private ConstraintLayout shoppingList;

    private DetailsFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

        shoppingList = (ConstraintLayout) findViewById(R.id.activity_shopping_list);

        Long shoppingId = getIntent().getLongExtra(DetailsFragment.SHOPPING_ID, -1);
        String name = getIntent().getStringExtra(DetailsFragment.SHOPPING_NAME);

        fragment = DetailsFragment.newInstance(shoppingId, name);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.activity_shopping_list, fragment, SHOPPING_FRAGMENT);
        fragmentTransaction.commit();
    }
}
