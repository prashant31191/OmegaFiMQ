package com.appsolution.omegafi;

import com.appsolution.layouts.DialogInformationOF;
import com.appsolution.services.Server;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashOmegaFiActivity extends OmegaFiLoginActivity {

	private Handler handlerProgress;
	private ChargerSplash barSplash;
	private TextView textUserName;
	private TextView textWelcomeOmega;
	private TextView textPercentaje;
	private TextView textLoading;
	private ImageView imageContact;
	private Handler handlerPhoto;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_omega_fi);
		Typeface bold=Typeface.createFromAsset(getAssets(), "fonts/Roboto-Bold.ttf");
		imageContact=(ImageView)findViewById(R.id.photoProfileSplash);
		textUserName=(TextView)findViewById(R.id.textHelloUser);
		textUserName.setTypeface(bold);
		
		textWelcomeOmega=(TextView)findViewById(R.id.textWelcomeUser);
		textWelcomeOmega.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf"));
		textPercentaje=(TextView)findViewById(R.id.percentajeLoading);
		textPercentaje.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Roboto-Condensed.ttf"));
		handlerProgress=new Handler(){
			@Override
			public void handleMessage(android.os.Message msg) {
				textPercentaje.setText(msg.obj+"%");
			};
		};
		
		handlerPhoto=new Handler(){
			@Override
			public void handleMessage(android.os.Message msg) {
				Bitmap image=(Bitmap)msg.obj;
				if(image!=null)
					imageContact.setImageBitmap(image);
				else
					imageContact.setImageResource(R.drawable.photo_member);
			};
		};
		textLoading=(TextView)findViewById(R.id.labelLoadingSplash);
		textLoading.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf"));
		
		textUserName.setText("Hello, "+Server.getServer().getForgotLogin().getFirstName(SplashOmegaFiActivity.this));
		barSplash=new ChargerSplash();
		barSplash.execute();
	}
	
	
	private class ChargerSplash extends AsyncTask<Void, Integer, Boolean>{
		
		int status=0;
		
		@Override
		protected Boolean doInBackground(Void... params) {
			status=(Integer)Server.getServer().getHome().getProfile().chargeProfileData()[0];
			if(Server.isStatusOk(status)){
				Message msg=new Message();
				msg.obj=10;
				handlerProgress.sendMessage(msg);
				Message msgPhoto=new Message();
				msgPhoto.obj=Server.getServer().chargeBitmapInImageView(Server.getServer().getHome().getProfile().getProfile().getSource(), 
						Server.getServer().getHome().getProfile().getProfile().getUrlPhoto());
				handlerPhoto.sendMessage(msgPhoto);
				msg=new Message();
				msg.obj=20;
				handlerProgress.sendMessage(msg);
				Server.getServer().getHome().getAccounts().chargeAccounts();
				msg=new Message();
				msg.obj=50;
				handlerProgress.sendMessage(msg);
				Server.getServer().getHome().getChapters().chargeChapters();
				Log.d("first chapter", Server.getServer().getHome().getChapters().getIdChapter(0)+"");
				Server.getServer().getHome().getOfficers().chargeOfficers(
						Server.getServer().getHome().getChapters().getIdChapter(0));			
				msg=new Message();
				msg.obj=50;
				handlerProgress.sendMessage(msg);
				Server.getServer().getHome().getCalendar().chargeEventsHome();
				msg=new Message();
				msg.obj=60;
				handlerProgress.sendMessage(msg);
				Log.d("url", Server.getServer().getForgotLogin().getUrlFeed(SplashOmegaFiActivity.this));
				Server.getServer().getHome().getFeeds().chargeNewsFeed
				(Server.getServer().getForgotLogin().getUrlFeed(SplashOmegaFiActivity.this));
				msg=new Message();
				msg.obj=60;
				handlerProgress.sendMessage(msg);
				Server.getServer().getHome().getPolls().chargePolls();
				msg=new Message();
				msg.obj=75;
				handlerProgress.sendMessage(msg);
				Server.getServer().setEmptyInformation(false);
				msg=new Message();
				msg.obj=99;
				handlerProgress.sendMessage(msg);
			}
//			for (int i = 0; i < 100; i+=5) {
//				try {
//					Thread.sleep(150);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				Message msg=new Message();
//				msg.obj=i;
//				handlerProgress.sendMessage(msg);
//			}
			return true;
		}
		
		@Override
		protected void onPreExecute() {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		@Override
		protected void onPostExecute(Boolean result) {
//			Intent homeActivity=new Intent(getApplication(), HomeActivity.class);
//			finish();
//			startActivity(homeActivity);
			if(status!=200){
				
				final DialogInformationOF of=new DialogInformationOF(SplashOmegaFiActivity.this);
				of.setMessageDialog("Web service is temporarily unavailable");
				of.setButtonListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						of.dismissDialog();
						finish();
						backToLogin();
					}
					
				});
				of.showDialog();
			}
			else{
				Intent homeActivity=new Intent(getApplicationContext(), HomeActivity.class);
				startActivityForResult(homeActivity,OmegaFiActivity.ACTIVITY_HOME);
				finish();
			}
		}
	}
	
	private void backToLogin(){
		OmegaFiActivity.closeAllActivities(this);
		Intent backToLogin=new Intent(getApplicationContext(), MainActivity.class);
		backToLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		backToLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
		startActivity(backToLogin);
		finish();
	}
	
	
}