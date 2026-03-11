package br.com.alura.forumhub.controller;

import br.com.alura.forumhub.domain.topico.DadosAtualizacaoTopico;
import br.com.alura.forumhub.domain.topico.DadosCadastroTopico;
import br.com.alura.forumhub.domain.topico.DadosDetalhamentoTopico;
import br.com.alura.forumhub.domain.topico.DadosListagemTopico;
import br.com.alura.forumhub.domain.topico.Topico;
import br.com.alura.forumhub.domain.topico.TopicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroTopico dados) {
      
        if (repository.existsByTituloAndMensagem(dados.titulo(), dados.mensagem())) {
            return ResponseEntity.badRequest().body("Já existe um tópico com este título e mensagem!");
        }

        var topico = new Topico(dados);
        repository.save(topico);

        return ResponseEntity.ok(topico); 
    }

    @GetMapping
    public ResponseEntity<org.springframework.data.domain.Page<DadosListagemTopico>> listar(
            @org.springframework.data.web.PageableDefault(size = 10, sort = {"dataCriacao"}) org.springframework.data.domain.Pageable paginacao) {
        
        var page = repository.findAll(paginacao).map(DadosListagemTopico::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
    
        var topicoOptional = repository.findById(id);
        
       
        if (topicoOptional.isPresent()) {
            var topico = topicoOptional.get();
            return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
        }
        
        return ResponseEntity.notFound().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoTopico dados) {
        var topico = repository.getReferenceById(dados.id());
        topico.atualizarInformacoes(dados);
        
        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        repository.deleteById(id);
        
        return ResponseEntity.noContent().build();
    }
}