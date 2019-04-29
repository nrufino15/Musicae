package com.example.musicae;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.example.musicae.Modal.Song;
import com.example.musicae.View.CurrentSongActivity;
import com.example.musicae.View.MusicFragment;

import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<Song> mList;
    private Context mContext;


    public RecyclerViewAdapter(Context mContext, List<Song> mList) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_music, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Song song = mList.get(position);

        holder.musicTitle.setText(song.title);
        holder.musicArtist.setText(song.artist);
        holder.musicLength.setText(song.length);


        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, song.title, Toast.LENGTH_SHORT).show();
                sendData(song.uri, song.title, song.length);

            }
        });

        holder.parentLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                openDialog(position);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return (mList != null ? mList.size() : 0);
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView musicTitle;
        private TextView musicArtist;
        private TextView musicLength;
        private CardView parentLayout;

        ViewHolder(View itemView) {
            super(itemView);
            musicTitle = itemView.findViewById(R.id.musicTitle);
            musicArtist = itemView.findViewById(R.id.musicArtist);
            musicLength = itemView.findViewById(R.id.musicLength);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }


    public void openDialog(int item){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("Información")
                .setMessage("Titulo: " + mList.get(item).title
                        + "\n\nArtista: " + mList.get(item).artist
                        + "\n\nDuración (ms): " + mList.get(item).length
                        + "\n\nAlacenamiento: " + mList.get(item).uri)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.show();
    }

    public void sendData(String uri, String title, String length) {
        Intent intent = new Intent(mContext.getApplicationContext(), CurrentSongActivity.class);
        intent.putExtra("URI", uri);
        intent.putExtra("SONG_TITLE", title);
        intent.putExtra("SONG_DURATION", length);
        mContext.startActivity(intent);

    }
}
