package yazzyyas.geoguessswipe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private GeoObjectAdapter goAdapter;
    private RecyclerView goRecyclerView;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<GeoObject> mGeoObjects = new ArrayList<>();

        for (int i = 0; i < GeoObject.ObjectImages.length; i++) {
            mGeoObjects.add(new GeoObject(GeoObject.ObjectNames[i], GeoObject.ObjectImages[i]));
        }

        goRecyclerView = findViewById(R.id.recyclerView);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false); //2 cells per row
        goRecyclerView.setLayoutManager(mLayoutManager);

        goAdapter = new GeoObjectAdapter(this, mGeoObjects);
        goRecyclerView.setAdapter(goAdapter);

        imageView = findViewById(R.id.geoImageView);


//        RecyclerView mGeoRecyclerView = findViewById(R.id.recyclerView);
//        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, LinearLayoutManager.VERTICAL);
//        mGeoRecyclerView.setLayoutManager(mLayoutManager);
//        GeoObjectAdapter mAdapter = new GeoObjectAdapter(this, mGeoObjects);
//        mGeoRecyclerView.setAdapter(mAdapter);

         /*
Add a touch helper to the RecyclerView to recognize when a user swipes.
An ItemTouchHelper enables touch behavior (like swipe and move) on each ViewHolder,
and uses callbacks to signal when a user is performing these actions.
*/

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback =
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder
                            target) {
                        return false;
                    }

                    //Called when a user swipes left or right on a ViewHolder
                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                        //Get the index corresponding to the selected position
                        int position = (viewHolder.getAdapterPosition());
                        if (swipeDir == ItemTouchHelper.LEFT) {
                            //whatever code you want the swipe to perform
//
                        }
                        goAdapter.notifyItemChanged(position);
                    }
                };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(goRecyclerView);
    }
}