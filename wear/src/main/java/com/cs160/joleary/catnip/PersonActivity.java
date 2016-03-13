package com.cs160.joleary.catnip;

import android.os.Bundle;
import android.app.Activity;
import android.support.wearable.view.GridViewPager;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by dradding on 3/12/16.
 */
public class PersonActivity extends Activity{

    private List<PersonList> mPersonLists = new ArrayList<PersonList>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picker);
        //SET DATA HERE.
    }

    public static class PersonList {

        private List<String> mName;
        private List<String> mParty;

        public PersonList(List<String> name, List<String> party) {
            mName = name;
            mParty = party;
        }

        public String getName(int page) {
            return mName.get(page);
        }

        public String getParty(int page) {
            return mParty.get(page);
        }

        public int getPageCount() {
            return mName.size() + 1;
        }
    }

}
