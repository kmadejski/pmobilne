package pl.edu.pl.shopping.presentation.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import pl.edu.pl.shopping.R;
import pl.edu.pl.shopping.data.ShoppingRepository;
import pl.edu.pl.shopping.data.entity.ShoppingList;
import pl.edu.pl.shopping.presentation.OnShoppingListFragmentSelectedListener;
import pl.edu.pl.shopping.presentation.adapter.ShoppingListAdapter;

/**
 * Created by root on 24.06.16.
 */
public class ShoppingListsFragment extends Fragment {

    ListView listView;
    ShoppingListAdapter adapter;
    List<ShoppingList> shoppingLists;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_shopping_lists, container, false);

        listView = (ListView) view.findViewById(R.id.shopping_lists);
        setUp();
        return view;
    }

    private void setUp() {
        ShoppingRepository repository = new ShoppingRepository();
        shoppingLists = repository.getShoppingLists();
        adapter = new ShoppingListAdapter(getActivity(), R.layout.shopping_line_item, shoppingLists);
        listView.setAdapter(adapter);
    }
}
