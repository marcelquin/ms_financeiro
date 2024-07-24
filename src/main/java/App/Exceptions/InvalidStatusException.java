package App.Exceptions;

public class InvalidStatusException extends RuntimeException {

    public InvalidStatusException() {
        super("Ops! algo errado, verifique as informações do pedido");
    }

    public InvalidStatusException(String message) {
        super(message);
    }

}
