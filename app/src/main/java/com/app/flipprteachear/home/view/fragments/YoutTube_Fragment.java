package com.app.flipprteachear.home.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.app.flipprteachear.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

/*import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;*/
public class YoutTube_Fragment extends Fragment {

    //    private YouTubePlayerView youTubePlayer;
//    private YouTubePlayer youTubePlayerComM;
    //  private YouTubePlayerFragment youtube_fragment;
    // RelativeLayout rel_youtube_page;
    //View view, youTubeView;
    View view;
    String video_Id="";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_yout_tube_, container, false);
        assert getArguments() != null;
        video_Id = getArguments().getString("video_Id");

        YouTubePlayerView youTubePlayerView = view.findViewById(R.id.youtube_fragment1);
        getLifecycle().addObserver(youTubePlayerView);

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                youTubePlayer.loadVideo(video_Id,0);
            }
        });
        return view;
    }
}