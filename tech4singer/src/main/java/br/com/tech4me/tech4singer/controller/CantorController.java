package br.com.tech4me.tech4singer.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tech4me.tech4singer.dto.CantorDto;
import br.com.tech4me.tech4singer.service.CantorService;
import br.com.tech4me.tech4singer.view.model.CantorResponseComLivrosDto;
import br.com.tech4me.tech4singer.view.model.CantorResponseDto;

@RestController
@RequestMapping("/api/artista")
public class CantorController {

    ModelMapper mapper = new ModelMapper();

    @Autowired
    private CantorService cantorService;
    
    @GetMapping
    public ResponseEntity<List<CantorResponseDto>> obterTodosOsCantores() {

        List<CantorDto> cantorDto = cantorService.obterTodosOsCantores();
        
        List<CantorResponseDto> responseDto = cantorDto.stream()
        .map(dto -> mapper
        .map(dto, CantorResponseDto.class))
        .collect(Collectors.toList());

        return new ResponseEntity<>(responseDto, HttpStatus.FOUND);
    }

    // GET /ID
    @GetMapping(value = "/{id}")
    public ResponseEntity<CantorResponseComLivrosDto> obterCantorPorId(@PathVariable String id) {

        Optional<CantorDto> cantorDto = cantorService.obterCantorPorId(id);

        if (cantorDto.isPresent()) {
            return new ResponseEntity<>(mapper.map(cantorDto.get(), CantorResponseComLivrosDto.class), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<CantorResponseDto> criarCantor(@RequestBody @Valid CantorDto cantor) {
        CantorDto cantorDto = cantorService.criarCantor(cantor);

        return new ResponseEntity<>(mapper.map(cantorDto, CantorResponseDto.class),HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CantorResponseDto> atualizarCantorPorId(@PathVariable String id, @RequestBody @Valid CantorDto cantor) {
       
        Optional<CantorDto> cantorEntidadeBusca = cantorService.obterCantorPorId(id);
        if (cantorEntidadeBusca.isPresent()) {
            CantorDto cantorEntidade = cantorService.atualizarCantorPorId(id, cantor);
            return new ResponseEntity<>( mapper.map(cantorEntidade,CantorResponseDto.class), HttpStatus.OK);
        }
        return null;
    }

    @DeleteMapping
    public ResponseEntity<Void> excluirCantorPorId(@PathVariable String id) {
        cantorService.excluirCantorPorId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
