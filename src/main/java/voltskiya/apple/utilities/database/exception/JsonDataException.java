package voltskiya.apple.utilities.database.exception;

import java.io.IOException;

public class JsonDataException extends IOException {

    private int code;

    public JsonDataException(int code) {
        this.code = code;
    }

    public JsonDataException(String message, int code) {
        super(message);
        this.code = code;
    }

    public JsonDataException(String message, Throwable cause, int code) {
        super(message, cause);
        this.code = code;
    }

    public JsonDataException(Throwable cause, int code) {
        super(cause);
        this.code = code;
    }
}
