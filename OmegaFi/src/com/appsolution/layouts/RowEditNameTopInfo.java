package com.appsolution.layouts;

import com.appsolution.omegafi.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.EditText;

public class RowEditNameTopInfo extends ViewNameTopInfo {

	private EditText textInfo;
	
	public RowEditNameTopInfo(Context context){
		super(context);
		this.initialize();
	}
	
	public RowEditNameTopInfo(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.initialize();
		TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.RowEditNameTopInfo);
		
		String hint=a.getString(R.styleable.RowEditNameTopInfo_hint_text_top_info);
		setHintText(hint);
		
		a.recycle();
	}
	
	private void initialize(){
		textInfo=new EditText(super.getContext());
		textInfo.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		textInfo.setBackgroundResource(R.drawable.white_input);
		this.addView(textInfo);
	}
	
	public void setHintText(String text){
		textInfo.setHint(text);
	}

}
