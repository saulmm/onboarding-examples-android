/*
 * Copyright (C) 2017
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
package com.example.saulmm.splashes;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("WeakerAccess")
public class ModelItem {
    public final String author;
    public final int imgId;

    public  ModelItem(String author, int imgId) {
        this.author = author;
        this.imgId = imgId;
    }

    static List<ModelItem> fakeItems() {
        ArrayList<ModelItem> itemsList = new ArrayList<>();
        itemsList.add(new ModelItem("Prasad A.", R.drawable.img_prasad));
        itemsList.add(new ModelItem("Besim  Mazhiqi", R.drawable.img_besim));
        itemsList.add(new ModelItem("Mark Bridger", R.drawable.img_mark));
        itemsList.add(new ModelItem("William Mevissem", R.drawable.img_william));
        itemsList.add(new ModelItem("Darren J Bennet", R.drawable.img_darren));
        return itemsList;
    }
}