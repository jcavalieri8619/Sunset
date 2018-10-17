package com.bignerdranch.android.sunset;


import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SunsetFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SunsetFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View mSceneView;
    View mSkyView;
    View mSunView;


    public SunsetFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SunsetFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SunsetFragment newInstance(String param1, String param2) {
        SunsetFragment fragment = new SunsetFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mSceneView = inflater.inflate(R.layout.fragment_sunset, container, false);

        mSkyView = mSceneView.findViewById(R.id.sky);
        mSunView = mSceneView.findViewById(R.id.sun);

        mSunView.setOnClickListener((v)->{

            startAnimation();

        });

        return mSceneView;
    }


    private void startAnimation() {
        float startSunY = mSunView.getTop();

        // we're moving sun down so that its Top is at bottom of
        // its parent (skyView) so we need to move sun down by
        // skyView's Height --> then Top of sun will be at bottom of
        // skyview
        float endSunY = mSkyView.getHeight();

        ObjectAnimator heightAnimator = ObjectAnimator
                .ofFloat(mSunView, "y", startSunY, endSunY);


        heightAnimator.setInterpolator(new AccelerateInterpolator());

        heightAnimator.setDuration(3000);

        ObjectAnimator sunsetSkyAnimator = ObjectAnimator.ofArgb(mSkyView, "backgroundColor",
                getActivity().getColor(R.color.blue_sky), getActivity().getColor(R.color.sunset_sky));

        sunsetSkyAnimator.setDuration(3000);
        sunsetSkyAnimator.setEvaluator(new ArgbEvaluator());


        ObjectAnimator nightSkyAnimator = ObjectAnimator.ofInt(mSkyView, "backgroundColor",
                getActivity().getColor(R.color.sunset_sky),
                getActivity().getColor(R.color.night_sky));


        nightSkyAnimator.setDuration(1500);
        nightSkyAnimator.setEvaluator(new ArgbEvaluator());


        AnimatorSet animatorSet = new AnimatorSet();


        animatorSet.play(heightAnimator)
                .with(sunsetSkyAnimator)
                .before(nightSkyAnimator);

        animatorSet.start();

//        sunsetSkyAnimator.setRepeatCount(ValueAnimator.INFINITE);
//        heightAnimator.setRepeatCount(ValueAnimator.INFINITE);



//        sunsetSkyAnimator.start();
//
//        heightAnimator.start();



    }

}
