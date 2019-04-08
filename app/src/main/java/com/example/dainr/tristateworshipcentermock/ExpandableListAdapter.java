package com.example.dainr.tristateworshipcentermock;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

//  https://stackoverflow.com/questions/38765790/default-navigation-drawer-view-to-expandablelistview

class ExpandableListAdapter extends BaseExpandableListAdapter {

    private final Context context;

    // header titles
    private final List <String> listDataHeader;

    // child data
    private final HashMap <String, List <String>> listDataChild;

    private LayoutInflater infalInflater;

    ExpandableListAdapter(Context context, List <String> listDataHeader, HashMap <String, List <String>> listDataChild) {
        this.context = context;
        this.listDataHeader = listDataHeader;
        this.listDataChild = listDataChild;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return Objects.requireNonNull(this.listDataChild.get(this.listDataHeader.get(groupPosition))).get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group_child, null);
        }

        TextView txtListChild = convertView.findViewById(R.id.lblListItem);

        txtListChild.setText(childText);
        return convertView;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int getChildrenCount(int groupPosition) {

        if (this.listDataChild.get(this.listDataHeader.get(groupPosition)) == null) return 0;
        else
            return Objects.requireNonNull(this.listDataChild.get(this.listDataHeader.get(groupPosition))).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.listDataHeader.size();

    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group_header, null);
        }

        TextView lblListHeader = convertView.findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}