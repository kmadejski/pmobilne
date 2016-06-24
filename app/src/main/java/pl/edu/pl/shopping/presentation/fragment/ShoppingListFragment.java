package pl.edu.pl.shopping.presentation.fragment;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import pl.edu.pl.shopping.R;
import pl.edu.pl.shopping.data.ShoppingRepository;
import pl.edu.pl.shopping.data.entity.ShoppingList;


public class ShoppingListFragment extends Fragment {

    public static final String SHOPPING_ID = "shopping";

    private EditText productName;
    private EditText quantity;
    private Button button;

    ShoppingList item;
    private ShoppingRepository repository;

    public ShoppingListFragment() {
        // Required empty public constructor
    }

    public static ShoppingListFragment newInstance(long id) {
        ShoppingListFragment fragment = new ShoppingListFragment();
        Bundle args = new Bundle();
        args.putLong(SHOPPING_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        repository = new ShoppingRepository();

        Bundle arguments = getArguments();

        if (arguments.getLong(SHOPPING_ID) != -1) {
            item = repository.getShoppingList(arguments.getLong(SHOPPING_ID));
        } else {
            item = repository.createShoppingList(new ShoppingList());
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping_list, container, false);

        productName = (EditText) view.findViewById(R.id.new_product_name);
        quantity = (EditText) view.findViewById(R.id.new_product_quantity);
        button = (Button) view.findViewById(R.id.add_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShoppingListFragment.this.addProduct();
            }
        });

        return view;
    }

    private void addProduct() {

    }

}
