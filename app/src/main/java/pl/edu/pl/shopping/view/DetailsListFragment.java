package pl.edu.pl.shopping.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pl.edu.pl.shopping.R;

/**
 * Created by root on 24.06.16.
 */
public class DetailsListFragment extends Fragment {
    public static String KEY_LIST_NAME = "KEY_LIST_NAME";

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey(KEY_LIST_NAME)) {
            showSelectedList(bundle.getString(KEY_LIST_NAME));
        }
    }

    public void showSelectedList(String listName) {
        ((TextView)getView().findViewById(R.id.textViewList)).setText(listName);
    }
}
