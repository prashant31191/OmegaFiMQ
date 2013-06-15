package com.appsolution.layouts;

import com.appsolution.omegafi.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

public class AccountLayout extends LinearLayout {

	public AccountLayout(Context context){
		super(context);
		this.initialize();
	}
	
	public AccountLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.initialize();
	}
	
	public void initialize() {
		LayoutInflater inflate= (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflate.inflate(R.layout.account_layout, this, true);
	}

}
