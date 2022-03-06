package edu.omur.shoppingcart.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceResponse<T> {
    private T modelData;
    private boolean errorOccurred;
    private String errorMessage;

    public ServiceResponse(T modelData) {
        this.modelData = modelData;
        errorOccurred = false;
        errorMessage = null;
    }

    public ServiceResponse(T modelData, String message) {
        this.modelData = modelData;
        if (message != null && (!message.isEmpty())) {
            errorOccurred = true;
            errorMessage = message;
        } else {
            errorOccurred = false;
            errorMessage = null;
        }
    }
}
