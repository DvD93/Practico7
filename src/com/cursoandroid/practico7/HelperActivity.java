package com.cursoandroid.practico7;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class HelperActivity extends Activity implements OnClickListener
{
	private TextView mMensaje;
	private Button mCrear;
	
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.helper_activity);
 
        addWidgets();
        addListeners();
    }

	private void addWidgets()
	{
		mMensaje = (TextView) findViewById(R.id.helper_activity_tvMensaje);
		mCrear = (Button) findViewById(R.id.helper_activity_btnCrear);
	}

	private void addListeners()
	{
		mCrear.setOnClickListener(this);		
	}

	@Override
	public void onClick(View v)
	{
		crearDB();
	}

	private void crearDB()
	{
		PeliculasSQLiteHelper pelisHelper = new PeliculasSQLiteHelper(this, "DBPeliculas", null, 1);
		 
        SQLiteDatabase db = pelisHelper.getWritableDatabase();

        if(db != null)
        {
        	mMensaje.setText("Base de datos creada!!");
            db.close();
        }
        else
        	mMensaje.setText("No se pudo crear la base de datos!!");
	}
}
