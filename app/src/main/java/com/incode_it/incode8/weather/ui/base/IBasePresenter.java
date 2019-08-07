package com.incode_it.incode8.weather.ui.base;

import com.androidnetworking.error.ANError;

/**
 * Created by incode8 on 08.08.17.
 */

public interface IBasePresenter<V extends IBaseView> {

    void onAttach(V view);

    void onDetach();

    void handleApiError(ANError error);
}
