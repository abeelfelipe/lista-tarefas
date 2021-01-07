package com.listaTarefas.rest;

import com.listaTarefas.model.entity.ListaTarefas;
import com.listaTarefas.model.entity.Tarefa;
import com.listaTarefas.model.repository.ListaTarefasRepository;
import com.listaTarefas.model.repository.TarefaRepository;
import com.listaTarefas.rest.dto.TarefaDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tarefa")
@CrossOrigin("http://localhost:4200")
@RequiredArgsConstructor
@Api(value="API Lista de Tarefas - Tarefas")
public class TarefaController {

    private final TarefaRepository repository;
    private final ListaTarefasRepository listaTarefasRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Salva uma tarefa para uma lista específica")
    public Tarefa salvar(@RequestBody TarefaDTO dto) {
        ListaTarefas listaTarefas =
                listaTarefasRepository.findById(dto.getIdLista())
                        .orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lista não existe"));

        Tarefa tarefa = new Tarefa();
        tarefa.setNomeTarefa(dto.getNomeTarefa());
        tarefa.setListaTarefas(listaTarefas);
        tarefa.setConcluido(Boolean.valueOf(dto.getConcluido()));


        return repository.save(tarefa);
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Consulta uma tarefa por ID")
    public Tarefa consultarPorId(@PathVariable Integer id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    @ApiOperation(value = "Consulta uma tarefa pelo Id da Lista")
    public List<Tarefa> consultarPorLista(@RequestParam(value = "idLista", required = true) String idLista) {
        ListaTarefas listaTarefas =
                listaTarefasRepository.findById(Integer.valueOf(idLista))
                        .orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lista não existe"));
        return repository.findAllByListaTarefas(listaTarefas);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Deleta uma tarefa")
    public void deletar(@PathVariable Integer id) {
        repository.
                findById(id).
                map(tarefa -> {
                    repository.delete(tarefa);
                    return Void.TYPE;
                }).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Altera uma tarefa já existente")
    public void alterar(@PathVariable Integer id, @RequestBody @Valid Tarefa tarefaAtualizada) {
        repository.
                findById(id).
                map(tarefa -> {
                    tarefaAtualizada.setId(tarefa.getId());
                    return repository.save(tarefaAtualizada);
                }).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
