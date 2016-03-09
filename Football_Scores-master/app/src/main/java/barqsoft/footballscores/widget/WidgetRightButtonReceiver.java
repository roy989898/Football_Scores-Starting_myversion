package barqsoft.footballscores.widget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class WidgetRightButtonReceiver extends BroadcastReceiver {
    public static final String WidgetRightButtonReceiver_ACTION="barqsoft.footballscores.RIGHT_ACTION";
    public WidgetRightButtonReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.d("WidgetLeftButtonReceiver", "Rclick");
    }
}
