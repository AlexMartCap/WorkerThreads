package com.iesebre.dam2.pa201415.alex.exemplethreads;

import java.io.InputStream;
import java.net.URL;
import android.support.v7.app.ActionBarActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends ActionBarActivity implements OnClickListener {
	
	private ImageView mImageView;	//Image view control
	
	private Bitmap b ;	//Bitmap
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Get controls
		mImageView = (ImageView)findViewById(R.id.mImageView);
		Button btnGet = (Button) findViewById(R.id.btnGetImage);
		//button listener
        btnGet.setOnClickListener(this);
	}
	
	//Click method
	public void onClick(View v) {
		new Thread(new Runnable() {
			public void run() {
				b = loadImageFromNetwork("http://www.naturcenter.es/Imatges/Animals/Anfibi%20imatge.jpg");
				setImage(b);
			}
		}).start();
		
	}
	
	//Load image method:
	private Bitmap loadImageFromNetwork(String url){
		Bitmap bitmap = null;	
		try {
			bitmap = BitmapFactory.decodeStream((InputStream)new URL(url).getContent());
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bitmap;
	}
	
	//Set Image to ImageView method
	public void setImage(Bitmap b){
		//set control with the image
		final Bitmap h = b;
		mImageView.post(new Runnable() {
			public void run() {
				mImageView.setImageBitmap(h);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

