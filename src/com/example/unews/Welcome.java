package com.example.unews;

import com.example.unews.ViewMain;
import com.example.unews.Welcome;
import com.example.unews.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
public class Welcome extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
	
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		ImageView iv=(ImageView)this.findViewById(R.id.wpic);//获取welcome.xml中id为wpic的ImageView组件
		iv.setImageResource(R.drawable.welcome1);//设置ImageView上显示的资源
//		final Window win = getWindow();
//		win.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题栏
		welcome();//欢迎界面
	}
	
		
	
	public void welcome() {
		new Thread(new Runnable() {//创建线程
			public void run() {//实现Runnable的run方法，即线程体
				try {
					Thread.sleep(2000);//欢迎界面暂停2秒钟
					Message m = new Message();//创建Message对象
					logHandler.sendMessage(m);//将消息放到消息队列中
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();//启动线程
	}
	Handler logHandler = new Handler() {
		public void handleMessage(Message msg) {
			welcome1();
		}
	};
	public void welcome1() {
		Intent it=new Intent();//实例化Intent
		it.setClass(Welcome.this,Log.class);//设置Class
    	startActivity(it);//启动Activity
    	Welcome.this.finish();//结束Welcome Activity 	
	}

}