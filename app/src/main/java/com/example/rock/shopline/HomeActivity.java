package com.example.rock.shopline;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.rock.shopline.DataTypes.BookDescription;
import com.example.rock.shopline.Fragments.ChatFragment;
import com.example.rock.shopline.Fragments.HomeFragment;
import com.example.rock.shopline.Fragments.ProfileFragment;
import com.example.rock.shopline.RecyclerViews.HomeBookRecyclerViewAdapter;
import com.example.rock.shopline.constants.Constants;
import com.example.rock.shopline.data.AddBook;
import com.example.rock.shopline.data.AuthService;
import com.example.rock.shopline.data.GetBook;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
  HomeFragment homeFragment;
    ImageButton Add;
    ImageView Home, Profile, Chat;
     LinearLayout linearLayout;
     ViewPager viewPager;
     ProfileFragment profileFragment;
     ChatFragment chatFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frag_home);
        homeFragment = new HomeFragment();
        profileFragment = new ProfileFragment();
        chatFragment = new ChatFragment();
        linearLayout = findViewById(R.id.linearhome);
        viewPager = findViewById(R.id.HomePager);

        Home = findViewById(R.id.HomeButton);
        Profile = findViewById(R.id.Profile);
        Chat = findViewById(R.id.Chat);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId() == R.id.Profile)
                {
                    viewPager.setCurrentItem(0);
                }
                else if(view.getId() == R.id.HomeButton)
                {
                    viewPager.setCurrentItem(1);
                }
                else viewPager.setCurrentItem(2);
            }
        };

        Home.setOnClickListener(listener);
        Profile.setOnClickListener(listener);
        Chat.setOnClickListener(listener);


        Add = findViewById(R.id.Add);
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AddBookActivity.class);
                startActivity(intent);

            }
        });

        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        viewPager.setCurrentItem(1);

        SmartTabLayout viewPagerTab =  findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(viewPager);


    }


    public interface ShowBooks{
        void success(boolean success);

    }
    public LinearLayout getLinearLayout() {
        return linearLayout;
    }

    public class ViewPagerAdapter extends FragmentPagerAdapter{

        public ViewPagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            if(position == 0)
            {
                return profileFragment;
            }
            else if(position == 1)
            {
                return homeFragment;
            }
            else
                return chatFragment;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }



}
