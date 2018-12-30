package infos.generationchange.gctv.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import cn.jzvd.JZUserAction;
import cn.jzvd.JZUserActionStd;
import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;
import infos.generationchange.gctv.R;
import infos.generationchange.gctv.categories.EBoutique;
import infos.generationchange.gctv.categories.GctvKamtoNews;
import infos.generationchange.gctv.utils.CustomDialog;


public class DirectAndTv extends Fragment {
    private TextView textView3;
    private ImageView imageView;
    private ImageView imageView5;
    private JzvdStd jzvdStd;
    private Button boutique , gctvKamtoNews , journaliste;

    private static final String TAG = "DirectAndTv";
    public DirectAndTv(){ }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.tv , container , false);

        jzvdStd = (JzvdStd) view.findViewById(R.id.videoplayer);

        boutique = view.findViewById(R.id.eboutique);
        gctvKamtoNews = view.findViewById(R.id.kamtonews);
        journaliste = view.findViewById(R.id.journaliste);


        boutique.setOnClickListener(v -> {
            startActivity(new Intent(getActivity() , EBoutique.class));
        });

        gctvKamtoNews.setOnClickListener(v -> {
            startActivity(new Intent(getActivity() , GctvKamtoNews.class));
        });

        journaliste.setOnClickListener(v -> {
            CustomDialog cdd = new CustomDialog(getActivity());
            cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            cdd.show();
        });

        imageView5 = view.findViewById(R.id.imageView5);
        imageView = view.findViewById(R.id.imageView);
        textView3 = view.findViewById(R.id.textView3);

        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jzvdStd.setVisibility(View.VISIBLE);
                imageView5.setVisibility(View.GONE);
                imageView.setVisibility(View.GONE);
                textView3.setVisibility(View.GONE);
                jzvdStd.setUp("http://192.169.189.15:1935/live/GCTV.stream_160p/playlist.m3u8",
                        "" , Jzvd.SCREEN_WINDOW_NORMAL);
                jzvdStd.startWindowTiny();
            }
        });


        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
    }
}
