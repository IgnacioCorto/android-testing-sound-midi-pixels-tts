package com.myapp;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.util.Log;

import java.io.FileInputStream;

/**
 * Created by Ignacio on 15/02/2020.
 */

public class MediaPlayerExample {
    public MediaPlayerExample(Context context) {

//        String filename = "audio/old-sample.mp3";
        String filename = "audio/darude-sandstorm-1.mid";
//        String filename = "audio/beethoven-no-5-4th.mid";

        Log.e("MediaPlayerExample", "** filename: " + filename);

        Log.e("MediaPlayerExample", "entered in player");

        MediaPlayer mp = new MediaPlayer();
//        AsyncPlayer ap = new AsyncPlayer("debug1");
//        Uri uri = Uri.fromFile(new File(filename));
//        ap.play(context, uri, false, AudioManager.STREAM_MUSIC);

        try {

//            final InputStream in = context.getAssets().open(filename);
            FileInputStream fis = new FileInputStream(context.getAssets().openFd(filename).getFileDescriptor());

            Log.e("MediaPlayerExample", "audio ok!");
            for(String as : context.getAssets().list("audio") ){
                System.out.println(as);
            }
            for(String as : context.fileList() ){
                System.out.println(as);
            }

            AssetFileDescriptor fd = context.getAssets().openFd(filename);
//            FileDescriptor fd = context.getAssets().openFd(filename).getFileDescriptor();
            mp.setDataSource(fd.getFileDescriptor(), fd.getStartOffset(), fd.getLength());  //(fd); //fd
            mp.setOnPreparedListener(mp_ready);
//            mp.prepareAsync();
            mp.prepare();
            Log.e("MediaPlayerExample", "audio ready to play!");
            mp.start();

        } catch(final Throwable tx) {
            Log.e("MediaPlayerExample", "audio not read!" + tx.getMessage());
        }
    }

    final OnPreparedListener mp_ready = new OnPreparedListener(){
        public void onPrepared(MediaPlayer player) {
            player.start();
            Log.e("MediaPlayerExample", "...playing...!");
        }
    };
}
