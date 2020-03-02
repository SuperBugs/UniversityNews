package com.example.unews;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;


public class ViewMain extends TabActivity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final TabHost tabHost = getTabHost();//获取TabHost对象
	    tabHost.addTab(tabHost.newTabSpec("tab1")
	            .setIndicator("新鲜事",getResources().getDrawable(R.drawable.ic_launcher))
	            .setContent(new Intent(this, News.class)));
	    tabHost.addTab(tabHost.newTabSpec("tab2")
	            .setIndicator("我的学校", getResources().getDrawable(R.drawable.ic_launcher))
	            .setContent(new Intent(this, Myschool.class)));
	    tabHost.addTab(tabHost.newTabSpec("tab3")
	            .setIndicator("关注", getResources().getDrawable(R.drawable.ic_launcher ))
	            .setContent(new Intent(this,Attention.class)));
	    tabHost.addTab(tabHost.newTabSpec("tab4")
	            .setIndicator("我的", getResources().getDrawable(R.drawable.ic_launcher))
	            .setContent(new Intent(this, Info.class)));
	    tabHost.setCurrentTab(0);
	}
	
}

