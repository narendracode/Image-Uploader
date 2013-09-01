package com.example.ca1;

import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

@SuppressLint("ValidFragment")
public class UpdatePhotoDialog extends DialogFragment{
	PhotoItem photoItem= null;
	EditText caption,location;
	ImageView image;
	Button update,cancel;
	public UpdatePhotoDialog(PhotoItem photoItem){
		this.photoItem= photoItem;
	}
	
	public void show(FragmentManager fm) {
		super.show(fm, "photoUpdate");
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_photo_edit_dialog_fragment, container);
	    caption = (EditText)view.findViewById(R.id.editPhoto_caption);
	    location = (EditText)view.findViewById(R.id.editPhoto_location);
	    image = (ImageView)view.findViewById(R.id.editPhoto_imageView);
	    update = (Button)view.findViewById(R.id.editPhoto_update);
	    cancel = (Button)view.findViewById(R.id.editPhoto_cancel);
	    image.setAdjustViewBounds(true);
	    image.setImageBitmap(PhotoHelper.getRemoteImage("http://137.132.247.137/images/"+photoItem.getFile()));
        caption.setText(photoItem.getCaption());
        location.setText(photoItem.getLocation());
        
        update.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View v) {
				//Toast.makeText(getApplicationContext(), "Register Button is pressed", Toast.LENGTH_SHORT).show();
				 //startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
				try{
			update();
				}catch(Exception e){}
				Toast.makeText(getActivity(), "Image "+photoItem.getCaption()+" is updated successfully", Toast.LENGTH_SHORT).show();
			startActivity(new Intent(getActivity(),MainActivity.class));
			}
		});

        
        cancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//startActivity(new Intent(getActivity(),ImageDetailsActivity.class));
				getDialog().dismiss();
				
			}
			});
        return view;
	}
	
	void update() throws Exception {
		   List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		   qparams.add(new BasicNameValuePair("id", photoItem.getId()));
		   qparams.add(new BasicNameValuePair("caption", caption.getText().toString()));
		   qparams.add(new BasicNameValuePair("location", location.getText().toString()));
		   URI uri = URIUtils.createURI("http", "137.132.247.137", -1, "/python/update", 
		      URLEncodedUtils.format(qparams, "UTF-8"), null);
		   HttpGet httpget = new HttpGet(uri);
		   HttpResponse response = new DefaultHttpClient().execute(httpget);
		   InputStream inp = response.getEntity().getContent();
		}

}
