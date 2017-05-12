package com.example.administrator.couldscreen4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static android.content.Intent.getIntent;


/**
 * Created by Administrator on 2017/5/3.
 */

public class JumpedColorSpace extends Fragment {
    View viewJ;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        viewJ = inflater.inflate(R.layout.guide,container,false);
        MainActivity mainActivity = (MainActivity) getActivity();
        //int state = mainActivity.getstate();


        viewJ.findViewById(R.id.menu_homepage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.change(1);
            }
        });
        viewJ.findViewById(R.id.title_guide1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetSelected(1);
            }
        });
        viewJ.findViewById(R.id.title_guide2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetSelected(2);
            }
        });
        viewJ.findViewById(R.id.title_guide3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetSelected(3);
            }
        });
        viewJ.findViewById(R.id.title_guide4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetSelected(4);
            }
        });
        return viewJ;
    }
    public void SetSelected(int x){
        viewJ.findViewById(R.id.title_guide1).setSelected(false);
        viewJ.findViewById(R.id.imageView7).setSelected(false);
        viewJ.findViewById(R.id.title_guide2).setSelected(false);
        viewJ.findViewById(R.id.imageView8).setSelected(false);
        viewJ.findViewById(R.id.title_guide3).setSelected(false);
        viewJ.findViewById(R.id.imageView9).setSelected(false);
        viewJ.findViewById(R.id.title_guide4).setSelected(false);
        viewJ.findViewById(R.id.imageView10).setSelected(false);
        switch (x){
            case 1:
                viewJ.findViewById(R.id.title_guide1).setSelected(true);
                viewJ.findViewById(R.id.imageView7).setSelected(true);
                break;
            case 2:
                viewJ.findViewById(R.id.title_guide2).setSelected(true);
                viewJ.findViewById(R.id.imageView8).setSelected(true);
                break;
            case 3:
                viewJ.findViewById(R.id.title_guide3).setSelected(true);
                viewJ.findViewById(R.id.imageView9).setSelected(true);
                break;
            case 4:
                viewJ.findViewById(R.id.title_guide4).setSelected(true);
                viewJ.findViewById(R.id.imageView10).setSelected(true);
                break;
        }
    }

}
