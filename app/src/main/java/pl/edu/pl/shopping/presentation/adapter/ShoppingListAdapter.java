package pl.edu.pl.shopping.presentation.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import pl.edu.pl.shopping.R;
import pl.edu.pl.shopping.data.entity.ShoppingList;
import pl.edu.pl.shopping.presentation.ShoppingListActivity;
import pl.edu.pl.shopping.presentation.fragment.DetailsFragment;

/**
 * Created by wojtek on 24.06.16.
 */

public class ShoppingListAdapter extends ArrayAdapter<ShoppingList> {

    private Context context;
    private List<ShoppingList> list;

    public ShoppingListAdapter(Context context, int resource, List<ShoppingList> objects) {
        super(context, resource, objects);

        this.context = context;
        this.list = objects;
    }

    @Override
    public int getCount() {
        return this.list.size();
    }

    public static class ViewHolder {
        public TextView name;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(R.layout.shopping_line_item, parent, false);

            holder = new ViewHolder();
            holder.name = (TextView) row.findViewById(R.id.shopping_list_name);

            holder.name.setText("Dsadasa");

            row.setTag(holder);
        } else  {
            holder = (ViewHolder) row.getTag();
        }

        final ShoppingList item = list.get(position);
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShoppingListActivity.class);
                intent.putExtra(DetailsFragment.SHOPPING_ID, item.getId());
                context.startActivity(intent);
            }
        });

        return row;
    }
}
