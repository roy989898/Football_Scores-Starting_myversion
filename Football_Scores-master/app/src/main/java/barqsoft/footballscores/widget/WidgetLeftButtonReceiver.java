package barqsoft.footballscores.widget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class WidgetLeftButtonReceiver extends BroadcastReceiver {
    public static final String WidgetLeftButtonReceiver_ACTION="barqsoft.footballscores.LEFT_ACTION";
    public WidgetLeftButtonReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.d("WidgetLeftButtonReceiver","click");
    }
}
