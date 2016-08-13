package com.frogermcs.recipes.dagger_userscope.user.repositories;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.frogermcs.recipes.dagger_userscope.R;

/**
 * Created by froger_mcs on 13/08/16.
 */

public class RepositoriesListAdapter extends ArrayAdapter<String> {

    public RepositoriesListAdapter(Context context) {
        super(context, 0);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_normal, parent, false);
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvName.setText(getItem(position));
        return convertView;
    }

    private static class ViewHolder {
        TextView tvName;
    }
}
