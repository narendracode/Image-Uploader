package com.example.ca1;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ImageDetailsActivity extends FragmentActivity{

	ImageView image;
	TextView caption;
	TextView location;
	PhotoItem p;
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photo_details);
		image = (ImageView)findViewById(R.id.imageView_PhotoDetails);		
		caption = (TextView)findViewById(R.id.caption_PhotoDetails);
		location = (TextView)findViewById(R.id.location_PhotoDetails);
		image.setAdjustViewBounds(true);
		Bundle extras = getIntent().getExtras();
		 p = (PhotoItem)extras.get("photoItem");
		caption.setText(p.getCaption());
		location.setText(p.getLocation());
		 image.setImageBitmap(PhotoHelper.getRemoteImage("http://137.132.247.137/images/"+p.getFile()));		
	}

	public void showDeleteDialog(){
		android.support.v4.app.FragmentManager fm =getSupportFragmentManager();
		PhotoDeleteDialog photoDeleteDialog = new PhotoDeleteDialog(p);
		photoDeleteDialog.show(fm);
	
	}

	public void showEditDialog(){
		android.support.v4.app.FragmentManager fm =getSupportFragmentManager();
		UpdatePhotoDialog updatePhotoDialog = new UpdatePhotoDialog(p);
		updatePhotoDialog.show(fm);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.photo_edit_menu, menu);
		return true;
	}
	public boolean onOptionsItemSelected(MenuItem item) {
		 Uri uri;
		   Intent i;
		   switch (item.getItemId()) {
		   case R.id.deletePhoto:				 
			   showDeleteDialog();
			      return true;
		   case R.id.EditImage:				 
			   showEditDialog();
			   // i = new Intent(this,ViewAllCustomersActivity.class);
				 // startActivity(i);
				  return true;	
		   default:
			      return super.onOptionsItemSelected(item);
		   }
	}
}
