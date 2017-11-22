package com.futuzon.opccounter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.futuzon.opccounter.view.opc.OpcCounterFragment;

public class LauncherActivity extends AppCompatActivity {

    // public SpaceTabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_launcher);

        openFragment(R.id.opc_fragment, new OpcCounterFragment());

        /*
        //add the fragments you want to display in a List
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new OpcCounterFragment());
        fragmentList.add(new ComingSoonFragment());
        fragmentList.add(new ComingSoonFragment());

        ViewPager viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.spaceTabLayout);

        //we need the savedInstanceState to get the position
        tabLayout.initialize(viewPager, getSupportFragmentManager(), fragmentList, savedInstanceState);
        */
    }

    /**
     * Shows / opens a fragment using a layout id and the fragment to display
     *
     * @param layoutIdForFragment the layout where to integrate the fragment
     * @param fragment            the fragment to display
     */
    private void openFragment(int layoutIdForFragment, Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(layoutIdForFragment, fragment);
        fragmentTransaction.commit();
    }
}
