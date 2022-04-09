package br.com.tech4me.tech4singer.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tech4me.tech4singer.clients.MusicaFeignClient;
import br.com.tech4me.tech4singer.dto.CantorDto;
import br.com.tech4me.tech4singer.model.Cantor;
import br.com.tech4me.tech4singer.repository.CantorRepository;

@Service
public class CantorServiceImpel implements CantorService {

    private ModelMapper mapper = new ModelMapper();

    @Autowired
    private CantorRepository cantorRepository;

    @Autowired
    private MusicaFeignClient musicaFeign;

    // GET
    @Override
    public List<CantorDto> obterTodosOsCantores() {

        List<Cantor> cantor = cantorRepository.findAll();

        return cantor.stream()
        .map(c -> mapper
        .map(c, CantorDto.class))
        .collect(Collectors.toList()); 
    }

    // GET ID
    @Override
    public Optional<CantorDto> obterCantorPorId(String id) {

        Optional<Cantor> cantor = cantorRepository.findById(id);

        if(cantor.isPresent()) {
           CantorDto cantorDto = mapper.map(cantor.get(), CantorDto.class);

           cantorDto.setMusica(musicaFeign.obterMusicaPorArtista(id));

           return Optional.of(cantorDto);
        }

        return Optional.empty();
    }

    // POST
    @Override
    public CantorDto criarCantor(CantorDto cantor) {

        Cantor cantorEntidade = mapper.map(cantor, Cantor.class);
        cantorEntidade = cantorRepository.save(cantorEntidade);

        return mapper.map(cantorEntidade, CantorDto.class);
        
    }

    // PUT
    @Override
    public CantorDto atualizarCantorPorId(String id, CantorDto cantor) {

        Optional<Cantor> cantorEntidadeBusca =  cantorRepository.findById(id);
        if(cantorEntidadeBusca.isPresent()) {
            Cantor cantorEntidade = mapper.map(cantor, Cantor.class);
            cantorEntidade = cantorRepository.save(cantorEntidade);
            return mapper.map(cantorEntidade, CantorDto.class);
        }
        return null;
       
    }

    // DELETE
    @Override
    public void excluirCantorPorId(String id) {
        cantorRepository.deleteById(id);
    }
    
}
