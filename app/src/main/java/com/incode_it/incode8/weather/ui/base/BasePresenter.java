package com.incode_it.incode8.weather.ui.base;

import android.util.Log;

import com.androidnetworking.common.ANConstants;
import com.androidnetworking.error.ANError;
import com.incode_it.incode8.weather.data.IDataManager;
import com.incode_it.incode8.weather.data.network.ApiError;
import com.incode_it.incode8.weather.utils.AppConstants;
import com.incode_it.incode8.weather.utils.rx.ISchedulerProvider;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import javax.inject.Inject;
import javax.net.ssl.HttpsURLConnection;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by incode8 on 08.08.17.
 */

public class BasePresenter<V extends IBaseView> implements IBasePresenter<V> {

    private static final String TAG = "BasePresenter";

    private final ISchedulerProvider mSchedulerProvider;
    private final CompositeDisposable mCompositeDisposable;
    private final IDataManager mDataManager;

    private V mView;

    @Inject
    public BasePresenter(ISchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable,
                         IDataManager dataManager) {
        this.mSchedulerProvider = schedulerProvider;
        this.mCompositeDisposable = compositeDisposable;
        this.mDataManager = dataManager;
    }

    public ISchedulerProvider getSchedulerProvider() {
        return mSchedulerProvider;
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    public IDataManager getDataManager() {
        return mDataManager;
    }

    @Override
    public void onAttach(V view) {
        mView = view;
    }

    @Override
    public void onDetach() {
        mCompositeDisposable.dispose();
        mView = null;
    }

    public V getMvpView() {
        return mView;
    }

    @Override
    public void handleApiError(ANError error) {
        if (error == null || error.getErrorBody() == null) {
            getMvpView().onError("Error");
            return;
        }

        if (error.getErrorCode() == AppConstants.API_STATUS_CODE_LOCAL_ERROR
                && error.getErrorDetail().equals(ANConstants.CONNECTION_ERROR)) {
            getMvpView().onError("Connection error");
            return;
        }

        if (error.getErrorCode() == AppConstants.API_STATUS_CODE_LOCAL_ERROR
                && error.getErrorDetail().equals(ANConstants.REQUEST_CANCELLED_ERROR)) {
            getMvpView().onError("Retry error");
            return;
        }

        final GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
        final Gson gson = builder.create();

        try {
            ApiError apiError = gson.fromJson(error.getErrorBody(), ApiError.class);

            if (apiError == null || apiError.getMessage() == null) {
                getMvpView().onError("Default error");
                return;
            }

            switch (error.getErrorCode()) {
                case HttpsURLConnection.HTTP_INTERNAL_ERROR:
                case HttpsURLConnection.HTTP_NOT_FOUND:
                default:
                    getMvpView().onError(apiError.getMessage());
            }
        } catch (JsonSyntaxException | NullPointerException e) {
            Log.e(TAG, "handleApiError", e);
            getMvpView().onError("Error");
        }


    }

}
