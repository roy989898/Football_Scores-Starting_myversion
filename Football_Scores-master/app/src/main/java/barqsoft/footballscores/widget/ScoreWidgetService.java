package barqsoft.footballscores.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.text.SimpleDateFormat;
import java.util.Date;

import barqsoft.footballscores.DatabaseContract;
import barqsoft.footballscores.R;
import barqsoft.footballscores.Utilies;

/**
 * Created by User on 8/3/2016.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class ScoreWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {

        //it is becasue today,i =2
        int i = 2;
        String[] fragmentdates = new String[1];
        Date fragmentdate = new Date(System.currentTimeMillis() + ((i - 2) * 86400000));
        SimpleDateFormat mformat = new SimpleDateFormat("yyyy-MM-dd");
        fragmentdates[0] = mformat.format(fragmentdate);

        Cursor cursor = getContentResolver().query(DatabaseContract.scores_table.buildScoreWithDate(), null, null, fragmentdates, null);
        return new ScoreWidgetListAdapter(getApplicationContext(), cursor);
    }

    private class ScoreWidgetListAdapter implements RemoteViewsService.RemoteViewsFactory {
        public static final int COL_HOME = 3;
        public static final int COL_AWAY = 4;
        public static final int COL_HOME_GOALS = 6;
        public static final int COL_AWAY_GOALS = 7;
        public static final int COL_DATE = 1;
        public static final int COL_LEAGUE = 5;
        public static final int COL_MATCHDAY = 9;
        public static final int COL_ID = 8;
        public static final int COL_MATCHTIME = 2;
        public double detail_match_id = 0;
        Context mcontext;
        Cursor mcursor;

        public ScoreWidgetListAdapter(Context context, Cursor cursor) {
            this.mcontext = context;
            this.mcursor = cursor;

        }

        @Override
        public void onCreate() {

        }

        @Override
        public void onDataSetChanged() {

        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            return mcursor.getCount();
        }

        @Override
        public RemoteViews getViewAt(int position) {
            RemoteViews views = new RemoteViews(getPackageName(), R.layout.widget_listview_iteam);
            bindData(views);
            return views;
        }

        private void bindData(RemoteViews views) {

            String home_name = mcursor.getString(COL_HOME);
            String awat_name = mcursor.getString(COL_AWAY);
            String date = mcursor.getString(COL_MATCHTIME);
            int home_goals = mcursor.getInt(COL_HOME_GOALS);
            int away_goals = mcursor.getInt(COL_AWAY_GOALS);

            String score = Utilies.getScores(home_goals, away_goals);
            views.setTextViewText(R.id.widget_home_name, home_name);
            views.setTextViewText(R.id.widget_away_name, awat_name);
            views.setTextViewText(R.id.widget_data_textview, date);
            views.setTextViewText(R.id.widget_score_textview, score);


        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }
    }


}
