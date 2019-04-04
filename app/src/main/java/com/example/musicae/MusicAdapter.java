package com.example.musicae;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import static android.app.PendingIntent.getActivity;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicViewHolder> {

    List<Music> list;

    MusicAdapter(List<Music> list){
        this.list = list;
    }

    @Override
    public MusicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemMusic = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_music, parent, false);
        return new MusicViewHolder(itemMusic);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicViewHolder holder, int position) {
        Music music = list.get(position);

        holder.musicTitle.setText(music.title);
        holder.musicArtist.setText(music.artist);
        holder.musicLength.setText(music.length);
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
            musicTitle = itemMusic.findViewById(R.id.musicTitle);
            musicArtist = itemMusic.findViewById(R.id.musicArtist);
            musicLength = itemMusic.findViewById(R.id.musicLength);
        }
    }
}
