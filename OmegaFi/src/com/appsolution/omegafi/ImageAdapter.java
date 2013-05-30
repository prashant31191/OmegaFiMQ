package com.appsolution.omegafi;

import android.content.Context;
import android.graphics.Color;
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
	private int[] listImages ={ R.drawable.hombre, R.drawable.mujer,
		       R.drawable.hombre1, R.drawable.mujer1,
		       R.drawable.hombre2, R.drawable.mujer2,
		       R.drawable.hombre3, R.drawable.mujer3 };
	
	public ImageAdapter(Context context){
		this.context=context;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listImages.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// Get a View to display image data
					LinearLayout linear=new LinearLayout(context);
					linear.setGravity(Gravity.CENTER_HORIZONTAL);
					linear.setOrientation(LinearLayout.VERTICAL);
					ImageView iv = new ImageView(this.context);
					iv.setImageResource(this.listImages[position]);
					// Image should be scaled somehow
					//iv.setScaleType(ImageView.ScaleType.CENTER);
					//iv.setScaleType(ImageView.ScaleType.CENTER_CROP);			
					//iv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
					//iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
					//iv.setScaleType(ImageView.ScaleType.FIT_XY);
					iv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
					// Set the Width & Height of the individual images
					iv.setLayoutParams(new Gallery.LayoutParams(200, 150));
					
					TextView textView=new TextView(context);
					android.widget.LinearLayout.LayoutParams params=new android.widget.LinearLayout.LayoutParams
							(android.widget.LinearLayout.LayoutParams.MATCH_PARENT,android.widget.LinearLayout.LayoutParams.WRAP_CONTENT);
					params.gravity=Gravity.CENTER_HORIZONTAL;
					textView.setGravity(Gravity.CENTER_HORIZONTAL);
					textView.setText("Title name");
					
					linear.addView(iv);
					linear.addView(textView);
					return linear;
	}

}