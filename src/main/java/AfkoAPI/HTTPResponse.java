package AfkoAPI;

public class HTTPResponse<T> {
    private String response;
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
    public static <T> HTTPResponse returnSuccess(T data) {
        return new HTTPResponse<T>("SUCCESS", "", data);
    }
    public static <T> HTTPResponse returnFailure(String message) {
        return new HTTPResponse<T>("FAILURE", message, null);
    }
}
