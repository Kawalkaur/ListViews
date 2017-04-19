package listview.com.listviews;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kawaldeep on 3/29/2017.
 */

public class FilesAdapter extends ArrayAdapter<FileBean> {

    Context context;
    int resources;
    ArrayList<FileBean> fileList;


    public FilesAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<FileBean> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resources = resource;
        fileList = objects;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    View view =null;

        view = LayoutInflater.from(context).inflate(resources, parent, false);

        ImageView imageView = (ImageView)view.findViewById(R.id.imageFile);
        TextView textTitle = (TextView)view.findViewById(R.id.textViewFile);

        FileBean fb = fileList.get(position);
        imageView.setBackgroundResource(fb.getImage());
        textTitle.setText(fb.getTitles());
        return view;

   }
}


