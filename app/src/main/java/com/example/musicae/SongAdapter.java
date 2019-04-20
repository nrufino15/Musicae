package com.example.musicae;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import static android.app.PendingIntent.getActivity;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.MusicViewHolder> {

    List<Song> list;

    SongAdapter(List<Song> list){
        this.list = list;
    }

    @Override
    public MusicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemMusic = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_music, parent, false);
        return new MusicViewHolder(itemMusic);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicViewHolder holder, int position) {
        Song song = list.get(position);

        holder.musicTitle.setText(song.title);
        holder.musicArtist.setText(song.artist);
        holder.musicLength.setText(song.length);
    }

    @Override
    public int getItemCount() {
        return (list != null ? list.size() : 0);
    }

    class MusicViewHolder extends RecyclerView.ViewHolder {
        private TextView musicTitle;
        private TextView musicArtist;
        private TextView musicLength;

        MusicViewHolder(View itemMusic) {
            super(itemMusic);
            musicTitle = itemMusic.findViewById(R.id.songTitle);
            musicArtist = itemMusic.findViewById(R.id.songArtist);
            musicLength = itemMusic.findViewById(R.id.musicLength);
        }
    }

}
