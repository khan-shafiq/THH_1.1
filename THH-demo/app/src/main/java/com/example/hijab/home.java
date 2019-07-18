package com.example.hijab;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class home extends Fragment {


    public home() {
        // Required empty public constructor
    }

    ////////BannerSlider
    private ViewPager bannerSliderViewPager;
    private List<SliderModel> sliderModelList;
    private int currentPage = 2;
    private Timer timer;
    final private long DELAY_TIME = 3000;
    final private  long PERIOD_TIME = 3000;

    ////////BannerSlder
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //////BannerSlider

        bannerSliderViewPager = view.findViewById(R.id.banner_viewpager);
        sliderModelList = new ArrayList<SliderModel>();
        sliderModelList.add(new SliderModel(R.drawable.cream));
        sliderModelList.add(new SliderModel(R.drawable.ic_gift));

        sliderModelList.add(new SliderModel(R.drawable.a));
        sliderModelList.add(new SliderModel(R.drawable.b));
        sliderModelList.add(new SliderModel(R.drawable.c));
        sliderModelList.add(new SliderModel(R.drawable.cream));
        sliderModelList.add(new SliderModel(R.drawable.ic_gift));

        sliderModelList.add(new SliderModel(R.drawable.a));
        sliderModelList.add(new SliderModel(R.drawable.b));


        SilderAdapter sliderAdapter = new SilderAdapter(sliderModelList);
        bannerSliderViewPager.setAdapter(sliderAdapter);
        bannerSliderViewPager.setClipToPadding(false);
        bannerSliderViewPager.setPageMargin(20);
        ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                currentPage = i;
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                if(i==ViewPager.SCROLL_STATE_IDLE){
                    pageLooper();
                }
            }
        };
        bannerSliderViewPager.addOnPageChangeListener(onPageChangeListener);
        startBannerSlideShow();

        bannerSliderViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                pageLooper();
                stopBannerSlideShow();
                if(event.getAction() == MotionEvent.ACTION_UP){
                    startBannerSlideShow();
                }
                return false;
            }
        });

        //////BannerSlider

        return view;
    }

        //////BannerSlider

    private void pageLooper(){
        if(currentPage == sliderModelList.size()-2)
        {
            currentPage = 2;
            bannerSliderViewPager.setCurrentItem(currentPage,false);
        }

        if(currentPage == 1)
        {
            currentPage = sliderModelList.size()-3;
            bannerSliderViewPager.setCurrentItem(currentPage,false);
        }

    }
    private void startBannerSlideShow(){
        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            @Override
            public void run() {
               if(currentPage >= sliderModelList.size()){
                   currentPage = 1;
               }
              bannerSliderViewPager.setCurrentItem(currentPage++,true);
            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        } ,DELAY_TIME,PERIOD_TIME);
    }

    private void stopBannerSlideShow(){
        timer.cancel();
    }



    //////BannnerSlider

}
