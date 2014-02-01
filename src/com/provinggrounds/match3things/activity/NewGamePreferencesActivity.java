package com.provinggrounds.match3things.activity;

import com.proving.grounds.match3things.R;
import com.provinggrounds.match3things.constants.IntentExtraReferences;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.app.Activity;
import android.content.Intent;

public class NewGamePreferencesActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_new_game_preferences);
    }

    /*
     * Called on clicking OK button
     */
    public void launchNewGame(View clickedView) {
	int height = Integer.parseInt(((EditText) findViewById(R.id.Input_Height)).getText().toString());
	int width = Integer.parseInt(((EditText) findViewById(R.id.Input_Width)).getText().toString());
	int numBlockTypes = Integer.parseInt(((EditText) findViewById(R.id.Input_NumBlockTypes)).getText().toString());

	Intent startGameActivityIntent = new Intent(this, GameActivity.class);
	startGameActivityIntent.putExtra(IntentExtraReferences.HEIGHT, height);
	startGameActivityIntent.putExtra(IntentExtraReferences.WIDTH , width);
	startGameActivityIntent.putExtra(IntentExtraReferences.NUMBLOCKTYPES , numBlockTypes);

	startActivity(startGameActivityIntent);
    }
}
