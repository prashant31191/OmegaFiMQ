package com.appsolution.layouts;

import com.appsolution.omegafi.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class RowEditTextSubmit extends LinearLayout {
	
	private EditText textEdit;
	private Button buttonSubmit;
	private WindowManager wm;
	private Display display;

	public RowEditTextSubmit(Context context){
		super(context);
		this.initialize();
	}
	
	public RowEditTextSubmit(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.initialize();
		
		TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.RowEditTextSubmit);
		
		String placeholder=a.getString(R.styleable.RowEditTextSubmit_text_placehoder);
		setPlaceHolderEdit(placeholder);
		
		String textButton= a.getString(R.styleable.RowEditTextSubmit_text_submit_button);
		setTextButton(textButton);
		
		int input=a.getInteger(R.styleable.RowEditTextSubmit_input_type_submit, 1);
		RowEditTextOmegaFi.setTypeInputTextFromAttrs(input, textEdit);
		
		a.recycle();	
	}
	
	private void initialize() {
		wm=(WindowManager)super.getContext().getSystemService(Context.WINDOW_SERVICE);
		display=wm.getDefaultDisplay();
		
		LayoutInflater inflate= (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflate.inflate(R.layout.row_text_edit_submit, this, true);
		textEdit=(EditText)findViewById(R.id.editText_submit);
		buttonSubmit=(Button)findViewById(R.id.buttonSubmitEdit);
	}
	
	public void setPlaceHolderEdit(String text){
		textEdit.setHint(text);
	}
	
	public void setTextButton(String text){
		buttonSubmit.setText(text);
	}
	
	public void onSubmit(OnClickListener listener){
		buttonSubmit.setOnClickListener(listener);
	}
	
	public void setInputTypeEditSubmit(int type){
		textEdit.setInputType(type);
	}

}