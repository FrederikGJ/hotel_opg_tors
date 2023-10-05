package exception;

import lombok.Getter;

@Getter
public class APIExeption extends  Exception{

    private final int statusCode;

    public APIExeption(int statusCode, String message) {
        super(message);
        this.statusCode= statusCode;
    }
}