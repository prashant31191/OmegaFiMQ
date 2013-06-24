package com.appsolution.layouts;

import com.appsolution.omegafi.OmegaFiActivity;
import com.appsolution.omegafi.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;

public class RowEditTextOmegaFi extends RowEditInformation{

	private EditText textEdit;
	private EditText textEdit2;
	private Drawable textOriginal;
	
	public RowEditTextOmegaFi(Context context){
		super(context);
		this.initializeComponets();
	}
	
	public RowEditTextOmegaFi(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.initializeComponets();
		TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.RowEditTextOmegaFi);
		   boolean isEditable = a.getBoolean(R.styleable.RowEditTextOmegaFi_text_editable,true);
		   setEditable(isEditable);
		   
		   String text=a.getString(R.styleable.RowEditTextOmegaFi_text_edit);
		   setTextEdit(text);
		   
		   String text2=a.getString(R.styleable.RowEditTextOmegaFi_text_edit_2);
		   setTextEdit2(text2);
		   
		   int resource=a.getResourceId(R.styleable.RowEditTextOmegaFi_background_input_text, R.drawable.white_input);
		   setBackgroundInputs(resource);
		   
		   int type=a.getInteger(R.styleable.RowEditTextOmegaFi_type_input_row_info_1, 1);
		   RowEditTextOmegaFi.setTypeInputTextFromAttrs(type, textEdit); 
		   
		   int type2=a.getInteger(R.styleable.RowEditTextOmegaFi_type_input_row_info_2, 1);
		   RowEditTextOmegaFi.setTypeInputTextFromAttrs(type, textEdit2);
		   
		   float widthPercentaje=a.getFloat(R.styleable.RowEditTextOmegaFi_width_edit_percentaje, 0.5f);
		   setWidthEditPercentaje(widthPercentaje);
		   
		   String hint=a.getString(R.styleable.RowEditTextOmegaFi_hint_edit_text);
		   textEdit.setHint(hint);
		   
		    a.recycle();
	}
	
	public void initializeComponets(){
		LinearLayout linear=new LinearLayout(super.getContext());
		linear.setLayoutParams(new LayoutParams(android.widget.LinearLayout.LayoutParams.WRAP_CONTENT, 
				android.widget.LinearLayout.LayoutParams.WRAP_CONTENT));
		linear.setOrientation(LinearLayout.VERTICAL);
		linear.setGravity(Gravity.LEFT);
		int widthEdit=OmegaFiActivity.getWidthPercentageDisplay(getContext(), 0.5f);
		textEdit=new EditText(super.getContext());
		textEdit.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
		textOriginal=textEdit.getBackground();
		textEdit.setBackgroundResource(R.drawable.white_input);
		textEdit.setLayoutParams(new LayoutParams(widthEdit,
				LayoutParams.WRAP_CONTENT));
		textEdit2=new EditText(super.getContext());
		textEdit2.setBackgroundResource(R.drawable.white_input);
		textEdit2.setLayoutParams(new LayoutParams(widthEdit,
				LayoutParams.WRAP_CONTENT));
		textEdit2.setVisibility(LinearLayout.GONE);
		linear.addView(textEdit);
		linear.addView(textEdit2);
		this.addViewRight(linear);
		int padding=super.getResources().getDimensionPixelSize(R.dimen.padding_6dp);
		this.setPaddingRow(padding,padding,padding,padding);
	}
	
	public void setEditable(boolean editable){
		if(!editable){
			textEdit.setKeyListener(null);
		}
	}
	
	public void setTextEdit(String text){
		textEdit.setText(text);
	}
	
	public void setTextEdit2(String text){
		if(text!=null){
			textEdit2.setText(text);
			textEdit2.setVisibility(LinearLayout.VISIBLE);
		}
	}
	
	public void setBackgroundInputs(int resource){
		if(resource!=R.drawable.white_input){
			textEdit.setBackgroundDrawable(textOriginal);
			textEdit2.setBackgroundDrawable(textOriginal);
		}
	}
	
	public void setTypeInputEditText(int type){
		textEdit.setInputType(type);
	}
	
	public void setTypeInputEditText2(int type){
		textEdit2.setInputType(type);
	}
	
	public static void setTypeInputTextFromAttrs(int type, EditText edit){
		switch (type) {
		case 2:
			edit.setInputType(InputType.TYPE_CLASS_PHONE);
			break;
		case 3:
			edit.setInputType(InputType.TYPE_CLASS_NUMBER);
			break;
		case 4:
			edit.setInputType(2);
			break;
		case 5:
			edit.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
			break;
		case 6:
			edit.setInputType(129);
			break;
		default:
			break;
		}
	}
	
	@Override
	public void setOnClickListener(OnClickListener l) {
		if(textEdit!=null){
			textEdit.setOnClickListener(l);
			textEdit2.setOnClickListener(l);
		}
		super.setOnClickListener(l);
	}
	
	public void setWidthEditPercentaje(float percentaje){
		int widthEdit=OmegaFiActivity.getWidthPercentageDisplay(getContext(), percentaje);
		textEdit.getLayoutParams().width=widthEdit;
		textEdit2.getLayoutParams().width=widthEdit;
	}
	
}