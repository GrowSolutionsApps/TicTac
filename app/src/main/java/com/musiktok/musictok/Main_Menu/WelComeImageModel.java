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
package com.musiktok.musictok.Main_Menu;
 /**
  * Created by grow solution on 14/05/2019.
  */

public class WelComeImageModel {

    private int image_drawable;
    private String image_text;
    private String title;
    private String subTitle;

    public int getImage_drawable() {
        return image_drawable;
    }

    public void setImage_drawable(int image_drawable) {
        this.image_drawable = image_drawable;
    }

    public String getImage_text() {
        return image_text;
    }

    public void setImage_text(String image_text) {
        this.image_text = image_text;
    }

     public String getTitle() {
         return title;
     }

     public void setTitle(String title) {
         this.title = title;
     }

     public String getSubTitle() {
         return subTitle;
     }

     public void setSubTitle(String subTitle) {
         this.subTitle = subTitle;
     }
 }