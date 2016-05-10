package com.example.base.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by Wagos.
 */
@Getter
@RequiredArgsConstructor
public class ErrorEvent {
    private final RequestType requestType;
}
