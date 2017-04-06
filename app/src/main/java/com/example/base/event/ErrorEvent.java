package com.example.base.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by Wagos.
 */

@RequiredArgsConstructor
@Getter
public class ErrorEvent {
    private final RequestType requestType;
}
