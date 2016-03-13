//package com.cs160.joleary.catnip;
//
//import android.app.Fragment;
//import android.app.FragmentManager;
//import android.content.Context;
//import android.graphics.drawable.Drawable;
//import android.support.wearable.view.CardFragment;
//import android.support.wearable.view.FragmentGridPagerAdapter;
//import android.view.Gravity;
//import java.util.List;
//
///**
// * Created by dradding on 3/12/16.
// */
//public class PersonAdapter extends FragmentGridPagerAdapter {
//
//    private final Context mContext;
//    private List<String> mRows;
//    private static final float MAXIMUM_CARD_EXPANSION_FACTOR = 3.0f;
//
//    public PersonAdapter(Context context, List<String> people, FragmentManager fm) {
//        super(fm);
//        mContext = context;
//        mRows = people;
//    }
//
//    @Override
//    public Fragment getFragment(int row, int column) {
//        PersonActivity.PersonList personList = mRows.get(row);
//        CardFragment fragment = CardFragment.create(personList.getName(column), personList.getParty(column));
//        fragment.setCardGravity(Gravity.BOTTOM);
//        fragment.setExpansionEnabled(true);
//        fragment.setExpansionDirection(CardFragment.EXPAND_DOWN);
//        fragment.setExpansionFactor(MAXIMUM_CARD_EXPANSION_FACTOR);
//        return fragment;
//    }
//
//    @Override
//    public int getRowCount() {
//        return mRows.size();
//    }
//
//    @Override
//    public int getColumnCount(int row) {
//        return mRows.get(row).getPageCount();
//    }
//
////    private static class Page {
////        // static resources
////        int nameRes;
////        int partyRes;
////    }
////
////    private final Page[][] PAGES = { ... };
//
//}
