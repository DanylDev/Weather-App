package com.incode_it.incode8.weather.utils.rx;

import io.reactivex.Scheduler;

/**
 * Created by incode8 on 08.08.17.
 */

public interface ISchedulerProvider {

    Scheduler ui();

    Scheduler computation();

    Scheduler io();
}
