package com.provinggrounds.match3things.activity;

import java.util.Collection;
import java.util.HashSet;

import com.proving.grounds.match3things.R;
import com.provinggrounds.match3things.fragment.MatchingSetInfoDisplayFragment;
import com.provinggrounds.match3things.game.Grid;
import com.provinggrounds.match3things.game.MatchingSet;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
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

	currentGameGrid = Grid.createGrid(gridWidth, gridHeight, gridNumBlockTypes);
	setGridViewProperties(currentGameGrid);
    }

    private void setGridViewProperties(Grid gameGrid) {
	GridView gameGridView = (GridView)findViewById(R.id.gameGrid);
	gameGridView.setNumColumns(gameGrid.getWidth());
	ArrayAdapter<Integer> gridAdapter = new ArrayAdapter<Integer>(this, R.layout.grid_element_text_view, gameGrid.getGameGrid());
	gameGridView.setAdapter(gridAdapter);
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

    public void findMatchesInGrid(View showMatchesButton) {
	//disable "Show matches" button to prevent extra unnecessary calls
	showMatchesButton.setClickable(false);
	Collection<MatchingSet> matchingSets = currentGameGrid.findMatches();
	//mark blocks deleted
	//generate match set string
	//log match set information to logcat
	/*
	 * display match information
	 * 1. Show fragment
	 * 2. Show match information in fragment's TextView
	 */
	getFragmentManager().beginTransaction().add(R.id.matchingSetDisplay, new MatchingSetInfoDisplayFragment()).commit();
    }

    /**
     * Returns a collection of strings that represent the matching sets passed in.
     * The collection of strings will not necessarily be in the same order as the 
     * collection of matching sets passed in.
     *
     * @param   matchingSets   The collection of matching sets for which string representations need to be generated
     * @return                 The collection of strings representing the matching sets passed in
     * @see     MatchingSet
     */
    private static Collection<String> generateMatchSetStrings(Collection<MatchingSet> matchingSets) {
	Collection<String> matchSetStrings = new HashSet<String>();
	for(MatchingSet ms : matchingSets) {
	    matchSetStrings.add(ms.toString());
	}
	return matchSetStrings;
    }
}
