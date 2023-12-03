package com.projexapi.projexapi.Risco;

import com.projexapi.projexapi.Utils.Message;
import com.projexapi.projexapi.Utils.Paginacao;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api/risco")
@CrossOrigin("*")
@AllArgsConstructor
public class RiscoController {
    private RiscoService riscoService;

    @GetMapping("/")
    public ResponseEntity<Paginacao> buscarRiscos(
            @QuerydslPredicate(root = Risco.class) Predicate filtroURI,
            @RequestParam(name="tamanhoPagina", defaultValue = "30") int tamanhoPagina,
            @RequestParam(name = "paginaSelecionada", defaultValue = "0") int paginaSelecionada) {
        Pageable pageable = PageRequest.of(paginaSelecionada, tamanhoPagina);

        Page<Risco> riscoList = Objects.isNull(filtroURI) ?
                this.riscoService.buscarTodos(pageable):
                this.riscoService.buscarTodos(filtroURI, pageable);

        List<RiscoRepresentation.Lista> listaFinal =
                RiscoRepresentation.Lista.from(riscoList.getContent());
        Paginacao paginacao = Paginacao.builder()
                .conteudo(listaFinal)
                .paginaSelecionada(paginaSelecionada)
                .proximaPagina(riscoList.hasNext())
                .tamanhoPagina(tamanhoPagina)
                .build();

        return ResponseEntity.ok(paginacao);

    }
    @PostMapping("/")
    public ResponseEntity<Message> createRisco(
            @RequestBody @Valid RiscoRepresentation.Criar criar) {


        this.riscoService.criarRisco(criar);

        return ResponseEntity.ok(new Message("Risco cadastrado com sucesso!"));

    }
    @PutMapping("/{idRisco}")
    public ResponseEntity<Message> atualizarRisco(
            @PathVariable Long idRisco,
            @RequestBody RiscoRepresentation.Atualizar atualizar) {

        this.riscoService.atualizar(idRisco, atualizar);

        return ResponseEntity.ok(new Message("Risco atualizado com sucesso!"));

    }
    @DeleteMapping("/{idRisco}")
    public ResponseEntity<Message> inativarProjeto(
            @PathVariable Long idRisco) {

        this.riscoService.inativar(idRisco);

        return ResponseEntity.ok(new Message("Risco inativado com sucesso!"));

    }
    @GetMapping("/{idRisco}")
    public ResponseEntity<RiscoRepresentation.Detalhes> buscarUmRisco(
            @PathVariable Long idRisco) {

        Risco risco = this.riscoService.buscarUmRisco(idRisco);

        RiscoRepresentation.Detalhes detalhes =
                RiscoRepresentation.Detalhes
                        .from(risco);

        return ResponseEntity
                .ok(detalhes);
    }
}
