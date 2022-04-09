package br.com.tech4me.tech4music.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import br.com.tech4me.tech4music.dto.MusicaDto;
import br.com.tech4me.tech4music.services.MusicaService;
import br.com.tech4me.tech4music.view.model.MusicaResponseDto;

@RestController
@RequestMapping("/api/musicas")
public class MusicaController {

    ModelMapper mapper = new ModelMapper();

    @Autowired
    private MusicaService servico;

    @GetMapping
    public ResponseEntity<List<MusicaResponseDto>> obterMusicas() {

        List<MusicaDto> musicaDto = servico.obterTodasAsMusicas();

        List<MusicaResponseDto> musicas = musicaDto.stream()
                .map(l -> mapper.map(l, MusicaResponseDto.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(musicas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MusicaResponseDto> obterMusicaPorId(@PathVariable String id) {

        Optional<MusicaDto> musica = servico.obterMusicaPorId(id);

        if (musica.isPresent()) {
            return new ResponseEntity<>(mapper.map(musica.get(), MusicaResponseDto.class), HttpStatus.FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/artista/{artista}")
    public ResponseEntity<List<MusicaResponseDto>> obterMusicaPorArtista(@PathVariable String artista) {

        List<MusicaDto> musicaDto = servico.obterMusicaPorArtista(artista);

        if (musicaDto.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<MusicaResponseDto> musicaRespose = musicaDto.stream()
        .map(m -> mapper.map(m, MusicaResponseDto.class))
        .collect(Collectors.toList());

        return new ResponseEntity<>(musicaRespose, HttpStatus.OK);
    }

    @GetMapping(value = "/status")
    public String statusServico(@Value("${local.server.port}") String porta) {
        return String.format("Serviço ativo e executando na porta %s", porta);
    }

    @PostMapping
    public ResponseEntity<MusicaResponseDto> adicionarMusica(@RequestBody @Valid MusicaDto musica) {

        MusicaDto musicaDto = servico.criarMusica(musica);

        return new ResponseEntity<>(mapper.map(musicaDto, MusicaResponseDto.class), HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<MusicaResponseDto> atualizarMusica(@PathVariable String id,
            @RequestBody @Valid MusicaDto musica) {

        Optional<MusicaDto> musicaBusca = servico.obterMusicaPorId(id);
        if (musicaBusca.isPresent()) {

            MusicaDto musicaDto = servico.atualizarMusicaPorId(id, musica);
            return new ResponseEntity<>(mapper.map(musicaDto, MusicaResponseDto.class), HttpStatus.OK);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerMusica(@PathVariable String id) {
        servico.excluirMusicaPorId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
