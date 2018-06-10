package xkhachat.android.xml.mendelu.cz.androidproject.gallery;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import xkhachat.android.xml.mendelu.cz.androidproject.R;

public class ViewPictures extends AppCompatActivity {

    private String[] imageUrls = new String[]{
            "https://akela.mendelu.cz/~xkhachat/android/img/tbilisi.jpg",
            "https://akela.mendelu.cz/~xkhachat/android/img/baku.jpg",
            "https://akela.mendelu.cz/~xkhachat/android/img/yerevan.jpg"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ViewPager viewPager = findViewById(R.id.view_pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(this, imageUrls);
        viewPager.setAdapter(adapter);

    }
}