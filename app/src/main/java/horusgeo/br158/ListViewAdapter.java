package horusgeo.br158;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by rafael on 11/06/2017.
 */

public class ListViewAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<String> imgid;
    private final ImageHelper imgHelper;

    public ListViewAdapter(Activity context,  ArrayList<String> imgid) {
        super(context, R.layout.row, imgid);
        // TODO Auto-generated constructor stub

        this.context = context;
        this.imgid = imgid;
        this.imgHelper = new ImageHelper();
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.row, null, true);

        ImageView ivImage = (ImageView) rowView.findViewById(R.id.rowImageView);

        ivImage.setImageBitmap(imgHelper.decodeSampledBitmapFromFile(imgid.get(position), 300, 300));
        return rowView;

    };
}