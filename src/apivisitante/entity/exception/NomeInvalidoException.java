package apivisitante.entity.exception;


public class NomeInvalidoException extends RuntimeException {

    public NomeInvalidoException() {
        super("nome não informado");
    }
}
