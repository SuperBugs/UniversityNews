package com.example.unews;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class Lists extends Activity implements OnItemClickListener,OnScrollListener{
	private ListView listView;
	private SimpleAdapter simp_adapter;
    private List<Map<String,Object>>dataList; //新建数据源
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.list);
	dataList=new ArrayList<Map<String,Object>>();//绑定数据源
	 listView=(ListView)findViewById(R.id.listView);
	 simp_adapter=new SimpleAdapter(this, getData(), R.layout.item, new String[]{"pic","text"}, new int[]{R.id.pic,R.id.text});
	 listView.setAdapter(simp_adapter);
	 listView.setOnItemClickListener(this);
	 listView.setOnScrollListener(this);
}
private List<Map<String,Object>> getData(){//声明特定泛型的集合
	for(int i=0;i<20;i++)
	{
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("pic",R.drawable.ic_launcher );
		map.put("text", "字符串"+i);
		dataList.add(map);
	}
	return dataList;
}
@Override
public void onScrollStateChanged(AbsListView view, int scrollState) {
	// TODO Auto-generated method stub
	switch(scrollState){
	case SCROLL_STATE_FLING:
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("pic", R.drawable.ic_launcher);
		map.put("text", "没有更多了");
		dataList.add(map);
		simp_adapter.notifyDataSetChanged();
		break;
	case SCROLL_STATE_IDLE:
		break;
	case SCROLL_STATE_TOUCH_SCROLL:
		
		break;
	
	}
	
}
@Override
public void onScroll(AbsListView view, int firstVisibleItem,
		int visibleItemCount, int totalItemCount) {
	// TODO Auto-generated method stub
	
}
@Override
public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	// TODO Auto-generated method stub
	Intent intent=new Intent();
		intent.setClass(Lists.this,Anew.class);//要启动的窗体
		startActivity(intent);
	
}
}