 /*
  * Copyright (C) 2019 Grow Solution
  *
  * Licensed under the Apache License, Version 2.0 (the "License");
  * you may not use this file except in compliance with the License.
  * You may obtain a copy of the License at
  *
  *      http://www.apache.org/licenses/LICENSE-2.0
  *
  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
  */

/**
 * This is a part of the Help Activity.
 */
 package com.musiktok.musictok.Main_Menu;

 import android.os.Bundle;
 import android.os.Handler;
 import android.util.Log;

 import androidx.appcompat.app.AppCompatActivity;
 import androidx.viewpager.widget.ViewPager;


 import com.musiktok.musictok.viewpagerindicator.CirclePageIndicator;
 import com.musiktok.videoapp.R;

 import java.util.ArrayList;
 import java.util.Timer;
 import java.util.TimerTask;


 /**
  * Created by grow solution on 14/05/2019.
  * instruct app usage using help activity to user.
  */
 public class WelComeActivity extends AppCompatActivity {
     private ViewPager mPager;
     private static int currentPage = 0;
     private static int NUM_PAGES = 0;
     private ArrayList<WelComeImageModel> helpImageModelArrayList;
     private int[] myImageList = new int[]{R.drawable.img_help1, R.drawable.img_help2,
             R.drawable.img_help3};
     private String[] title = new String[]{"Welcome to MusikTok","Share With MusikTok","Be a Star With MusikTok"};
     private String[] subTitle = new String[]{"Show Your talent to the whole \n community of musikTok",
             "be a star and celebrate your moment \n  with friends","Whatver your talent show it on  \n our musikTok"};
     private String[] stringlist = new String[]{"STEP 1 OF 4", "STEP 2 OF 4",
             "STEP 3 OF 4", " STEP 4 OF 4"};

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_welcome);
         helpImageModelArrayList = new ArrayList<>();
         helpImageModelArrayList = populateList();
         init();
     }

     private ArrayList<WelComeImageModel> populateList() {
         ArrayList<WelComeImageModel> list = new ArrayList<>();
         for (int i = 0; i < 3; i++) {
             WelComeImageModel helpImageModel = new WelComeImageModel();
             helpImageModel.setImage_drawable(myImageList[i]);
             helpImageModel.setImage_text(stringlist[i]);
             helpImageModel.setTitle(title[i]);
             helpImageModel.setSubTitle(subTitle[i]);
             Log.w("msg", "text " + stringlist[i]);
             list.add(helpImageModel);
         }
         return list;
     }

     private void init() {
         mPager = findViewById(R.id.pager);
         mPager.setAdapter(new SlidingImage_Adapter(this, helpImageModelArrayList, stringlist));
         CirclePageIndicator indicator = findViewById(R.id.indicator);
         indicator.setViewPager(mPager);
         final float density = getResources().getDisplayMetrics().density;
         indicator.setRadius(5 * density);
         NUM_PAGES = helpImageModelArrayList.size();
         final Handler handler = new Handler();
         final Runnable Update = new Runnable() {
             public void run() {
                 if (currentPage == NUM_PAGES) {
                     currentPage = 0;
                 }
                 mPager.setCurrentItem(currentPage++, true);
             }
         };
         Timer swipeTimer = new Timer();
         swipeTimer.schedule(new TimerTask() {
             @Override
             public void run() {
                 handler.post(Update);
             }
         }, 2000, 2000);
         indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
             @Override
             public void onPageSelected(int position) {
                 currentPage = position;
             }

             @Override
             public void onPageScrolled(int pos, float arg1, int arg2) {
             }

             @Override
             public void onPageScrollStateChanged(int pos) {
             }
         });
     }
 }