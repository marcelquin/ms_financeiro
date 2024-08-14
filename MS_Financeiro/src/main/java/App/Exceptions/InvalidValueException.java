package App.Exceptions;

public class InvalidValueException extends RuntimeException {

    public InvalidValueException() {
        super("Valor Invalido, verifique os dados e tente novamente");
    }

    public InvalidValueException(String message) {
        super(message);
    }

}
