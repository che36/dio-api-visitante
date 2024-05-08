package apivisitante.entity.exception;

public class CpfInvalidoException extends RuntimeException {

    public CpfInvalidoException() {
        super("cpf invalido ou não informado");
    }
}
