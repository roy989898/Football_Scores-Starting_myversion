package barqsoft.footballscores.widget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import barqsoft.footballscores.R;

public class WidgetRightButtonReceiver extends BroadcastReceiver {
    public static final String WidgetRightButtonReceiver_ACTION = "barqsoft.footballscores.RIGHT_ACTION";

    public WidgetRightButtonReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        //get the current day
        Log.d("widget","Receiver_onReciece");
        SharedPreferences sp = context.getSharedPreferences(context.getString(R.string.sharedpreference_name), Context.MODE_PRIVATE);
        int day = sp.getInt(context.getString(R.string.sharedpreference_day_key), 2);
        day=increaseTheDay(day);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(context.getString(R.string.sharedpreference_day_key),day);
        editor.commit();


        Intent brodcastIntent = new Intent(ScoreWidget.BRODCAST_MESSAGE);
        context.sendBroadcast(brodcastIntent);//send out the brodcast ,ScoreWidget will receive,and load the day from sharedPreference again

    }

    private int increaseTheDay(int i) {
        //day is 0-4,can't >=5
        int day = i + 1;
        if (day >= 5) {
            return 0;
        } else {
            return day;
        }


    }
}