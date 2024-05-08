package apivisitante.entity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VisitanteService {

    private final VisitanteRepository repository;

    public Visitante criar(VisitanteRequestDto dto) {
        VisitanteValidation.validarCriacaoDoVisitante(dto);

        var id = UUID.randomUUID().toString();
        var visitante = new Visitante(id, dto.getCpf(), dto.getNome());
        return repository.save(visitante);
    }

    public List<Visitante> listar() {
        return repository.findAll();
    }

}
