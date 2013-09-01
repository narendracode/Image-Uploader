package com.example.ca1;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.util.Log;
public class SplashScreen extends Activity {
	 
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
 
        
        //Actual code
       // new ImageLoader().execute();
        
        //To Delay by 3 Seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);
 
                // close this activity
                finish();
            	
            	
            }
        }, SPLASH_TIME_OUT);
        
        
       
        
    }//onCreate()
    
   /* private class ImageLoader extends AsyncTask<Void, Void, Void> {
 
    	public ImageLoader(){
    		 photoStore= new PhotoStore();
    	}
    	PhotoStore photoStore= null;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // before making http calls  
           
 
        }
        
        @Override
        protected Void doInBackground(Void... arg0) {
        	 StrictMode.setThreadPolicy(ThreadPolicy.LAX);
        	photoStore.setPhotos(PhotoHelper.PhotoRead());
        	Log.w("@@@@",photoStore.getPhotos().size()+"......");
			return null;
        	
        }
        
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);  
            Intent i = new Intent(SplashScreen.this, MainActivity.class);
            i.putExtra("photos", photoStore);
            Log.w("######",photoStore.getPhotos().size()+"......");
            startActivity(i);
            finish();
        }
    }*/
 
}
