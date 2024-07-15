package forum.hub.api.controller;

import forum.hub.api.domain.dto.DadosTopico;
import forum.hub.api.domain.model.Topico;
import forum.hub.api.domain.repository.TopicoRepository;
import forum.hub.api.domain.dto.DadosAtualizacaoTopico;
import forum.hub.api.domain.dto.DadosDetalhamentoTopico;
import forum.hub.api.domain.dto.DadosListaTopico;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @Transactional
    @PostMapping
    public ResponseEntity<DadosDetalhamentoTopico> cadastrarTopico(@RequestBody @Valid DadosTopico dados, UriComponentsBuilder uriBuilder) {
        // Verifica se já existe um tópico com o mesmo título e autor
        Optional<Topico> topicoExistente = repository.findByTituloAndNomeAutor(dados.getTitulo(), dados.getNomeAutor());
        // Se o tópico já existir, retorna um erro
        if (topicoExistente.isPresent()) {
            return ResponseEntity.badRequest().body(new DadosDetalhamentoTopico(topicoExistente.get(), "Tópico já existe com o mesmo título e autor"));
        }
        Topico topico = new Topico(dados);
        repository.save(topico);

        // Cria a URI do novo tópico
        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        // Retorna a URI do novo tópico e os detalhes
        return ResponseEntity.created(uri).body(new DadosDetalhamentoTopico(topico, "Tópico criado com sucesso"));
    }

    @GetMapping
    public Page<DadosListaTopico> listarTopicos(@PageableDefault(size = 10, sort = {"dataDeCriacao"}) Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosListaTopico::new);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoTopico> detalharTopico(@PathVariable Long id) {
        Optional<Topico> topicoOptional = repository.findById(id);
        return topicoOptional.map(topico -> ResponseEntity.ok(new DadosDetalhamentoTopico(topico, "Detalhes do tópico")))
                .orElse(ResponseEntity.notFound().build());
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<String> atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoTopico dados) {
        Optional<Topico> topicoOptional = repository.findById(id);
        if (topicoOptional.isPresent()) {
            Topico topico = topicoOptional.get();
            topico.atualizarInformacoes(dados);
            repository.save(topico);
            return ResponseEntity.ok("Tópico atualizado com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        Optional<Topico> topicoOptional = repository.findById(id);
        if (topicoOptional.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
