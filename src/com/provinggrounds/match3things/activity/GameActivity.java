package com.provinggrounds.match3things.activity;

import com.proving.grounds.match3things.R;
import com.proving.grounds.match3things.R.layout;
import com.proving.grounds.match3things.R.menu;
import com.provinggrounds.match3things.game.Grid;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.support.v4.app.NavUtils;

public class GameActivity extends Activity {

    Grid currentGameGrid;
    GridView gameGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_game);
	// Show the Up button in the action bar.
	setupActionBar();

	currentGameGrid = new Grid(4, 4, 6);
	gameGridView = createGridView(currentGameGrid);
    }

    private GridView createGridView(Grid gameGrid) {
	GridView newGridView = new GridView(this);
	newGridView.setNumColumns(gameGrid.getWidth());
	return newGridView;
    }

    /**
     * Set up the {@link android.app.ActionBar}.
     */
    private void setupActionBar() {

	getActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.game, menu);
	return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
	switch (item.getItemId()) {
	case android.R.id.home:
	    // This ID represents the Home or Up button. In the case of this
	    // activity, the Up button is shown. Use NavUtils to allow users
	    // to navigate up one level in the application structure. For
	    // more details, see the Navigation pattern on Android Design:
	    //
	    // http://developer.android.com/design/patterns/navigation.html#up-vs-back
	    //
	    NavUtils.navigateUpFromSameTask(this);
	    return true;
	}
	return super.onOptionsItemSelected(item);
    }

}
