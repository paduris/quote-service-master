package com.paduris.git.create.quote.exception;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@NoArgsConstructor
public class AddressNotFoundException extends RuntimeException {
    @NonNull
    private String message;
}
