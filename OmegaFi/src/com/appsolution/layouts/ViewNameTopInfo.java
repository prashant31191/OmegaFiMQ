package com.appsolution.layouts;

import com.appsolution.omegafi.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ViewNameTopInfo extends LinearLayout {

	private TextView nameTopInfo;
	private LinearLayout contentAll;
	private LinearLayout contentView;
	
	public ViewNameTopInfo(Context context){
		super(context);
		this.initialize();
	}
	
	public ViewNameTopInfo(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.initialize();
		TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.ViewNameTopInfo);
		
		String nameInfo=a.getString(R.styleable.ViewNameTopInfo_name_top_info);
		setNameInfoTop(nameInfo);
		
		a.recycle();
	}
	
	private void initialize(){
		LayoutInflater inflate= (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflate.inflate(R.layout.view_name_top_info, this, true);
		contentAll=(LinearLayout)findViewById(R.id.linearViewNameTopInfo);
		nameTopInfo=(TextView)findViewById(R.id.name_info_top);
		contentView=(LinearLayout)findViewById(R.id.linearContentAddView);
	}
	
	public void setNameInfoTop(String name){
		nameTopInfo.setText(name);
	}
	
	@Override
	public void addView(View child) {
		contentView.addView(child);
	}
	
	public void setWidthFillParent(boolean fill){
		if(fill){
			contentAll.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		}
		else{
			contentAll.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		}
	}
	

}