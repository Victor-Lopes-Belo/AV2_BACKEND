package br.com.tech4me.tech4singer.service;

import java.util.List;
import java.util.Optional;

import br.com.tech4me.tech4singer.dto.CantorDto;

public interface CantorService {

    // GET
    List<CantorDto> obterTodosOsCantores();

    // GET ID
    Optional<CantorDto> obterCantorPorId(String id);

    // POST
    CantorDto criarCantor(CantorDto cantor);

    // PUT
    CantorDto atualizarCantorPorId(String id, CantorDto cantor);

    // DELETE
    void excluirCantorPorId(String id);
}
