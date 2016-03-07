package md.mi.model.json.response;

public class ErrorResponse {
    private String message;
    private String error;
    private String exception;
    private String path;
    private int status;
    private long timestamp;
//"error" => "Unauthorized", "exception" => "org.springframework.security.authentication.BadCredentialsException", "message" => "Invalid username or password",
// "path" => "/auth", "status" => 401, "timestamp" => 1457351031820
    public ErrorResponse(String message) {
        super();
        this.setMessage(message);
        this.setTimestamp(System.currentTimeMillis());
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String errorMessage) {

        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
