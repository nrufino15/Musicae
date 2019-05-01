package com.example.musicae;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class SlideAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SlideAdapter(Context context) {
        this.context = context;
    }

    //Arrays
    public int[] slide_img = {
        R.drawable.musicae_icon,
        R.drawable.play_icon,
        R.drawable.warnig_icon
    };

    public String[] slide_headings = {
            "BIENVENIDO",
            "REPRODUCIR",
            "INCICENCIAS"
    };

    public String[] slide_descs = {
            "Bienvenidos a Musicae, con esta app podras" +
            "reproducir las cansiones de tu dispositivo en cualquier" +
            "momento y sin publicidad.",
            "Haz clic en las canciones para reproducirlas.",
            "Haz clic en incidencias en el menú desplegable de la derecha " +
            "para poder mandar algún problema de la app."
    };

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (ConstraintLayout) o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.slide_img);
        TextView slideHeading = (TextView) view.findViewById(R.id.slide_header);
        TextView slideDesc = (TextView) view.findViewById(R.id.slide_desc);

        slideImageView.setImageResource(slide_img[position]);
        slideHeading.setText(slide_headings[position]);
        slideDesc.setText(slide_descs[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }
}
