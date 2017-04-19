package listview.com.listviews;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PlayActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textSong;
    Button buttonPlay, buttonStop;
    String songName;
    MyBindMusicService service;

    void initViews(){
        textSong = (TextView)findViewById(R.id.textSong);
        buttonPlay = (Button)findViewById(R.id.buttonPlay);
        buttonStop = (Button)findViewById(R.id.buttonStop);

        Intent rcv  = getIntent();
        songName = rcv.getStringExtra("keySong");
        textSong.setText(songName);

       buttonPlay.setOnClickListener(this);
        buttonStop.setOnClickListener(this);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        initViews();

        Intent intent = new Intent(PlayActivity.this, MyBindMusicService.class);
        intent.putExtra("keySong", songName);
        bindService(intent,connection,BIND_AUTO_CREATE);
    }

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder iBinder) {
            MyBindMusicService.MyBinder myBinder = (MyBindMusicService.MyBinder) iBinder;
            service = myBinder.getServiceRefrence();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };

    @Override
    public void onClick(View v) {
        int i = v.getId();
      if (i == R.id.buttonPlay) {
        /*    Toast.makeText(this, "Song Played", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Sorry...Song not played", Toast.LENGTH_SHORT).show();

        }*/

          // Intent intent = new Intent(PlayActivity.this, MyMusicService.class);
          //intent.putExtra("keySong", songName);
          //startService(intent);
          service.playMusic();

      }else{
         // Intent intent = new Intent(PlayActivity.this,MyMusicService.class);
          //stopService(intent);
        service.pauseMusic();
      }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        service.stopMusic();
        unbindService(connection);
    }
}
