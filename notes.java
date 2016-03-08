//Today tomorrow yester day ...... each PagerFragment


//in each PagerFragment has a MainScreenFragment

//each MainScreenFragment has a kist view,it show the score and team



use DatabaseContract.scores_table.buildScoreWithDate() to query the data


   return new CursorLoader(getActivity(),DatabaseContract.scores_table.buildScoreWithDate(),
                null,null,fragmentdate,null);

DatabaseContract.scores_table.buildScoreWithDate() create :

"content://barqsoft.footballscores/date"


				
need the fragmentdate use in MainScreenFragment.

fragmentdate is come from setFragmentDate(String date)

setFragmentDate call at PageFragment


Date fragmentdate = new Date(System.currentTimeMillis() + ((i - 2) * 86400000));
            SimpleDateFormat mformat = new SimpleDateFormat("yyyy-MM-dd");
            viewFragments[i] = new MainScreenFragment();
            viewFragments[i].setFragmentDate(mformat.format(fragmentdate));
			
			
//query:

return new CursorLoader(getActivity(),DatabaseContract.scores_table.buildScoreWithDate(),
                null,null,fragmentdate,null);