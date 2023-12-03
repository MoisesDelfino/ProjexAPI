package com.projexapi.projexapi.AlteracoesContratos;



import com.projexapi.projexapi.Comentario.Comentario;
import com.projexapi.projexapi.Comentario.ComentarioRepresentation;
import com.projexapi.projexapi.Contrato.ContratoService;
import com.projexapi.projexapi.Enums.TipoAlteracaoContrato;
import com.projexapi.projexapi.Projeto.Projeto;
import com.projexapi.projexapi.Projeto.ProjetoRepresentation;
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
@RequestMapping("api/alteracoesContratos")
@CrossOrigin("*")
@AllArgsConstructor
public class AlteracaoesContratosController {

    private AlteracoesContratosService alteracoesContratoService;

    private ContratoService contratoService;
    @PostMapping("/{idContrato}")
    public ResponseEntity<Message> createAlteracaoContrato(
            @PathVariable Long idContrato,
            @RequestBody @Valid AlteracoesContratoRepresentation.Criar criar) {

        this.alteracoesContratoService.criarAlteracaoContrato(contratoService, idContrato, criar);

        if (criar.getTipo() == TipoAlteracaoContrato.A) {
            return ResponseEntity.ok(new Message("Aditivo cadastrado com sucesso!"));
        } else {
            return ResponseEntity.ok(new Message("Supressão cadastrada com sucesso!"));
        }
    }
    @GetMapping("/{idAlteracaoContrato}")
    public ResponseEntity<AlteracoesContratoRepresentation.Detalhes> buscarUmaAlteracaoContrato(
            @PathVariable Long idAlteracaoContrato) {

        AlteracoesContratos alteracaoContrato = this.alteracoesContratoService.buscarUmaAlteracaoContrato(idAlteracaoContrato);

        AlteracoesContratoRepresentation.Detalhes detalhes =
                AlteracoesContratoRepresentation.Detalhes
                        .from(alteracaoContrato);

        return ResponseEntity
                .ok(detalhes);
    }
    @GetMapping("/")
    public ResponseEntity<Paginacao> buscarAlteracoesContratos(
            @QuerydslPredicate(root = Comentario.class) Predicate filtroURI,
            @RequestParam(name="tamanhoPagina", defaultValue = "30") int tamanhoPagina,
            @RequestParam(name = "paginaSelecionada", defaultValue = "0") int paginaSelecionada) {
        Pageable pageable = PageRequest.of(paginaSelecionada, tamanhoPagina);

        Page<AlteracoesContratos> alteracoesContratosList = Objects.isNull(filtroURI) ?
                this.alteracoesContratoService.buscarTodos(pageable):
                this.alteracoesContratoService.buscarTodos(filtroURI, pageable);

        List<AlteracoesContratoRepresentation.Detalhes> listaFinal =
                AlteracoesContratoRepresentation.Detalhes.from(alteracoesContratosList.getContent());
        Paginacao paginacao = Paginacao.builder()
                .conteudo(listaFinal)
                .paginaSelecionada(paginaSelecionada)
                .proximaPagina(alteracoesContratosList.hasNext())
                .tamanhoPagina(tamanhoPagina)
                .build();

        return ResponseEntity.ok(paginacao);

    }

    @PutMapping("/{idAlteracaoContrato}")
    public ResponseEntity<Message> atualizarAlteracaoContrato(
            @PathVariable Long idAlteracaoContrato,
            @RequestBody AlteracoesContratoRepresentation.Atualizar atualizar) {

        this.alteracoesContratoService.atualizar(idAlteracaoContrato, atualizar);

        if (atualizar.getTipo() == TipoAlteracaoContrato.A) {
            return ResponseEntity.ok(new Message("Aditivo atualizado com sucesso!"));
        } else {
            return ResponseEntity.ok(new Message("Supressão atualizada com sucesso!"));
        }
    }

    @DeleteMapping("/{idAlteracaoContrato}")
    public ResponseEntity<Message> inativarComentario(
            @PathVariable Long idAlteracaoContrato) {

        AlteracoesContratos alteracaoContratoInativa = this.alteracoesContratoService.inativar(idAlteracaoContrato);

        if (alteracaoContratoInativa.getTipo() == TipoAlteracaoContrato.A) {
            return ResponseEntity.ok(new Message("Aditivo inativado com sucesso!"));
        } else {
            return ResponseEntity.ok(new Message("Supressão inativada com sucesso!"));
        }

    }
    @GetMapping("/contrato/{idContrato}")
    public ResponseEntity<Paginacao> buscarAlteracoesPorContrato(
            @PathVariable Long idContrato,
            @RequestParam(name="tamanhoPagina", defaultValue = "30") int tamanhoPagina,
            @RequestParam(name = "paginaSelecionada", defaultValue = "0") int paginaSelecionada){
        Pageable pageable = PageRequest.of(paginaSelecionada, tamanhoPagina);

        Page<AlteracoesContratos> alteracoesContratosList = this.alteracoesContratoService.buscarAlteracoesPorContrato(idContrato, pageable);

        List<AlteracoesContratoRepresentation.Detalhes> listaFinal =
                AlteracoesContratoRepresentation.Detalhes.from(alteracoesContratosList.getContent());
        Paginacao paginacao = Paginacao.builder()
                .conteudo(listaFinal)
                .paginaSelecionada(paginaSelecionada)
                .proximaPagina(alteracoesContratosList.hasNext())
                .tamanhoPagina(tamanhoPagina)
                .build();

        return ResponseEntity.ok(paginacao);
    }

}
