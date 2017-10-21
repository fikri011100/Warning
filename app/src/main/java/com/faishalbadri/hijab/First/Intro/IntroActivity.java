package com.faishalbadri.hijab.First.Intro;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.faishalbadri.hijab.First.HomeActivity;
import com.faishalbadri.hijab.R;

public class IntroActivity extends AppCompatActivity {

  @BindView(R.id.btn_next)
  Button btnNext;
  @BindView(R.id.btn_skip)
  Button btnSkip;
  private ViewPager viewPager;
  private MyViewPagerAdapter myViewPagerAdapter;
  private LinearLayout dotsLayout;
  private TextView[] dots;
  private int[] layouts;
  private com.faishalbadri.hijab.First.Intro.prefManager prefManager;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    prefManager = new prefManager(this);
    if (!prefManager.isFirstTimeLaunch()) {
      launchAwalScreen();
      finish();
    }


    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);

    setContentView(R.layout.activity_intro);
    ButterKnife.bind(this);

    viewPager = (ViewPager) findViewById(R.id.view_pager);
    dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);

    layouts = new int[]{
        R.layout.intro1,
        R.layout.intro2,
        R.layout.intro3,
    };

    addBottomDots(0);

    changeStatusBarColor();

    myViewPagerAdapter = new MyViewPagerAdapter();
    viewPager.setAdapter(myViewPagerAdapter);
    viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

  }

  private void addBottomDots(int currentPage) {
    dots = new TextView[layouts.length];

    int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
    int[] colorsInActive = getResources().getIntArray(R.array.array_dot_inactive);

    dotsLayout.removeAllViews();
    for (int i = 0; i < dots.length; i++) {
      dots[i] = new TextView(this);
      dots[i].setText(Html.fromHtml("&#8226;"));
      dots[i].setTextSize(35);
      dots[i].setTextColor(colorsInActive[currentPage]);
      dotsLayout.addView(dots[i]);
    }

    if (dots.length > 0)
      dots[currentPage].setTextColor(colorsActive[currentPage]);
  }

  private int getItem(int i) {
    return viewPager.getCurrentItem() + i;
  }

  private void launchAwalScreen() {
    prefManager.setFirstTimeLaunch(false);
    startActivity(new Intent(IntroActivity.this, HomeActivity.class));
    finish();
  }


  ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

    @Override
    public void onPageSelected(int position) {
      addBottomDots(position);

      // changing the next button text 'NEXT' / 'GOT IT'
      if (position == layouts.length - 1) {
        // last page. make button text to GOT IT
        btnNext.setText(getString(R.string.start));
        btnSkip.setVisibility(View.GONE);
      } else {
        // still pages are left
        btnNext.setText(getString(R.string.next));
        btnSkip.setVisibility(View.VISIBLE);
      }
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }

    @Override
    public void onPageScrollStateChanged(int arg0) {

    }
  };

  private void changeStatusBarColor() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = getWindow();
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
//        }

  }

  @OnClick(R.id.btn_next)
  public void onBtnNextClicked() {
    int current = getItem(+1);
    if (current < layouts.length) {
      viewPager.setCurrentItem(current);
    } else {
      launchAwalScreen();
    }

  }

  @OnClick(R.id.btn_skip)
  public void onBtnSkipClicked() {
    launchAwalScreen();
  }

  public class MyViewPagerAdapter extends PagerAdapter {
    private LayoutInflater layoutInflater;

    public MyViewPagerAdapter() {
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
      layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

      View view = layoutInflater.inflate(layouts[position], container, false);
      container.addView(view);

      return view;
    }

    @Override
    public int getCount() {
      return layouts.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object obj) {
      return view == obj;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
      View view = (View) object;
      container.removeView(view);
    }
  }

}

