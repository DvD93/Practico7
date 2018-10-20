package com.cursoandroid.practico7;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener
{
	private Button mHelper;
	private Button mABM;
	private Button mSelect;
	private Button mSalir;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
	}
	
	@Override
	public void onResume()
	{
		super.onResume();
		
		addWidgets();
		addListeners();
	}

	private void addListeners()
	{
		mHelper.setOnClickListener(MainActivity.this);
		mABM.setOnClickListener(MainActivity.this);
		mSelect.setOnClickListener(MainActivity.this);
		mSalir.setOnClickListener(MainActivity.this);
	}

	private void addWidgets()
	{
		mHelper = (Button) findViewById(R.id.main_activity_btnHelper);
		mABM = (Button) findViewById(R.id.main_activity_btnABM);
		mSelect = (Button) findViewById(R.id.main_activity_btnSelect);
		mSalir = (Button) findViewById(R.id.main_activity_btnSalir);
	}

	@Override
	public void onClick(View v)
	{
		Intent intent = new Intent();
		
		switch(v.getId())
		{
		case R.id.main_activity_btnHelper:
			intent.setClass(MainActivity.this, HelperActivity.class);
			break;
		case R.id.main_activity_btnABM:
			intent.setClass(MainActivity.this, ABMActivity.class);
			break;
		case R.id.main_activity_btnSelect:
			intent.setClass(MainActivity.this, SelectActivity.class);
			break;
		case R.id.main_activity_btnSalir:
			finish();
			return;
		}
		
		startActivity(intent);
	}
}
