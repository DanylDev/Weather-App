package com.incode_it.incode8.weather.data.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by incode8 on 08.08.17.
 */

public class ApiError {

    private int errorCode;

    @Expose
    @SerializedName("code")
    private String statusCode;

    @Expose
    @SerializedName("message")
    private String message;

    ApiError(int errorCode, String statusCode, String message){
        this.errorCode = errorCode;
        this.statusCode = statusCode;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        ApiError apiError = (ApiError) obj;

        if (errorCode != apiError.errorCode) return false;
        if (statusCode != null ? !statusCode.equals(apiError.statusCode)
                : apiError.statusCode != null)
            return false;
        return message != null ? message.equals(apiError.message) : apiError.message == null;
    }

    @Override
    public int hashCode() {
        int result = errorCode;
        result = 31 * result + (statusCode != null ? statusCode.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }

}
