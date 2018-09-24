package yazzyyas.geoguessswipe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<GeoObject> mGeoObjects = new ArrayList<>();

        for (int i = 0; i < GeoObject.ObjectImages.length; i++) {
            mGeoObjects.add(new GeoObject(GeoObject.ObjectNames[i],
                    GeoObject.ObjectImages[i]));
        }

        RecyclerView mGeoRecyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, LinearLayoutManager.VERTICAL); //2 cells per row
        mGeoRecyclerView.setLayoutManager(mLayoutManager);
        GeoObjectAdapter mAdapter = new GeoObjectAdapter(this, mGeoObjects);
        mGeoRecyclerView.setAdapter(mAdapter);

    }
}
