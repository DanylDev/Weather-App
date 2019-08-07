package com.incode_it.incode8.weather.ui.three_days;

import com.incode_it.incode8.weather.data.IDataManager;
import com.incode_it.incode8.weather.ui.base.BasePresenter;
import com.incode_it.incode8.weather.utils.rx.ISchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by incode8 on 13.08.17.
 */

public class ThreeDaysPresenter<V extends IThreeDaysView> extends BasePresenter<V>
        implements IThreeDaysPresenter<V> {

    @Inject
    public ThreeDaysPresenter(ISchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, IDataManager dataManager) {
        super(schedulerProvider, compositeDisposable, dataManager);
    }

}
