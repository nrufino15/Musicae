package com.example.musicae;


import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MusicFragment extends Fragment {

    private ArrayList<Music> arrayList;
    private MusicAdapter adapter;
    private ListView musicList;

    public MusicFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_music, container, false);

        adapter = new MusicAdapter(getActivity());

        return view;
    }

    public void getMusic() {
        ContentResolver contentResolver = getActivity().getContentResolver();
        Uri musicUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor musicCursor = contentResolver.query(musicUri, null, null, null, null);

        if (musicCursor != null) {
            if (musicCursor.moveToFirst()) {
                do {
                    String url = musicCursor.getString(musicCursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                    String name = musicCursor.getString(musicCursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                    String artist = musicCursor.getString(musicCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                    String length = musicCursor.getString(musicCursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
                } while (musicCursor.moveToNext());
            }
        }
    }
}
