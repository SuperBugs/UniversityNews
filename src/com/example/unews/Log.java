package com.example.unews;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Log extends Activity{
	 protected void onCreate(Bundle savedInstanceState) {
		    super.onCreate(savedInstanceState);
	        setContentView(R.layout.log);
	        Button button=(Button)this.findViewById(R.id.but1);
	        Button button2=(Button)this.findViewById(R.id.but2);
//	        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题栏
            button.setOnClickListener(new OnClickListener(){
     public void onClick(View v){
     		Intent intent=new Intent();
     		intent.setClass(Log.this,ViewMain.class);//要启动的窗体
     		startActivity(intent);
     	}
     });
            button2.setOnClickListener(new OnClickListener(){
                public void onClick(View v){
                		Intent intent=new Intent();
                		intent.setClass(Log.this,Regist.class);//要启动的窗体
                		startActivity(intent);
                	}
                });
	 }
	 }