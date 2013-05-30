package com.appsolution.layouts;

import com.appsolution.omegafi.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class RowEditInformation extends RelativeLayout {

	private RelativeLayout content;
	private TextView textNameInfo;
	
	public RowEditInformation(Context context){
		super(context);
		this.initialize();
	}
	
	public RowEditInformation(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.initialize();
		TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.RowEditInformation);
		   boolean isBorderBottom = a.getBoolean(R.styleable.RowEditInformation_put_border_bottom_e,true);
		   if(isBorderBottom){
			   setBorderBottom(isBorderBottom);
			   }
		
		   String nameInfo = a.getString(R.styleable.RowEditInformation_name_information_e);
		   setNameInfo(nameInfo);
		   
		   float textSize = a.getDimension(R.styleable.RowEditInformation_row_text_size_e, 15f);
		   setTextSizeInformation(textSize);
		   
		   int padding=(int)a.getDimension(R.styleable.RowEditInformation_padding_row_edit, content.getPaddingLeft());
		   setPaddingRow(padding, padding, padding, padding);
		   int paddingLeft=(int)a.getDimension(R.styleable.RowEditInformation_padding_left_row_edit, content.getPaddingLeft());
		   int paddingTop=(int)a.getDimension(R.styleable.RowEditInformation_padding_top_row_edit, content.getPaddingTop());
		   int paddingRight=(int)a.getDimension(R.styleable.RowEditInformation_padding_right_row_edit, content.getPaddingRight());
		   int paddingBottom=(int)a.getDimension(R.styleable.RowEditInformation_padding_bottom_row_edit, content.getPaddingBottom());
		   setPaddingRow(paddingLeft, paddingTop, paddingRight, paddingBottom);
		   
		    a.recycle();
	}
	
	private void initialize(){
		LayoutInflater inflate= (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflate.inflate(R.layout.row_edit_information, this, true);
		content=(RelativeLayout)findViewById(R.id.rowEditInformation);
		content.setPadding(content.getPaddingLeft(), content.getPaddingTop(), 10, content.getPaddingBottom());
		textNameInfo=(TextView)findViewById(R.id.nameInfoEdit);
	}
	
	public void setNameInfo(String name) {
		textNameInfo.setText(name);
	}
	
	
	public void setTextSizeInformation(float size){
		textNameInfo.setTextSize(size);
	}
	
	
	public void setPaddingRow(int left, int top, int right, int bottom){
		content.setPadding(left, top, right, bottom);
	}
	
	public void setBorderBottom(boolean border){
		if(border){
//			   content.setBackgroundResource(R.drawable.border_bottom);
		}
		else{
			content.setBackgroundColor(Color.TRANSPARENT);
		}
	}
	
	public void addViewRight(View view){
		LayoutParams params = (RelativeLayout.LayoutParams)view.getLayoutParams();
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		view.setLayoutParams(params);	
		content.addView(view);
	}


}