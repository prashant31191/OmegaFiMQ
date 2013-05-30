package com.appsolution.omegafi;

import com.appsolution.layouts.RowInformation;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.LinearLayout;

public class HistoryActivity extends OmegaFiActivity {

	private LinearLayout linearContent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history);
		linearContent=(LinearLayout)findViewById(R.id.contentLinearHistory);
		this.completeHistory();
	}
	
	@Override
	protected void optionsActionBar() {
		actionBar.setTitle("History");
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setDisplayShowHomeEnabled(false);
	}
	
	private void completeHistory(){
		for (int i = 0; i < 20; i++) {
			RowInformation row=new RowInformation(this);
			row.setNameInfo("Transaction Description");
			row.setNameSubInfo("MM/DD/YYYY");
			row.setValueInfo("$135.00");
			linearContent.addView(row);
		}
		
	}

}