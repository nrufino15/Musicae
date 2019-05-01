package com.example.musicae.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.musicae.Modal.PlayList;
import com.example.musicae.R;

public class AddPlayListActivity extends AppCompatActivity {

    private Button setPlayList;
    private EditText playListTitle, playListDesc;

    private String Title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_play_list);

        setPlayList = findViewById(R.id.createBtn);
        playListTitle = findViewById(R.id.playListTitle);
        playListDesc = findViewById(R.id.playListDecription);

        final PlayListFragment playListFragment = new PlayListFragment();

        setPlayList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NavigationBarWithTabsActivity.class);
                Title = playListTitle.getText().toString();
                playListFragment.mList.add(new PlayList(Title));
                playListFragment.recyclerViewAdapter.notifyItemInserted(PlayList);
                startActivity(intent);
                finish();
            }
        });
    }
}
