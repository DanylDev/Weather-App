package com.incode_it.incode8.weather.ui.three_days;


import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.incode8.weather.R;
import com.incode_it.incode8.weather.di.component.ActivityComponent;
import com.incode_it.incode8.weather.models.daily_model.Daily;
import com.incode_it.incode8.weather.models.daily_model.DailyModelUi;
import com.incode_it.incode8.weather.adapter.Weather.DailyWeatherRecyclerAdapter;
import com.incode_it.incode8.weather.ui.base.BaseFragment;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.ButterKnife;
import zlc.season.practicalrecyclerview.PracticalRecyclerView;

public class ThreeDaysFragment extends BaseFragment implements IThreeDaysView {

    public static final String TAG = "ThreeDaysFragment";
    private ArrayList<Daily> arrayListThreeDay = new ArrayList<>();

    private DailyWeatherRecyclerAdapter mAdapter;

    @Inject
    IThreeDaysPresenter<IThreeDaysView> mPresenter;

    public static ThreeDaysFragment newInstance() {
        Bundle args = new Bundle();
        ThreeDaysFragment fragment = new ThreeDaysFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_three_days, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
        }
        arrayListThreeDay =  new ArrayList<>();
        for(int i = 0; i < 3; i++){
            arrayListThreeDay.add(DailyModelUi.listDaily.get(i));
        }

        PracticalRecyclerView recyclerView = (PracticalRecyclerView) view.findViewById(R.id.recyclerViewThreeDay);
        mAdapter = new DailyWeatherRecyclerAdapter();
        mAdapter.addAll(arrayListThreeDay);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onError(String errorString) {

    }

    @Override
    public void onError(@StringRes int idRes) {

    }

    @Override
    public boolean networkConnected() {
        return false;
    }

    @Override
    protected void setUp(View view) {

    }

}
