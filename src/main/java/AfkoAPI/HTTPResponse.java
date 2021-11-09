package AfkoAPI;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class HTTPResponse<T> {
    /**
     * the code to send back on success
     */
    private static final String SUCCESS_RESPONSE_CODE = "SUCCESS";

    /**
     * the response code, mostly "FAILURE" or "SUCCESS"
     */
    private String response;

    /**
     * the error message
     */
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

    /** checks if this HTTPResponse is a success, is ignored by json
     * @return whether this HTTPResponse contains a success
     */
    @JsonIgnore
    public boolean isSuccess() {
        return response.equals(SUCCESS_RESPONSE_CODE);
    }

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
