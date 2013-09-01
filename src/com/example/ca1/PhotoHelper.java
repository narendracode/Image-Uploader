package com.example.ca1;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class PhotoHelper {
	public static ArrayList<PhotoItem> PhotoRead() {
		ArrayList<PhotoItem> list = new ArrayList<PhotoItem>();
	    JSONArray a = JSONParser.getJSONArrayFromUrl("http://137.132.247.137/python/list?json");
	    try {
	        for (int i =0; i<a.length(); i++) {
			    JSONObject b = a.getJSONObject(i);
			    list.add(new PhotoItem(b.getString("caption"), 
			    		b.getString("location"), b.getString("file"), b.getString("id")));
	        }
		} catch (Exception e) {
	        Log.e("PhotoItem", "Array error");
		}
	    PhotoItem[] result = new PhotoItem[list.size()];
	    
	    return(list);
	}
	
	public static Bitmap getRemoteImage(final String url) {
	    try {
	        final URLConnection conn = new URL(url).openConnection();
	        conn.connect();
	        final BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
	        final Bitmap bm = BitmapFactory.decodeStream(bis);
	        bis.close();
	        return bm;
	    } catch (IOException e) {}
	    return null;
	}
}
