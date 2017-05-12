package com.example.administrator.couldscreen4;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017/5/12.
 */

public class ListSchool extends Fragment {
    View viewL;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        viewL = inflater.inflate(R.layout.list_school,container,false);
        viewL.findViewById(R.id.menu_homepage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.change(1);
            }
        });
        viewL.findViewById(R.id.guide1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetSelected(1);
            }
        });
        viewL.findViewById(R.id.guide2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetSelected(2);
            }
        });
        viewL.findViewById(R.id.guide3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetSelected(3);
            }
        });
        viewL.findViewById(R.id.guide4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetSelected(4);
            }
        });
        viewL.findViewById(R.id.guide5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetSelected(5);
            }
        });
        return viewL;

    }
    public void SetSelected(int x){
        viewL.findViewById(R.id.guide1).setSelected(false);
        viewL.findViewById(R.id.underline1).setSelected(false);
        viewL.findViewById(R.id.guide2).setSelected(false);
        viewL.findViewById(R.id.underline2).setSelected(false);
        viewL.findViewById(R.id.guide3).setSelected(false);
        viewL.findViewById(R.id.underline3).setSelected(false);
        viewL.findViewById(R.id.guide4).setSelected(false);
        viewL.findViewById(R.id.underline4).setSelected(false);
        viewL.findViewById(R.id.guide5).setSelected(false);
        viewL.findViewById(R.id.underline5).setSelected(false);
        switch (x){
            case 1:
                viewL.findViewById(R.id.guide1).setSelected(true);
                viewL.findViewById(R.id.underline1).setSelected(true);
                break;
            case 2:
                viewL.findViewById(R.id.guide2).setSelected(true);
                viewL.findViewById(R.id.underline2).setSelected(true);
                break;
            case 3:
                viewL.findViewById(R.id.guide3).setSelected(true);
                viewL.findViewById(R.id.underline3).setSelected(true);
                break;
            case 4:
                viewL.findViewById(R.id.guide4).setSelected(true);
                viewL.findViewById(R.id.underline4).setSelected(true);
                break;
            case 5:
                viewL.findViewById(R.id.guide5).setSelected(true);
                viewL.findViewById(R.id.underline5).setSelected(true);
                break;
        }
    }
}
