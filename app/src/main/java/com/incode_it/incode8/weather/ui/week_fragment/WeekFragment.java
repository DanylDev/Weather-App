package com.incode_it.incode8.weather.ui.week_fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.incode8.weather.R;
import com.incode_it.incode8.weather.di.component.ActivityComponent;
import com.incode_it.incode8.weather.models.daily_model.DailyModelUi;
import com.incode_it.incode8.weather.adapter.Weather.DailyWeatherRecyclerAdapter;
import com.incode_it.incode8.weather.ui.base.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import zlc.season.practicalrecyclerview.PracticalRecyclerView;


public class WeekFragment extends BaseFragment implements IWeekView {

    public static final String TAG = "WeelFragment";
    private Typeface typeface;
    private DailyWeatherRecyclerAdapter mAdapter;

    @Inject
    IWeekPresenter<IWeekView> mPresenter;

    @BindView(R.id.recyclerViewWeek)
    PracticalRecyclerView recyclerView;

    public static WeekFragment newInstance() {
        Bundle args = new Bundle();
        WeekFragment fragment = new WeekFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_week, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
        }
        String fontPath = "fonts/CenturyGothicRegular.ttf";
        typeface = Typeface.createFromAsset(getActivity().getAssets(), fontPath);
        mAdapter = new DailyWeatherRecyclerAdapter();
        mAdapter.addAll(DailyModelUi.listDaily);
        recyclerView.setOverScrollMode(2);
        recyclerView.setLayoutManager( new LinearLayoutManager(view.getContext()));
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
