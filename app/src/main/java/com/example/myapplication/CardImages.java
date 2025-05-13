package com.example.myapplication;

import java.util.HashMap;
import java.util.Map;

public class CardImages {
    public static final Map<String, Integer> cardImageMap = new HashMap<>();

    static {
        cardImageMap.put("ha", R.drawable.ha);
        cardImageMap.put("h2", R.drawable.h2);
        cardImageMap.put("h3", R.drawable.h3);
        cardImageMap.put("h4", R.drawable.h4);
        cardImageMap.put("h5", R.drawable.h5);
        cardImageMap.put("h6", R.drawable.h6);
        cardImageMap.put("h7", R.drawable.h7);
        cardImageMap.put("h8", R.drawable.h8);
        cardImageMap.put("h9", R.drawable.h9);
        cardImageMap.put("h10", R.drawable.h10);
        cardImageMap.put("hj", R.drawable.hj);
        cardImageMap.put("hq", R.drawable.hq);
        cardImageMap.put("hk", R.drawable.hk);
        cardImageMap.put("da", R.drawable.da);
        cardImageMap.put("d2", R.drawable.d2);
        cardImageMap.put("d3", R.drawable.d3);
        cardImageMap.put("d4", R.drawable.d4);
        cardImageMap.put("d5", R.drawable.d5);
        cardImageMap.put("d6", R.drawable.d6);
        cardImageMap.put("d7", R.drawable.d7);
        cardImageMap.put("d8", R.drawable.d8);
        cardImageMap.put("d9", R.drawable.d9);
        cardImageMap.put("d10", R.drawable.d10);
        cardImageMap.put("dj", R.drawable.dj);
        cardImageMap.put("dq", R.drawable.dq);
        cardImageMap.put("dk", R.drawable.dk);
        cardImageMap.put("sa", R.drawable.sa);
        cardImageMap.put("s2", R.drawable.s2);
        cardImageMap.put("s3", R.drawable.s3);
        cardImageMap.put("s4", R.drawable.s4);
        cardImageMap.put("s5", R.drawable.s5);
        cardImageMap.put("s6", R.drawable.s6);
        cardImageMap.put("s7", R.drawable.s7);
        cardImageMap.put("s8", R.drawable.s8);
        cardImageMap.put("s9", R.drawable.s9);
        cardImageMap.put("s10", R.drawable.s10);
        cardImageMap.put("sj", R.drawable.sj);
        cardImageMap.put("sq", R.drawable.sq);
        cardImageMap.put("sk", R.drawable.sk);
        cardImageMap.put("ca", R.drawable.ca);
        cardImageMap.put("c2", R.drawable.c2);
        cardImageMap.put("c3", R.drawable.c3);
        cardImageMap.put("c4", R.drawable.c4);
        cardImageMap.put("c5", R.drawable.c5);
        cardImageMap.put("c6", R.drawable.c6);
        cardImageMap.put("c7", R.drawable.c7);
        cardImageMap.put("c8", R.drawable.c8);
        cardImageMap.put("c9", R.drawable.c9);
        cardImageMap.put("c10", R.drawable.c10);
        cardImageMap.put("cj", R.drawable.cj);
        cardImageMap.put("cq", R.drawable.cq);
        cardImageMap.put("ck", R.drawable.ck);
        cardImageMap.put("gray_back", R.drawable.gray_back);

    }

    public static int getCardResourceId(String cardName) {
        Integer resId = cardImageMap.get(cardName.toLowerCase());
        if (resId != null) {
            return resId;
        } else {
            return R.drawable.gray_back;
        }
    }
}
