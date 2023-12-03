package com.projexapi.projexapi.Dashboard;

import com.projexapi.projexapi.Etapa.Etapa;
import com.projexapi.projexapi.Etapa.EtapaRepository;
import com.projexapi.projexapi.Etapa.EtapaService;
import com.projexapi.projexapi.Enums.Status;
import com.projexapi.projexapi.Etapa.QEtapa;
import com.projexapi.projexapi.Projeto.Projeto;
import com.projexapi.projexapi.Projeto.ProjetoRepository;
import com.projexapi.projexapi.Projeto.ProjetoService;
import com.projexapi.projexapi.Projeto.QProjeto;
import com.projexapi.projexapi.Utils.Paginacao;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//ifsvuibfuv
@RestController
@RequestMapping("api/dashboard")
@CrossOrigin("*")
@AllArgsConstructor
public class DashboardController {

    private ProjetoRepository projetoRepository;
    private ProjetoService projetoService;

    private EtapaRepository etapaRepository;
    private EtapaService etapaService;

//Geral - Visão Admin
@GetMapping("/")
public ResponseEntity<Map<String, Map<String, Integer>>> getPorStatus() {
    Map<String, Map<String, Integer>> porStatus = new HashMap<>();

    // Obter contagens de projetos por status
    Map<String, Integer> projetosPorStatus = new HashMap<>();
    for (Status status : Status.values()) {
        int quantidade = projetoRepository.countByStatus(status);
        projetosPorStatus.put(status.getDescricao(), quantidade);
    }
    porStatus.put("projetos", projetosPorStatus);

    // Obter contagens de etapas por status
    Map<String, Integer> etapasPorStatus = new HashMap<>();
    for (Status status : Status.values()) {
        int quantidade = etapaRepository.countByStatus(status);
        etapasPorStatus.put(status.getDescricao(), quantidade);
    }
    porStatus.put("etapas", etapasPorStatus);

    return ResponseEntity.ok(porStatus);
}


    @GetMapping("/projetos/{status}")
    public ResponseEntity<Paginacao> buscarProjetosPorStatus(
            @PathVariable Status status,
                @RequestParam(name="tamanhoPagina", defaultValue = "30") int tamanhoPagina,
                @RequestParam(name = "paginaSelecionada", defaultValue = "0") int paginaSelecionada){
        Pageable pageable = PageRequest.of(paginaSelecionada, tamanhoPagina);

        Page<Projeto> projetoList = this.projetoService.buscarProjetosPorStatus(status, pageable);

        List<DashboardRepresentation.DetalhesProjeto> listaFinal =
                DashboardRepresentation.DetalhesProjeto.from(projetoList.getContent());
        Paginacao paginacao = Paginacao.builder()
                .conteudo(listaFinal)
                .paginaSelecionada(paginaSelecionada)
                .proximaPagina(projetoList.hasNext())
                .tamanhoPagina(tamanhoPagina)
                .build();

        return ResponseEntity.ok(paginacao);
    }

    @GetMapping("/etapas/{status}")
    public ResponseEntity<Paginacao> buscarEtapasPorStatus(
            @PathVariable Status status,
            @RequestParam(name="tamanhoPagina", defaultValue = "30") int tamanhoPagina,
            @RequestParam(name = "paginaSelecionada", defaultValue = "0") int paginaSelecionada){
        Pageable pageable = PageRequest.of(paginaSelecionada, tamanhoPagina);

        Page<Etapa> etapaList = this.etapaService.buscarEtapasPorStatus(status, pageable);

        List<DashboardRepresentation.DetalhesEtapa> listaFinal =
                DashboardRepresentation.DetalhesEtapa.from(etapaList.getContent());
        Paginacao paginacao = Paginacao.builder()
                .conteudo(listaFinal)
                .paginaSelecionada(paginaSelecionada)
                .proximaPagina(etapaList.hasNext())
                .tamanhoPagina(tamanhoPagina)
                .build();

        return ResponseEntity.ok(paginacao);
    }

    @GetMapping("etapas/prazoFinalizacao")
    public ResponseEntity<Paginacao> buscarEtapasPrazoFinalizacao(
            @RequestParam(name="tamanhoPagina", defaultValue = "30") int tamanhoPagina,
            @RequestParam(name = "paginaSelecionada", defaultValue = "0") int paginaSelecionada){
        Pageable pageable = PageRequest.of(paginaSelecionada, tamanhoPagina);

        Page<Etapa> etapaList = this.etapaService.buscaEtapaPrazoFinalizacao(pageable);

        List<DashboardRepresentation.DetalhesEtapa> listaFinal =
                DashboardRepresentation.DetalhesEtapa.from(etapaList.getContent());
        Paginacao paginacao = Paginacao.builder()
                .conteudo(listaFinal)
                .paginaSelecionada(paginaSelecionada)
                .proximaPagina(etapaList.hasNext())
                .tamanhoPagina(tamanhoPagina)
                .build();

        return ResponseEntity.ok(paginacao);
    }

    @GetMapping("etapas/prazoFinalizacao/qtde")
    public Integer buscarQtdeEtapasPrazoFinalizacao(Pageable pageable){
       return this.etapaService.buscaQtdeEtapaPrazoFinalizacao(pageable);
    }


    //Por setor
    @GetMapping("/{idSetor}")
    public ResponseEntity<Map<String, Map<String, Integer>>> getPorStatusPorSetor(@PathVariable Long idSetor) {
        Map<String, Map<String, Integer>> porStatusPorSetor = new HashMap<>();

        // Obter contagens de projetos por status
        Map<String, Integer> projetosPorStatusPorSetor = new HashMap<>();
        for (Status status : Status.values()) {
            int quantidade = this.projetoService.buscarTodos(QProjeto.projeto.status.eq(status).and(QProjeto.projeto.setor().id.eq(idSetor))).size();

            projetosPorStatusPorSetor.put(status.getDescricao(), Integer.valueOf(String.valueOf(quantidade)));
        }
        porStatusPorSetor.put("projetos", projetosPorStatusPorSetor);

        // Obter contagens de etapas por status
        Map<String, Integer> etapasPorStatusPorSetor = new HashMap<>();
        for (Status status : Status.values()) {
            int quantidade = this.etapaService.buscarTodos(QEtapa.etapa.status.eq(status).and(QEtapa.etapa.projeto().setor().id.eq(idSetor))).size();

            etapasPorStatusPorSetor.put(status.getDescricao(), Integer.valueOf(String.valueOf(quantidade)));
        }
        porStatusPorSetor.put("etapas", etapasPorStatusPorSetor);

        return ResponseEntity.ok(porStatusPorSetor);
    }




    @GetMapping("/projetos/{idSetor}/{status}")
    public ResponseEntity<Paginacao> buscarProjetosPorStatusPorSetor(
            @PathVariable Status status,
            @PathVariable Long idSetor,
            @RequestParam(name="tamanhoPagina", defaultValue = "30") int tamanhoPagina,
            @RequestParam(name = "paginaSelecionada", defaultValue = "0") int paginaSelecionada){
        Pageable pageable = PageRequest.of(paginaSelecionada, tamanhoPagina);

        Page<Projeto> projetoList = this.projetoService.buscarTodos(QProjeto.projeto.status.eq(status).and(QProjeto.projeto.setor().id.eq(idSetor)), pageable);

        List<DashboardRepresentation.DetalhesProjeto> listaFinal =
                DashboardRepresentation.DetalhesProjeto.from(projetoList.getContent());
        Paginacao paginacao = Paginacao.builder()
                .conteudo(listaFinal)
                .paginaSelecionada(paginaSelecionada)
                .proximaPagina(projetoList.hasNext())
                .tamanhoPagina(tamanhoPagina)
                .build();

        return ResponseEntity.ok(paginacao);
    }

    @GetMapping("/etapas/{idSetor}/{status}")
    public ResponseEntity<Paginacao> buscarEtapasPorStatusPorSetor(
            @PathVariable Status status,
            @PathVariable Long idSetor,
            @RequestParam(name="tamanhoPagina", defaultValue = "30") int tamanhoPagina,
            @RequestParam(name = "paginaSelecionada", defaultValue = "0") int paginaSelecionada){
        Pageable pageable = PageRequest.of(paginaSelecionada, tamanhoPagina);



        Page<Etapa> etapaList = this.etapaService.buscarTodos(QEtapa.etapa.status.eq(status).and(QEtapa.etapa.projeto().setor().id.eq(idSetor)), pageable);

        List<DashboardRepresentation.DetalhesEtapa> listaFinal =
                DashboardRepresentation.DetalhesEtapa.from(etapaList.getContent());
        Paginacao paginacao = Paginacao.builder()
                .conteudo(listaFinal)
                .paginaSelecionada(paginaSelecionada)
                .proximaPagina(etapaList.hasNext())
                .tamanhoPagina(tamanhoPagina)
                .build();

        return ResponseEntity.ok(paginacao);
    }

    @GetMapping("etapas/{idSetor}/prazoFinalizacao")
    public ResponseEntity<Paginacao> buscarEtapasPrazoFinalizacaoPorSetor(
            @PathVariable Long idSetor,
            @RequestParam(name="tamanhoPagina", defaultValue = "30") int tamanhoPagina,
            @RequestParam(name = "paginaSelecionada", defaultValue = "0") int paginaSelecionada){
        Pageable pageable = PageRequest.of(paginaSelecionada, tamanhoPagina);

        Page<Etapa> etapaList = this.etapaService.buscaEtapaPrazoFinalizacaoPorSetor(idSetor, pageable);

        List<DashboardRepresentation.DetalhesEtapa> listaFinal =
                DashboardRepresentation.DetalhesEtapa.from(etapaList.getContent());
        Paginacao paginacao = Paginacao.builder()
                .conteudo(listaFinal)
                .paginaSelecionada(paginaSelecionada)
                .proximaPagina(etapaList.hasNext())
                .tamanhoPagina(tamanhoPagina)
                .build();

        return ResponseEntity.ok(paginacao);
    }

    @GetMapping("etapas/{idSetor}/prazoFinalizacao/qtde")
    public Integer buscarQtdeEtapasPrazoFinalizacaoPorSetor(@PathVariable Long idSetor, Pageable pageable){
        return this.etapaService.buscaQtdeEtapaPrazoFinalizacaoPorSetor(idSetor, pageable);
    }
}
