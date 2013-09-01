package com.example.ca1;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAndTextListAdapter extends ArrayAdapter<PhotoItem>{

	@Override
	  public void notifyDataSetChanged() {
	    super.notifyDataSetChanged();
	  }
	public ImageAndTextListAdapter(Activity context, List<PhotoItem> photoitems) {
		super(context,0, photoitems);
		// TODO Auto-generated constructor stub
	}
	
	 @Override
	     public View getView(int position, View convertView, ViewGroup parent) {
	         Activity activity = (Activity) getContext(); 
	         LayoutInflater inflater = activity.getLayoutInflater();
	         
	         View rowView = inflater.inflate(R.layout.photo_row, null);
	         
	         PhotoItem photoItem = getItem(position);
	         
	         ImageView imageView = (ImageView)rowView.findViewById(R.id.imageView1); 
	         imageView.setImageBitmap(PhotoHelper.getRemoteImage("http://137.132.247.137/images/thumb-"+photoItem.getFile()));	        
	         TextView caption = (TextView)rowView.findViewById(R.id.photo_caption);
	         caption.setText(photoItem.getCaption());
	         TextView location = (TextView)rowView.findViewById(R.id.photo_location);
	         location.setText(photoItem.getLocation());
	         
	         //able(loadImageFromUrl(imageAndText.getImageUrl()))
	         
	         return rowView;
	 }


}
