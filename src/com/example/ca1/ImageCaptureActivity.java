package com.example.ca1;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.maps.MyLocationOverlay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ImageCaptureActivity extends Activity{
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	Uri imageUri;
	Uri img;
	TextView txt;
	EditText locationEditText,captionEditText;
	ImageView image;
	String caption,location,path;
	Location loc;
	Button uploadButton;
	private static int RESULT_LOAD_IMAGE = 1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_capture);
		locationEditText = (EditText)findViewById(R.id.location);
		
		captionEditText = (EditText)findViewById(R.id.caption);
		image = (ImageView)findViewById(R.id.imageView1);
		image.setAdjustViewBounds(true);
		myGeo();
		//image.setMaxHeight(50);
		//Log.w("GEO check@@@@@@@@@@@@@:","8.."+MyLocation);
		locationEditText.setText(MyLocation);
		Log.w("GEO check:","9");
		uploadButton = (Button)findViewById(R.id.uploadButton);
		uploadButton.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View v) {
				caption = captionEditText.getText().toString();
        		location = locationEditText.getText().toString();
              Log.w("------", "imageURI"+imageUri);
               Transfer.uploadFile(caption,location,getRealPathFromURI(imageUri));
               
               //maps
               Log.w("TEST MAP:","A#############");
               plot();
               // LatLng myLatLng = new LatLng(loc.getLatitude(),loc.getLongitude());
               // Log.w("TEST MAP:","1#############");
               //MyMapFragment myMapFragment=  new MyMapFragment();
               //myMapFragment.setCaption(caption);
              // android.app.FragmentManager fm = getFragmentManager();
              // myMapFragment.setLatLng(myLatLng,fm);
               //Log.w("TEST MAP:","2#############");
              // myMapFragment.plot();
               //Log.w("TEST MAP:","3#############");
               Toast.makeText(getApplicationContext(), "Image uploaded successfully", Toast.LENGTH_SHORT).show();
       	    Intent i;
       		   i = new Intent(getApplicationContext(),MainActivity.class);				  
       			  startActivity(i);
			}
		});	
	}
	public void capturePhoto(View view) {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		imageUri = Uri.fromFile(new File("/sdcard/", "IMG_" +        
                String.valueOf(System.currentTimeMillis()) + ".jpg"));
		intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, imageUri);
	    startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
	}
	
	public void selectPhotoFromGallery(View view){
		Intent i = new Intent(
				Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);				
				startActivityForResult(i, RESULT_LOAD_IMAGE);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
	        if (resultCode == RESULT_OK) {
	        	boolean useIntent = false;
	        	 img = (data == null ? imageUri : data.getData()); // cope with Nexus
	        	if (useIntent) {
	               Intent intent = new Intent();
	               intent.setAction(android.content.Intent.ACTION_VIEW);
	               intent.setDataAndType(img, "image/*");
	               startActivity(intent);
	        	} else {
	        		image.setImageURI(imageUri);
	        	}
	        } else if (resultCode == RESULT_CANCELED) {
	        	txt.append("Photo cancelled\n");
	        } else {
	            // Image capture failed, advise user
	        }
	    } 
	    else if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data){
	    	img = data.getData();
	    	String[] filePathColumn = { MediaStore.Images.Media.DATA };
	    	Cursor cursor = getContentResolver().query(img,filePathColumn, null, null, null);    	
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			String picturePath = cursor.getString(columnIndex);
			cursor.close();
			imageUri=Uri.parse(new File(picturePath).toString());
			image.setImageURI(Uri.parse(new File(picturePath).toString()));		
	    }
}//onActivityResult
	
	private String getRealPathFromURI(Uri uri)
	{
		String filePath;
        if (uri != null && "content".equals(uri.getScheme())) {
            Cursor cursor = this.getContentResolver().
              query(uri, new String[] { android.provider.MediaStore.Images.ImageColumns.DATA }, null, null, null);
            cursor.moveToFirst();   
            filePath = cursor.getString(0);
            cursor.close();
        }
        else {
            filePath = uri.getPath();
        }
        return(filePath);
    }//getRealPathFromURI
	
	String MyLocation;
	public String myGeo() {	
		LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		 loc = lm.getLastKnownLocation("network");
	    Geocoder gc = new Geocoder(this, Locale.getDefault());
	    List<Address> addresses = null;
	    try {
	      addresses = gc.getFromLocation(loc.getLatitude(),
	                                     loc.getLongitude(), 1);
	      MyLocation=addresses.get(0).getAddressLine(0);
	    } catch (IOException e) {
	    	Log.w("GEO check:","7@@@@@@@@@@@@@@"+e);
	    }
	    return addresses.get(0).toString();
	}
	
	
	void plot(){
		GoogleMap map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
	      LatLng latLng = new LatLng(loc.getLatitude(),loc.getLongitude());
	      Marker sgm = map.addMarker(new MarkerOptions()
           .position(latLng)
           .title(caption)
           .snippet("Image Browser Project By Narendra")
           .icon(BitmapDescriptorFactory.fromResource(R.drawable.arrow)));
	      	map.setMyLocationEnabled(true);
	}
}
