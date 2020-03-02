package com.example.unews;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class Attention extends Activity {
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.attention);
	LinearLayout but1=(LinearLayout)findViewById(R.id.but1);
	LinearLayout but2=(LinearLayout)findViewById(R.id.but2);
	 but1.setOnClickListener(new View.OnClickListener(){
	     public void onClick(View v){
	     		Intent intent=new Intent();
	     		intent.setClass(Attention.this,Lists.class);//要启动的窗体
	     		startActivity(intent);
	     		int i=0;
	     	}
	     });
	 but2.setOnClickListener(new View.OnClickListener(){
	     public void onClick(View v){
	     		Intent intent=new Intent();
	     		intent.setClass(Attention.this,Lists.class);//要启动的窗体
	     		startActivity(intent);
	     		int i=0;
	     	}
	     });
}

}