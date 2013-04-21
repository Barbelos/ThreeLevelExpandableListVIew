package com.example.threelevelexpandablelistview;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class Adapter extends BaseExpandableListAdapter {
  private List<Object> objects;
  private Activity activity;
  private LayoutInflater inflater;
 
 public Adapter(Activity activity, List<Object> objects) {
   this.objects= objects;
   this.activity= activity;
   this.inflater= (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 }

 @Override
 public Object getChild(int groupPosition, int childPosition) {
  return objects.get(groupPosition).getObjects().get(childPosition);
 }

 @Override
 public long getChildId(int groupPosition, int childPosition) {
  return childPosition;
 }

 @Override
 public View getChildView(int groupPosition, int childPosition,
   boolean isLastChild, View convertView, ViewGroup parent) {
  
  Object object= (Object) getChild(groupPosition, childPosition);
  CustomExpandableListView subObjects= (CustomExpandableListView) convertView;;
  if (convertView==null) {
   subObjects= new CustomExpandableListView(activity);
   Adapter2 adapter= new Adapter2(activity, object);
   subObjects.setAdapter(adapter);
  }

  return subObjects;
 }

 @Override
 public int getChildrenCount(int groupPosition) {
  return objects.get(groupPosition).getObjects().size();
 }

 @Override
 public Object getGroup(int groupPosition) {
  return objects.get(groupPosition);
 }

 @Override
 public int getGroupCount() {
  return objects.size();
 }
 
 @Override
 public long getGroupId(int groupPosition) {
  return groupPosition;
 }
 
 @Override
 public View getGroupView(int groupPosition, boolean isExpanded,
   View convertView, ViewGroup parent) {

  Object object= (Object) getGroup(groupPosition);
  if (convertView==null) {
   convertView= inflater.inflate(R.layout.listview_element, null);
  }
  
  TextView name= (TextView) convertView.findViewById(R.id.name);
  name.setText(object.getName());

  return convertView;
 }

 @Override
 public boolean hasStableIds() {
  return true;
 }

 @Override
 public boolean isChildSelectable(int groupPosition, int childPosition) {
  return true;
 }
}


class Adapter2 extends BaseExpandableListAdapter {
 private Object object;
 private LayoutInflater inflater; 
 private Activity activity;
 
 public Adapter2(Activity activity, Object object) {
  this.activity= activity;
  this.object= object;
  this.inflater= (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 }

 @Override
 public Object getChild(int groupPosition, int childPosition) {
  return object.getObjects().get(childPosition);
 }

 @Override
 public long getChildId(int groupPosition, int childPosition) {
  return childPosition;
 }

 @Override
 public View getChildView(int groupPosition, int childPosition,
   boolean isLastChild, View convertView, ViewGroup parent) {

  Object object= (Object) getChild(0, childPosition);
  if (convertView==null) {
   convertView= inflater.inflate(R.layout.listview_element, null);

   Resources r = activity.getResources();
   float px40 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, r.getDisplayMetrics());
   convertView.setPadding(
     convertView.getPaddingLeft() + (int) px40,
     convertView.getPaddingTop(),
     convertView.getPaddingRight(),
     convertView.getPaddingBottom());
  }
  
  TextView name= (TextView) convertView.findViewById(R.id.name);
  name.setText(object.getName());

  return convertView;
 }

 @Override
 public int getChildrenCount(int groupPosition) {
  return object.getObjects().size();
 }

 @Override
 public Object getGroup(int groupPosition) {
  return object;
 }
 
 @Override
 public int getGroupCount() {
  return 1;
 }
 
 @Override
 public long getGroupId(int groupPosition) {
  return groupPosition;
 }
 
 @Override
 public View getGroupView(int groupPosition, boolean isExpanded,
   View convertView, ViewGroup parent) {

  if (convertView==null) {
   convertView= inflater.inflate(R.layout.listview_element, null);
   Resources r = activity.getResources();
   float px20 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, r.getDisplayMetrics());
   convertView.setPadding(
     convertView.getPaddingLeft() + (int) px20,
     convertView.getPaddingTop(),
     convertView.getPaddingRight(),
     convertView.getPaddingBottom()); 
  }

  TextView name= (TextView) convertView.findViewById(R.id.name);
  name.setText(object.getName());
  
  return convertView;
 }

 @Override
 public boolean hasStableIds() {
  return true;
 }

 @Override
 public boolean isChildSelectable(int groupPosition, int childPosition) {
  return true;
 }
}