package net.ziemers.swxercise.ui;

import net.ziemers.swxercise.ui.enums.ResponseState;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RestResponse that = (RestResponse) o;

        return responseState == that.responseState;
    }

}

