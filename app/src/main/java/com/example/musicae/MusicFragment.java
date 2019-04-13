package com.example.musicae;


import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MusicFragment extends Fragment {

    private ArrayList<Song> arrayList;
    private SongAdapter songAdapter;
    List<Song> songList = new ArrayList<>();

    public MusicFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_music, container, false);

        getSongs();

        RecyclerView recyclerView = view.findViewById(R.id.music_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        final SongAdapter songAdapter = new SongAdapter(songList);
        recyclerView.setAdapter(songAdapter);


        return view;
    }

    public void getSongs() {
        ContentResolver contentResolver = getActivity().getContentResolver();
        Uri musicUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor songCursor = contentResolver.query(musicUri, null, null, null, null);

        if (songCursor != null) {
            if (songCursor.moveToFirst()) {
                do {
                    String name = songCursor.getString(songCursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                    String artist = songCursor.getString(songCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                    String length = songCursor.getString(songCursor.getColumnIndex(MediaStore.Audio.Media.DURATION));

                    Song song = new Song();
                    song.artist = artist;
                    song.length = length;
                    song.title = name;

                    songList.add(song);

                } while (songCursor.moveToNext());
            }
        }
    }
}
