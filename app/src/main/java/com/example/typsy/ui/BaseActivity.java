package com.example.typsy.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.typsy.R;
import com.example.typsy.Util;
import com.example.typsy.ui.screens.about.AboutActivity;
import com.example.typsy.ui.screens.conversionList.ConversionListFragment;

/**
 * Created by gravity on 10/29/17.
 */

public class BaseActivity extends AppCompatActivity {

    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);

        mFragmentManager = getSupportFragmentManager();

        ConversionListFragment fragment = (ConversionListFragment) mFragmentManager.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = ConversionListFragment.newInstance();
            Util.addFragmentToActivity(mFragmentManager, fragment);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_about) {
            // launch about activity
            startActivity(new Intent(this, AboutActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
