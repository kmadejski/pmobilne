package pl.edu.pl.shopping.view;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by root on 24.06.16.
 */
public class ShoppingListFragment extends ListFragment {
    private OnShoppingListFragmentSelectedListener onShoppingListFragmentSelectedListener;

    public void setOnShoppingListFragmentSelectedListener(OnShoppingListFragmentSelectedListener onShoppingListFragmentSelectedListener) {
        this.onShoppingListFragmentSelectedListener = onShoppingListFragmentSelectedListener;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //TODO podpiac tu dao
        String[] countries = new String[]{"zakupy dla noska", "zakupy na poniedzialek", "na uczelnie"};

        ListAdapter countryAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, countries);

        setListAdapter(countryAdapter);

        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (onShoppingListFragmentSelectedListener != null) {
                    onShoppingListFragmentSelectedListener.onItemSelected(((TextView) view).getText().toString());
                }
            }
        });
    }
}
