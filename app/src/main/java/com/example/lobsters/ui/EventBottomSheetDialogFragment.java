package com.example.lobsters.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.example.lobsters.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class EventBottomSheetDialogFragment extends BottomSheetDialogFragment {


    public static EventBottomSheetDialogFragment newInstance(int num) {
        //MyDialogFragment f = new MyDialogFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
       // args.pu
        args.putInt("num", num);
        //f.setArguments(args);

        //return f;

        return null;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.bottomsheet_event, container,
                false);

//        AppCompatImageButton button = root.findViewById(R.id.)


        return root;

    }
}
