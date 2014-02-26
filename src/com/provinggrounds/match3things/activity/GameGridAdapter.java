package com.provinggrounds.match3things.activity;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.provinggrounds.match3things.game.Grid;

/**
 * Represents game grid to a View. Meant to be used with a GridView to display
 * the game grid.
 */
final class GameGridAdapter extends BaseAdapter {

    private final Grid gameGrid;
    private final Context mContext;

    /**
     * @param  grid  Class stores a reference to this object. Must be a fully initialized game grid. Method throws AssertionError if parameter is null.
     */
    public GameGridAdapter(Grid grid, Context context) {
        super();
        assert grid!=null;
        this.gameGrid = grid;
        this.mContext = context;
    }


    /**
     * @return  Returns number of elements in Grid object passed to constructor.
     */
    @Override
    public int getCount() {
        return gameGrid.getHeight()*gameGrid.getWidth();
    }

    /**
     * @return   Returns grid object at specified position
     */
    @Override
    public Object getItem(int position) {
        return gameGrid.getGameGrid()[position];
    }

    /**
     * @return   Stub method. Always returns 0.
     */
    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;
        if(convertView == null) {
            textView = new TextView(mContext);
            textView.setGravity(Gravity.CENTER);
            //textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            textView.setText(gameGrid.getGameGrid()[position].toString());
        }
        else {
            textView = (TextView) convertView;
        }
        if(gameGrid.getGameGrid()[position].equals(0)) {
            textView.setText("X");
        }
        return textView;
    }
}
