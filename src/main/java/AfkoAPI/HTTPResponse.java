package AfkoAPI;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class HTTPResponse<T> {
    private static final String SUCCESS_RESPONSE_CODE = "SUCCESS";
    // error code
    private String response;
    // error message
    private String error;
    private T data;
    private HTTPResponse(String response, String error, T data) {
        this.response = response;
        this.error = error;
        this.data = data;
    }
    public String getResponse() {
        return response;
    }

    public String getError() {
        return error;
    }

    public T getData() {
        return data;
    }

    @JsonIgnore
    public boolean isSuccess() { return response.equals(SUCCESS_RESPONSE_CODE); }

    public static <T> HTTPResponse returnSuccess(T data) {
        return new HTTPResponse<T>(SUCCESS_RESPONSE_CODE, "", data);
    }

    public static <T> HTTPResponse returnFailure(String message) {
        return new HTTPResponse<T>("FAILURE", message, null);
    }
    public static <T> HTTPResponse returnUserDisabled(String message) {
        return new HTTPResponse<T>("USER_DISABLED", message, null);
    }
    public static <T> HTTPResponse returnInvalidCredentials(String message) {
        return new HTTPResponse<T>("INVALID_CREDENTIALS", message, null);
    }
}
