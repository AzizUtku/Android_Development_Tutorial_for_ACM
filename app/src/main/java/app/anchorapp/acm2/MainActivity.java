package app.anchorapp.acm2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import app.anchorapp.acm2.fragments.HomeFragment;
import app.anchorapp.acm2.fragments.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    private Fragment mCurrentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final HomeFragment homeFragment = new HomeFragment();
        final ProfileFragment profileFragment = new ProfileFragment();

        mCurrentFragment = homeFragment;

        setFragment(homeFragment, "home");
        BottomNavigationView bottomNavigationView = findViewById(R.id.main_bnv);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.main_menu_item_home:
                        switchFragment(homeFragment, "home");
                        break;
                    case R.id.main_menu_item_profile:
                        switchFragment(profileFragment, "profile");
                        break;
                }
                return false;
            }
        });
    }

    private void setFragment(Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment, tag);
        fragmentTransaction.commit();
    }

    private void switchFragment(Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (getSupportFragmentManager().findFragmentByTag(tag) == null) {
            fragmentTransaction.add(fragment, tag);
        }
        fragmentTransaction.hide(mCurrentFragment);
        fragmentTransaction.show(fragment);
        mCurrentFragment = fragment;
        fragmentTransaction.commit();
    }
}
