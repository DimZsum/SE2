package net.ziemers.swxercise.ui;

import net.ziemers.swxercise.ui.enums.ResponseState;
import net.ziemers.swxercise.ui.enums.ResponseState.SUCCESS;

public class RestResponse {

    private ResponseState responseState;

    public RestResponse() {
        this(ResponseState.SUCCESS);
    }

    public RestResponse(final ResponseState responseState) {
        this.responseState = responseState;        
    }

    public int getResponseCode() {
        return responseState.getResponseCode();
    }

    public String getResponseText() {
        return responseState.getResponseText();
    }

}

