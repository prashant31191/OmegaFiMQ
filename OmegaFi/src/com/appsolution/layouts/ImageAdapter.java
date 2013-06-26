package com.appsolution.layouts;

import com.appsolution.omegafi.R;
import com.appsolution.omegafi.R.drawable;

import android.R.dimen;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter {

	private Context context;
	private int[] listImages ={ R.drawable.photo_2, R.drawable.photo_3,
		       R.drawable.photo_member, 
		       R.drawable.photo_2, R.drawable.photo_3,
		       R.drawable.photo_member};
	
	
	public ImageAdapter(Context context){
		this.context=context;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listImages.length;
	}
	
	public void changeListImages(){
		if(listImages[0]==R.drawable.photo_3){
			listImages[0]=R.drawable.photo_2;
			listImages[1]=R.drawable.photo_3;
			listImages[3]=R.drawable.photo_member;
		}
		else{
			listImages[0]=R.drawable.photo_3;
			listImages[1]=R.drawable.photo_member;
			listImages[3]=R.drawable.photo_1;
		}
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return listImages[position];
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ImageRoosterName rooster=new ImageRoosterName(context);
		rooster.setImageResource(this.listImages[position]);
		rooster.setNameRooster("Name Rooster");
		return rooster;
	}

}
