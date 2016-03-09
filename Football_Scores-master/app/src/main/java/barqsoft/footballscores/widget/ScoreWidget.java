package barqsoft.footballscores.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.widget.RemoteViews;

import barqsoft.footballscores.MainActivity;
import barqsoft.footballscores.R;

/**
 * Implementation of App Widget functionality.
 */
public class ScoreWidget extends AppWidgetProvider {
    final static public String BRODCAST_MESSAGE = "barqsoft.footballscores.WIDGET_UPDATE_ACTION";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_score_layout);
        //set the click action

        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        views.setOnClickPendingIntent(R.id.widget_bar, pendingIntent);
//        views.setTextViewText(R.id.appwidget_text, widgetText);
        //set the array adapter
        Intent brodcastIntent = new Intent(context, ScoreWidgetService.class);
        // Add the app widget ID to the intent extras.
        brodcastIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        brodcastIntent.setData(Uri.parse(brodcastIntent.toUri(Intent.URI_INTENT_SCHEME)));

        //et the left button
        Intent leftintent=new Intent(WidgetLeftButtonReceiver.WidgetLeftButtonReceiver_ACTION);
        PendingIntent leftpendingIntent=PendingIntent.getBroadcast(context,0,leftintent,0);
        views.setOnClickPendingIntent(R.id.widget_im_left, leftpendingIntent);

        // set the right bytton
        Intent rightintent=new Intent(WidgetRightButtonReceiver.WidgetRightButtonReceiver_ACTION);
        PendingIntent rightpendingIntent=PendingIntent.getBroadcast(context,0,rightintent,0);
        views.setOnClickPendingIntent(R.id.widget_im_right,rightpendingIntent);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            views.setRemoteAdapter(R.id.widget_listview_score, brodcastIntent);
        }


        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        AppWidgetManager awm = AppWidgetManager.getInstance(context);
        int[] ids = awm.getAppWidgetIds(new ComponentName(context, AppWidgetProvider.class));
        onUpdate(context, awm, ids);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }


}




