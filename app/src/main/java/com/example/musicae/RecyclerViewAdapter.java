package com.example.musicae;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.musicae.Modal.Song;

import java.util.List;

import static android.app.PendingIntent.getActivity;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> implements PopupMenu.OnMenuItemClickListener {

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
            }
        });

        holder.parentLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showPopUp(v);
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
            musicTitle = itemView.findViewById(R.id.songTitle);
            musicArtist = itemView.findViewById(R.id.songArtist);
            musicLength = itemView.findViewById(R.id.musicLength);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }


    public void showPopUp(View v) {
        PopupMenu popup = new PopupMenu(mContext, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup_menu);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Toast.makeText(mContext, "Item 1 click", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;
        }
    }

}
