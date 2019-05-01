package com.example.musicae.View;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.musicae.GridRecyclerViewAdapter;
import com.example.musicae.Modal.PlayList;
import com.example.musicae.R;

import java.util.ArrayList;
import java.util.List;



/**
 * A simple {@link Fragment} subclass.
 */
public class PlayListFragment extends Fragment {

    List<PlayList> mList = new ArrayList<>();

    private String NamePlayList;

    public FloatingActionButton floatingActionButton;
    static GridRecyclerViewAdapter recyclerViewAdapter;

    public PlayListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_play_list, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.play_list);

        recyclerViewAdapter = new GridRecyclerViewAdapter(getContext(), mList);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setAdapter(recyclerViewAdapter);

        updateData(view);
        return view;
    }

    public void updateData(View view){
        mList.add(new PlayList("Pop"));
        mList.add(new PlayList("Rock"));

        floatingActionButton = view.findViewById(R.id.addPlayList);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddPlayListActivity.class);
                startActivity(intent);
            }
        });
    }
}
