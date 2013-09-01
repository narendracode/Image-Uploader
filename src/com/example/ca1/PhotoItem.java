package com.example.ca1;

import java.io.Serializable;
import java.util.HashMap;

public class PhotoItem  implements Serializable{
	String caption; String location; String file; String id;
	public PhotoItem(String caption, String location, String file, String id) {
		super();
		this.caption = caption;
		this.location = location;
		this.file = file;
		this.id = id;
	}
	private static final long serialVersionUID = 5976828556440440492L;
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	

}
