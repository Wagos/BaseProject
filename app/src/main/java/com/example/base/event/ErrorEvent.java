package com.example.base.event;

/**
 * Created by Wagos.
 */

public class ErrorEvent {
    private final RequestType requestType;

    public ErrorEvent(RequestType requestType) {
        this.requestType = requestType;
    }

    public RequestType getRequestType() {
        return requestType;
    }
}
