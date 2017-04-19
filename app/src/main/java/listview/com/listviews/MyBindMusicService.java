package listview.com.listviews;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.widget.Toast;

import java.io.IOException;

public class MyBindMusicService extends Service {

    IBinder ibref = new MyBinder();
    String songToPlay;
    MediaPlayer mediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "Service Cretaed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Service started", Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service Destroy", Toast.LENGTH_SHORT).show();
    }


    @Override
    public IBinder onBind(Intent intent) {

        mediaPlayer = new MediaPlayer();

        Toast.makeText(this, "Service Bound", Toast.LENGTH_SHORT).show();

        songToPlay = intent.getStringExtra("keySong");
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + songToPlay;

        try {
            mediaPlayer.setDataSource(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  ibref;

    }

    @Override
    public boolean onUnbind(Intent intent) {

        Toast.makeText(this, "Service unBound", Toast.LENGTH_SHORT).show();
        return super.onUnbind(intent);
    }

    public  void playMusic(){
        mediaPlayer.start();
    }

    public  void pauseMusic(){
        mediaPlayer.pause();
    }

    public  void  stopMusic(){
        mediaPlayer.stop();
        mediaPlayer.release();
    }

    class  MyBinder extends Binder{

        MyBindMusicService getServiceRefrence(){
            return MyBindMusicService.this;
        }
    }
}
