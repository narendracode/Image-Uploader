package com.example.ca1;
import java.util.ArrayList;
import java.util.List;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.app.ListActivity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
public class MainActivity extends ListActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 StrictMode.setThreadPolicy(ThreadPolicy.LAX);
		 List<PhotoItem> photos = PhotoHelper.PhotoRead();
		/* ArrayList<PhotoItem> photos= null;

		 Bundle extras = getIntent().getExtras();
		 PhotoStore photoStore = (PhotoStore)extras.get("photos");

		 photos = photoStore.getPhotos();
*/
		 setListAdapter(new ImageAndTextListAdapter(this, photos));		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public boolean onOptionsItemSelected(MenuItem item) {
		 Uri uri;
		   Intent i;
		   switch (item.getItemId()) {
		   case R.id.captureImage:				 
				  i = new Intent(this,ImageCaptureActivity.class);				  
				  startActivity(i);
			      return true;
		   case R.id.viewMap:				 
				 i = new Intent(this,ImageCaptureActivity.class);
				 startActivity(i);
				  return true;	
		   default:
			      return super.onOptionsItemSelected(item);
		   }
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		PhotoItem item = (PhotoItem) getListAdapter().getItem(position);
        //Toast.makeText(this, "ID:"+item.getId(), Toast.LENGTH_LONG).show();
		Intent i = new Intent(this,ImageDetailsActivity.class);
		i.putExtra("photoItem", item);
		Log.w("start before activity check", item.getCaption());
		startActivity(i);
	}//onListItemClick()
	

}
