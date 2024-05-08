package apivisitante.entity.controller;

import apivisitante.entity.Visitante;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

public class VisitanteController {
    private final VisitanteService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Visitante criar(@RequestBody VisitanteRequestDTO dto) {
        return service.criar(dto);
    }

    @GetMapping
    public List<Visitante> listar() {
        return service.listar();
    }

}
