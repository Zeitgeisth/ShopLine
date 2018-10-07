package com.example.rock.shopline;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.rock.shopline.DataTypes.BookDescription;
import com.example.rock.shopline.Fragments.HomeFragment;
import com.example.rock.shopline.RecyclerViews.HomeBookRecyclerViewAdapter;
import com.example.rock.shopline.constants.Constants;
import com.example.rock.shopline.data.AddBook;
import com.example.rock.shopline.data.AuthService;
import com.example.rock.shopline.data.GetBook;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
  HomeFragment homeFragment;
    ImageButton Add;
     LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frag_home);
        homeFragment = new HomeFragment();
        linearLayout = findViewById(R.id.linearhome);

        //linearLayout.setVisibility(View.GONE);


        Add = findViewById(R.id.Add);
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AddBookActivity.class);
                startActivity(intent);

            }
        });
        getSupportFragmentManager().beginTransaction().replace(R.id.HomesFragment, homeFragment).commit();
    }

    public interface ShowBooks{
        void success(boolean success);

    }
    public LinearLayout getLinearLayout() {
        return linearLayout;
    }
}
