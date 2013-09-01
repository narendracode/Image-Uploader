package com.example.ca1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PhotoStore implements Serializable{

	private static final long serialVersionUID = 1L;
	private static ArrayList<PhotoItem> photos= null;
	public  ArrayList<PhotoItem> getPhotos(){
		return photos;
	}
	public static void setPhotos(ArrayList<PhotoItem> p){
		photos = p;
	}
}
