package com.provinggrounds.match3things.activity;

import com.proving.grounds.match3things.R;
import com.proving.grounds.match3things.R.layout;
import com.proving.grounds.match3things.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainMenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main_menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.main_menu, menu);
	return true;
    }
    
    public void launchNewGame(View view) {
	//launch GameActivity with a new game
	Intent intent = new Intent(this, GameActivity.class);
	startActivity(intent);
    }

}
