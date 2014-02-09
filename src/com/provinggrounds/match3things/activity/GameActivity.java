package com.provinggrounds.match3things.activity;

import com.proving.grounds.match3things.R;
import com.provinggrounds.match3things.game.Grid;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.GridView;

public class GameActivity extends Activity {

    private static final int defaultNumBlockTypes = 6;
    private static final int defaultGridWidth = 4;
    private static final int defaultGridHeight = 4;
    Grid currentGameGrid;
    GridView gameGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_game);
	// Show the Up button in the action bar.
	setupActionBar();

	Intent callingIntent = getIntent();
	int gridHeight = callingIntent.getIntExtra(IntentExtraReferences.HEIGHT, defaultGridHeight);
	int gridWidth = callingIntent.getIntExtra(IntentExtraReferences.WIDTH, defaultGridWidth);
	int gridNumBlockTypes = callingIntent.getIntExtra(IntentExtraReferences.NUMBLOCKTYPES, defaultNumBlockTypes);

	currentGameGrid = new Grid(gridHeight, gridWidth, gridNumBlockTypes);
	gameGridView = createGridView(currentGameGrid);
    }

    private GridView createGridView(Grid gameGrid) {
	GridView newGridView = new GridView(this);
	newGridView.setNumColumns(gameGrid.getWidth());
	ArrayAdapter<int[]> gridAdapter = new ArrayAdapter<int[]>(this, R.layout.grid_element_text_view, gameGrid.getGameGrid());
	newGridView.setAdapter(gridAdapter);
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

}
