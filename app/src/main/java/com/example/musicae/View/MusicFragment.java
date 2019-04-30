package com.example.musicae.View;


import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.musicae.R;
import com.example.musicae.RecyclerViewAdapter;
import com.example.musicae.Modal.Song;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class MusicFragment extends Fragment {

    List<Song> mList = new ArrayList<>();


    public MusicFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_music, container, false);

        getSongs();

        RecyclerView recyclerView = view.findViewById(R.id.music_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        final RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(), mList);
        recyclerView.setAdapter(recyclerViewAdapter);

        return view;
    }

    public void getSongs() {
        ContentResolver contentResolver = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            contentResolver = Objects.requireNonNull(getActivity()).getContentResolver();
        }
        Uri musicUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        assert contentResolver != null;
        @SuppressLint("Recycle") Cursor songCursor = contentResolver.query(musicUri, null, null, null, null);

        if (songCursor != null) {
            if (songCursor.moveToFirst()) {
                do {
                    String data = songCursor.getString(songCursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                    String name = songCursor.getString(songCursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                    String artist = songCursor.getString(songCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                    String length = songCursor.getString(songCursor.getColumnIndex(MediaStore.Audio.Media.DURATION));

                    Song song = new Song();
                    song.uri = data;
                    song.artist = artist;
                    song.length = length;
                    song.title = name;

                    mList.add(song);

                } while (songCursor.moveToNext());
            }
        }
    }
}
