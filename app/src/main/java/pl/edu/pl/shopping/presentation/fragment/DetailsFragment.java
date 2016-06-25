package pl.edu.pl.shopping.presentation.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import pl.edu.pl.shopping.R;
import pl.edu.pl.shopping.data.ShoppingRepository;
import pl.edu.pl.shopping.data.entity.ListItem;
import pl.edu.pl.shopping.data.entity.ShoppingList;
import pl.edu.pl.shopping.presentation.adapter.ItemListAdapter;


public class DetailsFragment extends Fragment {

    public static final String SHOPPING_ID = "shopping";
    public static final String SHOPPING_NAME = "shopping_name";

    private EditText productName;
    private EditText quantity;
    private Button button;
    private ListView listView;
    private ItemListAdapter adapter;

    ShoppingList item;
    List<ListItem> itemList;
    private ShoppingRepository repository;

    public DetailsFragment() {
        // Required empty public constructor
    }

    public static DetailsFragment newInstance(Long id, String name) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putLong(SHOPPING_ID, id);
        args.putString(SHOPPING_NAME, name);
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
        } else if(arguments.getString(SHOPPING_NAME) != null) {
            ShoppingList shoppingList = new ShoppingList();
            shoppingList.setName(arguments.getString(SHOPPING_NAME));
            item = repository.createShoppingList(shoppingList);
        }

        itemList = repository.getShoppingListItems(item.getId());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        productName = (EditText) view.findViewById(R.id.new_product_name);
        quantity = (EditText) view.findViewById(R.id.new_product_quantity);
        button = (Button) view.findViewById(R.id.add_button);
        listView = (ListView) view.findViewById(R.id.shopping_list_items);

        adapter = new ItemListAdapter(getActivity(), R.layout.item_line, itemList);

        listView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailsFragment.this.addProduct();
            }
        });

        return view;
    }

    private void addProduct() {

        ListItem listItem = new ListItem();
        listItem.setShoppingID(item.getId());
        listItem.setName(productName.getText().toString());
        listItem.setQuantity(quantity.getText().toString());

        ShoppingRepository repository = new ShoppingRepository();
        repository.createListItem(listItem);

        adapter.add(listItem);
        adapter.notifyDataSetChanged();

    }

}
