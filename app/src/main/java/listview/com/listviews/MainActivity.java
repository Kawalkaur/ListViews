

package listview.com.listviews;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView listView;
    ArrayList<FileBean> fileList;
    FilesAdapter adapter;

    void readFileSystem() {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() ;
        File file = new File(path);
        File[] files = file.listFiles();

        for (File f : files) {
            FileBean fb = new FileBean();

           /* if (f.isFile()) {
                fb.setImage(R.drawable.file);
                fb.setTitles(f.getName());
            } else {
                fb.setImage(R.drawable.folder);
                fb.setTitles(f.getName());
                fileList.add(fb);
            */

           if(f.isFile() && f.getName().endsWith(".mp3")){
               fb.setImage(R.drawable.musicplayer);
               fb.setTitles(f.getName());
               fileList.add(fb);
            }

            adapter = new FilesAdapter(this, R.layout.listitem, fileList);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(this);

        }
    }

    void initviews(){
        listView = (ListView)findViewById(R.id.ListView);
        fileList = new ArrayList<>();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initviews();
        readFileSystem();
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
        FileBean fb = fileList.get(i);
        Toast.makeText(this,"You Selected:"+fb.getTitles(),Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(MainActivity.this,PlayActivity.class);
        intent.putExtra("keySong", fb.getTitles());
        startActivity(intent);

    }
}
