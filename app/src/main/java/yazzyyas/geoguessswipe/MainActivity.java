package yazzyyas.geoguessswipe;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private GeoObjectAdapter goAdapter;
    private RecyclerView goRecyclerView;
    private List<GeoObject> mGeoObjects = new ArrayList<>();

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < GeoObject.ObjectImages.length; i++) {
            mGeoObjects.add(new GeoObject(GeoObject.ObjectNames[i], GeoObject.ObjectImages[i]));
        }

        goRecyclerView = findViewById(R.id.recyclerView);

        final RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false); //2 cells per row
        goRecyclerView.setLayoutManager(mLayoutManager);

        goAdapter = new GeoObjectAdapter(this, mGeoObjects);
        goRecyclerView.setAdapter(goAdapter);

        imageView = findViewById(R.id.geoImageView);

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
                        int position = (viewHolder.getAdapterPosition());
                        String Europe = inEurope(position);
                        if (swipeDir == ItemTouchHelper.LEFT) {
                            Toast.makeText(MainActivity.this, Europe + GeoObject.ObjectNames[position].toString() + ")", Toast.LENGTH_SHORT).show();
                            result(viewHolder, "left", position);
                        } else {
                            Toast.makeText(MainActivity.this, Europe + GeoObject.ObjectNames[position].toString() + ")", Toast.LENGTH_SHORT).show();
                            result(viewHolder, "right", position);
                        }
                        goAdapter.notifyItemChanged(position);
                    }
                };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(goRecyclerView);
    }

    // Swipe left for in Europe and right for not Europe
    // If the result is correct, imageview becomes green, if not, imageview becomes red.
    private void result(RecyclerView.ViewHolder viewHolder, String direction, int position) {
        if (direction == "left") {
            Log.d("left", "result: swipe leftttt");
            if (Boolean.TRUE.equals(GeoObject.inEurope[position])) {
                viewHolder.itemView.setBackgroundColor(Color.parseColor("#1EB025"));
            } else {
                viewHolder.itemView.setBackgroundColor(Color.parseColor("#FF0000"));
            }
        } else if (direction == "right") {
            Log.d("left", "result: swipe leftttt");
            if (Boolean.FALSE.equals(GeoObject.inEurope[position])) {
                viewHolder.itemView.setBackgroundColor(Color.parseColor("#1EB025"));
            } else {
                viewHolder.itemView.setBackgroundColor(Color.parseColor("#FF0000"));
            }
        }
    }

    private String inEurope(int position) {
        String messageEurope;
        if (Boolean.TRUE.equals(GeoObject.inEurope[position])) {
            messageEurope = "Dit is in Europa (";
        } else {
            messageEurope = "Dit is buiten Europa (";
        }
        return messageEurope;
    }
}