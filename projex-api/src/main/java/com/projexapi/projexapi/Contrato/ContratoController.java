package com.projexapi.projexapi.Contrato;

import com.projexapi.projexapi.Projeto.ProjetoService;
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
@RequestMapping("api/contrato")
@CrossOrigin("*")
@AllArgsConstructor
public class ContratoController {
    private ContratoService contratoService;

    private ProjetoService projetoService;


    @PostMapping("/{idProjeto}")
    public ResponseEntity<Message> createContrato(
            @PathVariable Long idProjeto,
            @RequestBody @Valid ContratoRepresentation.Criar criar) {


        this.contratoService.criarContrato(projetoService, idProjeto, criar);

        return ResponseEntity.ok(new Message("Contrato cadastrado com sucesso!"));

    }
    @PutMapping("/{idContrato}")
    public ResponseEntity<Message> atualizarContrato(
            @PathVariable Long idContrato,
            @RequestBody ContratoRepresentation.Atualizar atualizar) {

        this.contratoService.atualizar(idContrato, atualizar);


        return ResponseEntity.ok(new Message("Contrato atualizado com sucesso!"));

    }
    @DeleteMapping("/{idContrato}")
    public ResponseEntity<Message> inativarContrato(
            @PathVariable Long idContrato) {

        Contrato contratoInativo = this.contratoService.inativar(idContrato);

        ContratoRepresentation.Detalhes detalhes = ContratoRepresentation.Detalhes.from(contratoInativo);

        return ResponseEntity.ok(new Message("Contrato inativado com sucesso!"));

    }
    @GetMapping("/{idContrato}")
    public ResponseEntity<ContratoRepresentation.Detalhes> buscarUmContrato(
            @PathVariable Long idContrato) {

        Contrato contrato = this.contratoService.buscarUmContrato(idContrato);

        ContratoRepresentation.Detalhes detalhes =
                ContratoRepresentation.Detalhes
                        .from(contrato);

        return ResponseEntity
                .ok(detalhes);
    }
    @GetMapping("/")
    public ResponseEntity<Paginacao> buscarContratos(
            @QuerydslPredicate(root = Contrato.class) Predicate filtroURI,
            @RequestParam(name="tamanhoPagina", defaultValue = "30") int tamanhoPagina,
            @RequestParam(name = "paginaSelecionada", defaultValue = "0") int paginaSelecionada) {
        Pageable pageable = PageRequest.of(paginaSelecionada, tamanhoPagina);

        Page<Contrato> contratoList = Objects.isNull(filtroURI) ?
                this.contratoService.buscarTodos(pageable):
                this.contratoService.buscarTodos(filtroURI, pageable);

        List<ContratoRepresentation.Lista> listaFinal =
                ContratoRepresentation.Lista.from(contratoList.getContent());
        Paginacao paginacao = Paginacao.builder()
                .conteudo(listaFinal)
                .paginaSelecionada(paginaSelecionada)
                .proximaPagina(contratoList.hasNext())
                .tamanhoPagina(tamanhoPagina)
                .build();

        return ResponseEntity.ok(paginacao);

    }

}
