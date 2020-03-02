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
		ImageView iv=(ImageView)this.findViewById(R.id.wpic);//��ȡwelcome.xml��idΪwpic��ImageView���
		iv.setImageResource(R.drawable.welcome1);//����ImageView����ʾ����Դ
//		final Window win = getWindow();
//		win.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);//���ر�����
		welcome();//��ӭ����
	}
	
		
	
	public void welcome() {
		new Thread(new Runnable() {//�����߳�
			public void run() {//ʵ��Runnable��run���������߳���
				try {
					Thread.sleep(2000);//��ӭ������ͣ2����
					Message m = new Message();//����Message����
					logHandler.sendMessage(m);//����Ϣ�ŵ���Ϣ������
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();//�����߳�
	}
	Handler logHandler = new Handler() {
		public void handleMessage(Message msg) {
			welcome1();
		}
	};
	public void welcome1() {
		Intent it=new Intent();//ʵ����Intent
		it.setClass(Welcome.this,Log.class);//����Class
    	startActivity(it);//����Activity
    	Welcome.this.finish();//����Welcome Activity 	
	}

}