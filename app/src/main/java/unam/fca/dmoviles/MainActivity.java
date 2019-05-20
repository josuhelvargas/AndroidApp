package unam.fca.dmoviles;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, IWorkerListener{


    private SwipeRefreshLayout mSwipeRefreshLayout;
    private static String OPEN_WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather?id=3530597&&units=metric&appid=aab43df52f8564004b98f9045f76ee5e";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_main);


        mSwipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        try {

            mSwipeRefreshLayout.setRefreshing(true);
            new Worker(MainActivity.this).execute(new URL(OPEN_WEATHER_URL));


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void setDrawable(ImageView iv, String drawableName) {

        int drawable = getApplicationContext().getResources().getIdentifier(drawableName, "drawable", getPackageName());
        if (iv != null) {
            iv.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), drawable));
        }

    }


    @Override
    public void onRefresh() {
        try {
            new Worker(MainActivity.this).execute(new URL(OPEN_WEATHER_URL));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void onNetworkSuccess(JSONObject jsonObject) {

        try {
            JSONArray weatherArray = jsonObject.getJSONArray("weather");
            JSONObject weather = weatherArray.getJSONObject(0);
            JSONObject main = jsonObject.getJSONObject("main");

            String temp = main.getString("temp");
            String icon = weather.getString("icon");
            setValues(icon, temp);


        } catch (Exception e) {
            e.printStackTrace();
        }

        mSwipeRefreshLayout.setRefreshing(false);
    }

    private void setValues(String iconValue, String tempValue) {
        final TextView tv = findViewById(R.id.textView);

        String temp = String.format(getResources().getString(R.string.temp_text),tempValue);
        tv.setText(temp);

        ImageView iv = findViewById(R.id.appCompatImageView);
        setDrawable(iv, "ic_w" + iconValue);
    }

    @Override
    public void onNetworkError(String error) {

        mSwipeRefreshLayout.setRefreshing(false);

        Snackbar snackbar = Snackbar.make(mSwipeRefreshLayout, error, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

}
