package AfkoAPI;

public class HTTPResponse<T> {
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

    public boolean isSuccess() { return response.equals("Success"); }

    public static <T> HTTPResponse returnSuccess(T data) {
        return new HTTPResponse<T>("SUCCESS", "", data);
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
