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
 * This is a part of the Image Slider Helper.
 */
 package com.musiktok.musictok.Main_Menu;


 import android.app.Activity;
 import android.content.Context;
 import android.content.Intent;
 import android.os.Parcelable;
 import android.view.LayoutInflater;
 import android.view.View;
 import android.view.ViewGroup;
 import android.widget.ImageView;
 import android.widget.RelativeLayout;
 import android.widget.TextView;

 import androidx.viewpager.widget.PagerAdapter;

 import com.bumptech.glide.Glide;
 import com.musiktok.videoapp.R;

 import java.util.ArrayList;


 /**
  * Created by grow solution on 14/05/2019.
  */
 public class SlidingImage_Adapter extends PagerAdapter {
     private ArrayList<WelComeImageModel> helpImageModelArrayList;
     private String[] nextbuttonList;
     private LayoutInflater inflater;
     private Context context;
//    private RelativeLayout btnback;


     public SlidingImage_Adapter(Context context, ArrayList<WelComeImageModel> helpImageModelArrayList, String[] textarray,
                                 String[] nextbuttonList) {
         this.context = context;
         this.helpImageModelArrayList = helpImageModelArrayList;
         this.nextbuttonList = nextbuttonList;
         inflater = LayoutInflater.from(context);
     }

     @Override
     public void destroyItem(ViewGroup container, int position, Object object) {
         container.removeView((View) object);
     }

     @Override
     public int getCount() {
         return helpImageModelArrayList.size();
     }

     @Override
     public Object instantiateItem(ViewGroup view, int position) {
         View imageLayout = inflater.inflate(R.layout.item_help_layout, view, false);

         assert imageLayout != null;
         final ImageView imageView = imageLayout
                 .findViewById(R.id.image);
//        TextView text1 = imageLayout.findViewById(R.id.text1);
         TextView title = imageLayout.findViewById(R.id.title);
         TextView subTitle = imageLayout.findViewById(R.id.subTitle);
         RelativeLayout btnskip = imageLayout.findViewById(R.id.btnskip);
         RelativeLayout btncancel = imageLayout.findViewById(R.id.btncancel);
         TextView nextbtn_click = imageLayout.findViewById(R.id.nextbtn_click);
         btnskip.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(context, MainMenuActivity.class);
                 context.startActivity(intent);
                 ((Activity) context).finish();
             }
         });
         btncancel.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(context, MainMenuActivity.class);
                 context.startActivity(intent);
             }
         });
        /*btnback = imageLayout.findViewById(R.id.btnback);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity) context).finish();
            }
        });*/
         Glide.with(context).load(helpImageModelArrayList.get(position).getImage_drawable()).into(imageView);
         title.setText(helpImageModelArrayList.get(position).getTitle());
         subTitle.setText(helpImageModelArrayList.get(position).getSubTitle());
         nextbtn_click.setText(helpImageModelArrayList.get(position).getNextTitle());
//        text1.setText(this.textarray[position]);

         view.addView(imageLayout, 0);

         return imageLayout;
     }

     @Override
     public boolean isViewFromObject(View view, Object object) {
         return view.equals(object);
     }

     @Override
     public void restoreState(Parcelable state, ClassLoader loader) {
     }

     @Override
     public Parcelable saveState() {
         return null;
     }


 }
