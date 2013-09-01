package com.example.ca1;

import java.net.URI;
import java.net.URISyntaxException;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("ValidFragment")
public class PhotoDeleteDialog  extends DialogFragment{
	TextView heading;
	Button cancelButton,okButton;
	private PhotoItem photoItem = null;
	public PhotoDeleteDialog(PhotoItem photoItem){	
		this.photoItem = photoItem;
		
	}
	
	public void show(FragmentManager fm) {
		super.show(fm, "photoDelete");
	}
	public void deletePhoto(String id) throws Exception{
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		   qparams.add(new BasicNameValuePair("id", String.valueOf(id)));
		   URI uri;
		
			uri = URIUtils.createURI("http", "137.132.247.137", -1, "/python/delete", 
			      URLEncodedUtils.format(qparams, "UTF-8"), null);		

		   HttpGet httpget = new HttpGet(uri);
		   HttpResponse response = new DefaultHttpClient().execute(httpget);		   
		   Intent i;
		   i = new Intent(getActivity(),MainActivity.class);				  
			  startActivity(i);
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_photo_delete_dialog_fragment, container);
        heading = (TextView)view.findViewById(R.id.deleteDialogHeading);
        cancelButton = (Button)view.findViewById(R.id.delete_photo_cancel);
        okButton = (Button)view.findViewById(R.id.delete_photo_ok);
        getDialog().setTitle("Are you sure you want to delete "+photoItem.getCaption()+" ?");   
        okButton.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View v) {
				//Toast.makeText(getApplicationContext(), "Register Button is pressed", Toast.LENGTH_SHORT).show();
				 //startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
				try{
			deletePhoto(photoItem.getId());
				}catch(Exception e){}
				Toast.makeText(getActivity(), "Image "+photoItem.getCaption()+" is deleted successfully", Toast.LENGTH_SHORT).show();
			startActivity(new Intent(getActivity(),MainActivity.class));
			}
		});
              
        cancelButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//startActivity(new Intent(getActivity(),ImageDetailsActivity.class));
				getDialog().dismiss();
				
			}
			});
        return view;    
	}
	
}
