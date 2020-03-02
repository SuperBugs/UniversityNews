package com.example.unews;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;


public class ViewMain extends TabActivity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final TabHost tabHost = getTabHost();//��ȡTabHost����
	    tabHost.addTab(tabHost.newTabSpec("tab1")
	            .setIndicator("������",getResources().getDrawable(R.drawable.ic_launcher))
	            .setContent(new Intent(this, News.class)));
	    tabHost.addTab(tabHost.newTabSpec("tab2")
	            .setIndicator("�ҵ�ѧУ", getResources().getDrawable(R.drawable.ic_launcher))
	            .setContent(new Intent(this, Myschool.class)));
	    tabHost.addTab(tabHost.newTabSpec("tab3")
	            .setIndicator("��ע", getResources().getDrawable(R.drawable.ic_launcher ))
	            .setContent(new Intent(this,Attention.class)));
	    tabHost.addTab(tabHost.newTabSpec("tab4")
	            .setIndicator("�ҵ�", getResources().getDrawable(R.drawable.ic_launcher))
	            .setContent(new Intent(this, Info.class)));
	    tabHost.setCurrentTab(0);
	}
	
}

