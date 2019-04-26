package com.example.musicae;


import android.content.ContentResolver;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MusicFragment extends Fragment {

    private RecyclerViewAdapter recyclerViewAdapter;
    List<Song> mList = new ArrayList<>();

    MediaPlayer mediaPlayer;

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

        final RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(), mList);
        recyclerView.setAdapter(recyclerViewAdapter);

        return view;
    }

    public void getSongs() {
        ContentResolver contentResolver = getActivity().getContentResolver();
        Uri musicUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor songCursor = contentResolver.query(musicUri, null, null, null, null);

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

//    public void playSong() throws IOException {
//        Song song = new Song();
//        String filePath = song.uri;
//        mediaPlayer = new MediaPlayer();
//        mediaPlayer.setDataSource(filePath);
//        mediaPlayer.prepare();
//        mediaPlayer.start();
//
//    }
}
