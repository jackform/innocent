package com.innocent.ui;

import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;	
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.view.View.OnClickListener;

import com.edmodo.cropper.CropImageView;
import com.innocent.R;

public class RegisterActivity extends  Activity {
//	private static final String TAG = "RegisterActivity";
	private static final int CHOOSE_PICTURE = 1;
	private Button mAlbumChoose,mCaremaChoose;
	private	ImageView mHeaderImage;
	private CropImageView cropImg;
	private Bitmap cropedBitmap = null;
	private View vPopWindow = null;
	private PopupWindow popWindow = null;
	private Handler mHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch(msg.what){
			case 0x1:
				if(cropImg != null && cropedBitmap != null) {
					 mHeaderImage.setImageBitmap(cropedBitmap);
					 popWindow.dismiss();
				}
				break;
			case 0x2:
				break;
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		mHeaderImage = (ImageView)findViewById(R.id.head_image);
		mAlbumChoose = (Button)findViewById(R.id.album_choose);
		mCaremaChoose = (Button)findViewById(R.id.camera_choose);
		
		mAlbumChoose.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {				
//				cropImageFromAlbum();
				showCropImagePopWindow(view);
			}
		});
		mCaremaChoose.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				
			}
		});
		
		
	}
	
	
	protected void showCropImagePopWindow(View parent) {
		 LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);     
		 vPopWindow=inflater.inflate(R.layout.layout_crop_image, null, false);  
		 popWindow = new PopupWindow(vPopWindow,LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,true);
		 ColorDrawable color = new ColorDrawable(Color.TRANSPARENT);
	     popWindow.setBackgroundDrawable(color);
	     popWindow.setFocusable(true); // 设置PopupWindow可获得焦点
	     popWindow.setTouchable(true); // //设置PopupWindow可触摸

	     cropImg = (CropImageView) vPopWindow.findViewById(R.id.imgView);
		 cropImg.setAspectRatio(30,40);
		 cropImg.setFixedAspectRatio(true);
		 
		 Button select = (Button)vPopWindow.findViewById(R.id.select);
		 Button crop   = (Button)vPopWindow.findViewById(R.id.crop);
		 Button cancel = (Button)vPopWindow.findViewById(R.id.cancel);
		 
		 select.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent("android.intent.action.PICK");
				intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(intent,CHOOSE_PICTURE);
			}
		 });
		 crop.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				cropedBitmap = cropImg.getCroppedImage();
				mHandler.sendEmptyMessage(0x1);
			}
		 });
		 cancel.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View view) {
				popWindow.dismiss();
			}
		 });
//	     popWindow.showAsDropDown(parent,-25,10); 
		 popWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);
	}
	/*
	private void cropImageFromAlbum()
	{
		Intent intent = new Intent("android.intent.action.PICK");
		intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		intent.putExtra( "crop", "true");
		intent.putExtra( "aspectX", 300);
		intent.putExtra( "aspectY", 400);
		intent.putExtra( "outputX", 300);
		intent.putExtra( "outputY", 400);
	    intent.putExtra( "scale", true);
	    intent.putExtra( "return-data", true);
		intent.putExtra("output",Bitmap.CompressFormat.JPEG.toString());
		intent.putExtra("noFaceDetection",true);
		startActivityForResult(intent,CHOOSE_PICTURE);
	}
	*/
	
	protected void onActivityResult(int requestCode,int resultCode,Intent data)
	{
		super.onActivityResult(requestCode,requestCode,data);
		if( RESULT_OK != resultCode )
			return ;
		switch(requestCode) {
		case CHOOSE_PICTURE:
			if ( data != null ) {
				
				Uri originalUri = data.getData(); 
//				Log.v(TAG,originalUri.toString());
//				Log.v(TAG,originalUri.getPath());
				ContentResolver resolver = getContentResolver();
				try {
					Bitmap photo = MediaStore.Images.Media.getBitmap(resolver, originalUri);
					if (photo != null) {
						Bitmap newbmp = scaleBitmap(photo);
                        photo.recycle();                                                                                                                                                                                                                                                                                                                                                                                                                                                    
                        cropImg.setImageBitmap(newbmp);
                    }
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}	
			break;
		default:
			break;
		}
	}
	/*
	 * 缩放原图，使其宽至屏幕的三分之二
	 * 缩放比的公式如下：
	 * because:
	 * 		picDisplayWidth = 2/3 screenWidth
	 * 		scale = picDisplayWidth / picOriginalWidth
	 * so:
	 * 		scale = 2/3 * screenWidth / picOriginalWith
	 * 
	 */
	
	private Bitmap scaleBitmap(Bitmap photo) {
		int picOriginalWidth = photo.getWidth();
	    int picOriginalHeight = photo.getHeight();
//	    int screenWidth= getWindowManager().getDefaultDisplay().getWidth();
//	    int screenHeight=getWindowManager().getDefaultDisplay().getHeight();
	    DisplayMetrics dm = new DisplayMetrics();
	    dm = getResources().getDisplayMetrics();
	    int screenWidth  = dm.widthPixels;
	    int screenHeight = dm.heightPixels;
	    
        Matrix matrix = new Matrix();
        float scaleWidth = 2.0f/3 * screenWidth / picOriginalWidth;
        float scaleHeight = 2.0f/3 * screenHeight / picOriginalHeight;
        matrix.postScale(scaleWidth, scaleHeight);// 利用矩阵进行缩放不会造成内存溢出
        Bitmap newbmp = Bitmap.createBitmap(photo, 0, 0, picOriginalWidth, picOriginalHeight, matrix, true);
		return newbmp;
	}
}
