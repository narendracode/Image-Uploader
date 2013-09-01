package com.example.ca1;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class MyMapFragment extends MapFragment{
	LatLng latLng = null;
	String caption = null;
	android.app.FragmentManager fm = null;
	public LatLng getLatLng() {
		return latLng;
	}

	public void setLatLng(LatLng latLng, android.app.FragmentManager fm) {
		this.fm = fm;
		this.latLng = latLng;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public MyMapFragment(){}
	
	
	/*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_maps, container);
        MapView mapView = (MapView)view.findViewById(R.id.map);
        return view;
        }
	public void plot(){
		  Log.w("TEST MAP:","4#############");
		 // android.app.FragmentManager fm = getFragmentManager();
		  Log.w("TEST MAP:","4111111#############:::"+fm);
		  MapFragment mF = ((MapFragment)fm.findFragmentById(R.id.map));
		  Log.w("TEST MAP:","5#############::"+mF);
		  
		   GoogleMap map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		   Log.w("TEST MAP:","6#############");
   		Marker sgm = map.addMarker(new MarkerOptions()
   		                          .position(latLng)
   		                          .title(caption)
   		                          .snippet("Image Browser Project By Narendra")
   		                          .icon(BitmapDescriptorFactory.fromResource(R.drawable.arrow)));
   	  Log.w("TEST MAP:","6#############");
   		map.setMyLocationEnabled(true);
   		
	}*/
	
}
