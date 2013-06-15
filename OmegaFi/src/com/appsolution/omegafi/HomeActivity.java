package com.appsolution.omegafi;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.appsolution.layouts.DetailsOfficer;
import com.appsolution.layouts.PollOmegaFiContent;
import com.appsolution.layouts.RowAnnouncement;
import com.appsolution.layouts.RowInformation;
import com.appsolution.layouts.SectionOmegaFi;
import com.appsolution.logic.Server;
import com.viewpagerindicator.CirclePageIndicator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.opengl.Visibility;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Gallery;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class HomeActivity extends OmegaFiActivity {
	
	private SectionOmegaFi sectionAccountUser;
	
	private SectionOmegaFi sectionChapterDirectory;
	private DetailsOfficer detailsOffice;
	
	private Gallery listPhotos;
	private SectionOmegaFi sectionEvents;
	private ViewPager paginator;
	private PagerAdapter adapterPager;
	
	private SectionOmegaFi sectionPoll;
	
	private PollOmegaFiContent contentPoll;
	private SectionOmegaFi sectionNews;
	
	private Bundle bundleHome;
	
	private JSONObject jsonAccounts;
	private JSONObject jsonChapters;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		sectionChapterDirectory=(SectionOmegaFi)findViewById(R.id.sectionChapterDirectory);
		detailsOffice=new DetailsOfficer(this);
		detailsOffice.setVisibility(LinearLayout.GONE);
		detailsOffice.setClickViewListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent memberDetail=new Intent(getApplication(), OfficerMemberDetailActivity.class);
				startActivity(memberDetail);
			}
		});
		
		this.completeChapterDirectory();
		
		sectionEvents=(SectionOmegaFi)findViewById(R.id.sectionEvents);
		sectionEvents.setOnClickTitleListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
					Intent calendarActivity=new Intent(getApplication(), CalendarActivity.class);
					startActivity(calendarActivity);
			}
		});
		this.completeEvents();
		
		sectionPoll=(SectionOmegaFi)findViewById(R.id.sectionPoll);
		this.completePollSection();
		
		sectionNews=(SectionOmegaFi)findViewById(R.id.sectionNews);
		sectionNews.setOnClickTitleListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
					Intent newsActivity=new Intent(getApplication(), NewsOmegaFiActivity.class);
					startActivity(newsActivity);
			}
		});
		
		this.completeNewsSection();
		this.getJSONsServicesHome();
	}
	
	private void getJSONsServicesHome(){
		/*bundleHome=getIntent().getExtras();
		String profile=bundleHome.getString("profile");
		String accounts=bundleHome.getString("accounts");
		String chapters="";
		try {
			jsonAccounts=new JSONObject(accounts);
			if(chapters!=""){
				jsonChapters=new JSONObject(chapters);
			}
			JSONArray jsonArray=jsonAccounts.getJSONArray("accounts");
			Log.d("Recibido el json array", jsonArray.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	
	private void completeChapterDirectory(){
		SectionOmegaFi sectionOfficers=new SectionOmegaFi(this);
		sectionOfficers.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		sectionOfficers.setTitleSection("Officers");
		sectionOfficers.setSizeTitle(14f);
		sectionOfficers.setShowArrow(false);
		sectionOfficers.setPutBorderBottom(true);
		listPhotos=new Gallery(this);
		listPhotos.setPadding(10,10, 10, 10);
		listPhotos.setLayoutParams(new LayoutParams(android.widget.Gallery.LayoutParams.MATCH_PARENT,
				android.widget.Gallery.LayoutParams.WRAP_CONTENT));
		listPhotos.setAdapter(new ImageAdapter(this));
		listPhotos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				detailsOffice.setVisibility(LinearLayout.VISIBLE);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		LinearLayout linearSection=(LinearLayout)sectionOfficers.findViewById(R.id.contentSectionOmegaFi);
		linearSection.setBackgroundResource(R.drawable.border_bottom);
		linearSection.setPadding(12, 0, 0, 10);
		linearSection.addView(listPhotos);
		
		RowInformation rowChapter=new RowInformation(this);
		rowChapter.setNameInfo("Alpha Delta Pi - Alpha Delta");
		rowChapter.setNameSubInfo("Miami University");
		rowChapter.setColorFontRowInformation(Color.BLACK);
		rowChapter.setBorderBottom(true);
		rowChapter.setPaddingRow(10, rowChapter.getPaddingTop(),rowChapter.getPaddingRight(), rowChapter.getPaddingBottom());
		
		sectionChapterDirectory.addView(rowChapter);
		sectionChapterDirectory.addView(sectionOfficers);
		sectionChapterDirectory.addView(detailsOffice);
	}
	
	private void completeEvents(){
		sectionEvents.setPaddingAll(0, 0, 0, this.getResources().getDimensionPixelSize(R.dimen.padding_5dp));
		paginator=new ViewPager(getApplicationContext());
		paginator.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 
				this.getResources().getDimensionPixelSize(R.dimen.height_new_event_content)));
		adapterPager=new EventsNewsAdapter(getApplicationContext());
		paginator.setAdapter(adapterPager);
		
		CirclePageIndicator titlesIndicator=new CirclePageIndicator(this);
		titlesIndicator.setFillColor(this.getResources().getColor(R.color.red_wine));
		int circleSize=this.getResources().getDimensionPixelSize(R.dimen.size_circle_newevents);
		titlesIndicator.setRadius(circleSize);
		titlesIndicator.setLayoutParams(new LayoutParams(android.widget.LinearLayout.LayoutParams.MATCH_PARENT, 
				android.widget.LinearLayout.LayoutParams.WRAP_CONTENT));
		titlesIndicator.setViewPager(paginator);
		sectionEvents.addView(paginator);
		sectionEvents.addView(titlesIndicator);
	}
	
	private void completePollSection(){
		LinearLayout content=(LinearLayout)sectionPoll.findViewById(R.id.contentSectionOmegaFi);
		content.setPadding(8,8, 8, 8);
		contentPoll=new PollOmegaFiContent(this);
		contentPoll.setTitleQuestion("Lorem ipsum dolor sit amet, consectetur adipisicing?");
		ArrayList<String> aux=new ArrayList<String>();
		for (int i = 0; i < 4; i++) {
			aux.add("Lorem ipsum dolor sit amet, consectetur adipisicing");
		}
		contentPoll.addAnswersToPoll(aux);
		content.addView(contentPoll);
	}
	
	private void completeNewsSection(){
		sectionNews.setPaddingAll(0, 0, 0, this.getResources().getDimensionPixelSize(R.dimen.padding_5dp));
		paginator=new ViewPager(getApplicationContext());
		paginator.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, this.getResources().getDimensionPixelSize
				(R.dimen.height_new_event_content)));
		adapterPager=new EventsNewsAdapter(getApplicationContext());
		paginator.setAdapter(adapterPager);
		
		CirclePageIndicator titlesIndicator=new CirclePageIndicator(this);
		titlesIndicator.setFillColor(this.getResources().getColor(R.color.red_wine));
		int circleSize=this.getResources().getDimensionPixelSize(R.dimen.size_circle_newevents);
		titlesIndicator.setRadius(circleSize);
		titlesIndicator.setLayoutParams(new LayoutParams(android.widget.LinearLayout.LayoutParams.MATCH_PARENT, 
				android.widget.LinearLayout.LayoutParams.WRAP_CONTENT));
		titlesIndicator.setViewPager(paginator);
		sectionNews.addView(paginator);
		sectionNews.addView(titlesIndicator);
	}
	
	@Override
	public void onBackPressed() {
		this.showConfirmateExit();
	}
	
	public void activityTermsUse(View button){
		Intent activityTerms=new Intent(this, TermsActivity.class);
		startActivity(activityTerms);
	}
	
	public void activityPrivatePolicy(View button){
		Intent activityPrivacy=new Intent(this, PrivacyActivity.class);
		startActivity(activityPrivacy);
	}

	
}
