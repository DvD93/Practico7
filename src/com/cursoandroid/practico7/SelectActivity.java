package com.cursoandroid.practico7;

import java.util.ArrayList;

import com.cursoandroid.practico7.collections.Peliculas;
import com.cursoandroid.practico7.entities.Pelicula;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class SelectActivity extends Activity implements OnClickListener
{
	private EditText mRanking;
	private Button mTodas;
	private Button mFiltrar;
	private Button mSalir;
	private CheckBox mVerRanking;
	private CheckBox mVerAnio;
	private ListView mPeliculas;
	
	private PeliculasAdapter mAdpPelis;
	ArrayList<Pelicula> mPelis;
	
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_activity);
 
        addWidgets();
        addListeners();
    }

	private void addWidgets()
	{
		mRanking = (EditText) findViewById(R.id.select_activity_etRanking);
		mTodas = (Button) findViewById(R.id.select_activity_btnTodos);
		mFiltrar = (Button) findViewById(R.id.select_activity_btnFiltrar);
		mSalir = (Button) findViewById(R.id.select_activity_btnSalir);
		
		mVerRanking = (CheckBox) findViewById(R.id.select_activity_chkRanking);
		mVerAnio = (CheckBox) findViewById(R.id.select_activity_chkAnio);
		
		mPeliculas = (ListView) findViewById(R.id.select_activity_lvPeliculas);
	}

	private void addListeners()
	{
		mTodas.setOnClickListener(this);
		mFiltrar.setOnClickListener(this);
		mSalir.setOnClickListener(this);
	}

	@Override
	public void onClick(View v)
	{
		switch(v.getId())
		{
		case R.id.select_activity_btnTodos:
			todas();
			break;
		case R.id.select_activity_btnFiltrar:
			filtrar();
			break;
		case R.id.select_activity_btnSalir:
			finish();
			break;
		}
	}

	private void todas()
	{
		mPelis = Peliculas.getAll(this, mVerRanking.isChecked(), mVerAnio.isChecked());
		
		if(mPelis != null);
		{
			mAdpPelis = new PeliculasAdapter(this, R.layout.select_item, mPelis);
			
			mPeliculas.setAdapter(mAdpPelis);
		}
	}

	private void filtrar()
	{
		mPelis = Peliculas.filter(this, mRanking.getText().toString(), mVerRanking.isChecked(), mVerAnio.isChecked());

		if(mPelis != null);
		{
			mAdpPelis = new PeliculasAdapter(this, R.layout.select_item, mPelis);

			mPeliculas.setAdapter(mAdpPelis);
		}
	}
	
	private class PeliculasAdapter extends ArrayAdapter<Pelicula>
	{
		protected ArrayList<Pelicula> items;

		public PeliculasAdapter(Context context, int textViewResourceId, ArrayList<Pelicula> items)
		{
			super(context, textViewResourceId, items);
			this.items = items;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			View v = convertView;
			if (v == null)
			{
				LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = vi.inflate(R.layout.select_item, null);
			}

			Pelicula peli = (Pelicula) items.get(position);
			if (peli != null)
			{
				TextView f1 = (TextView) v.findViewById(R.id.select_item_tvRanking);
				TextView f2 = (TextView) v.findViewById(R.id.select_item_tvTitulo);
				TextView f3 = (TextView) v.findViewById(R.id.select_item_tvAnio);
				
				f1.setVisibility(View.VISIBLE);
				f2.setVisibility(View.VISIBLE);
				f3.setVisibility(View.VISIBLE);
				
				if (f1 != null)
				{
					if(mVerRanking.isChecked())
						f1.setText(String.valueOf(peli.getRanking()));
					else
						f1.setVisibility(View.GONE);
				}

				if (f2 != null)
					f2.setText(peli.getTitulo());

				if (f3 != null)
				{
					if(mVerAnio.isChecked())
						f3.setText("Año " + String.valueOf(peli.getAnio()));
					else
						f3.setVisibility(View.GONE);
				}
			}
			return v;
		}
	}
}
