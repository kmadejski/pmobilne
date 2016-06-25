package pl.edu.pl.shopping.presentation.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

import pl.edu.pl.shopping.R;
import pl.edu.pl.shopping.data.ShoppingRepository;
import pl.edu.pl.shopping.data.entity.ListItem;

/**
 * Created by wojtek on 25.06.16.
 */

public class ItemListAdapter extends ArrayAdapter<ListItem> {

    private Context context;
    private List<ListItem> list;

    public ItemListAdapter(Context context, int resource, List<ListItem> objects) {
        super(context, resource, objects);

        this.context = context;
        this.list = objects;
    }

    public static class ViewHolder {
        public TextView name;
        public TextView qtn;
        public CheckBox status;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(R.layout.item_line, parent, false);

            holder = new ViewHolder();
            holder.name = (TextView) row.findViewById(R.id.product_name_line);
            holder.qtn = (TextView) row.findViewById(R.id.product_qtn_line);
            holder.status = (CheckBox) row.findViewById(R.id.product_line_status);

            row.setTag(holder);
        } else  {
            holder = (ViewHolder) row.getTag();
        }

        final ListItem item = list.get(position);

        if (item.getStatus() == 1) {
            holder.status.setChecked(true);
        }

        holder.status.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                ShoppingRepository repository = new ShoppingRepository();
                repository.checkItem(item.getId(), isChecked);

            }
        });


        holder.name.setText(item.getName());
        holder.qtn.setText(item.getQuantity());

        return row;
    }
}
