package com.provinggrounds.match3things.fragment;

import com.proving.grounds.match3things.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MatchingSetInfoDisplayFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	    return inflater.inflate(R.layout.matching_set_display_layout, container, false);
	}
}
