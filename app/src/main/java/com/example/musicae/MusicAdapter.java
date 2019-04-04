package com.example.musicae;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.app.PendingIntent.getActivity;

public class MusicAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Music> arrayList;

    public MusicAdapter(Context context, int layout, ArrayList<Music> arrayList) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private  class ViewHolder  {
        TextView musicTitle, musicArtist, musicLength;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = layoutInflater.inflate(layout, null);
            viewHolder.musicTitle = (TextView) convertView.findViewById(R.id.musicTitle);
            viewHolder.musicArtist = (TextView) convertView.findViewById(R.id.musicArtist);
            viewHolder.musicLength = (TextView) convertView.findViewById(R.id.musicLength);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Music music = arrayList.get(position);
        viewHolder.musicTitle.setText(music.getName());
        viewHolder.musicArtist.setText(music.getName());
        viewHolder.musicLength.setText(music.getName());
        return convertView;
    }


//    public void getMusic() {
//        ContentResolver contentResolver = getActivity().getContentResolver();
//        Uri musicUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
//        Cursor musicCursor = contentResolver.query(musicUri, null, null, null, null);
//
//        if (musicCursor != null) {
//            if (musicCursor.moveToFirst()) {
//                do {
//                    String url = musicCursor.getString(musicCursor.getColumnIndex(MediaStore.Audio.Media.DATA));
//                    String name = musicCursor.getString(musicCursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
//                    String artist = musicCursor.getString(musicCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
//                    String length = musicCursor.getString(musicCursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
//                } while (musicCursor.moveToNext());
//            }
//        }
//    }
}
