package com.listaTarefas.rest;

import com.listaTarefas.model.entity.ListaTarefas;
import com.listaTarefas.model.repository.ListaTarefasRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/lista-tarefas")
@CrossOrigin("http://localhost:4200")
@RequiredArgsConstructor
@Api(value="API Lista de Tarefas - Lista de Tarefas")
public class ListaTarefasController {

    private final ListaTarefasRepository repository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Salva uma lista de tarefas")
    public ListaTarefas salvar(@RequestBody @Valid ListaTarefas listaTarefas) {
        return repository.save(listaTarefas);
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Consulta uma lista de tarefas por ID")
    public ListaTarefas consultarPorId(@PathVariable Integer id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    @ApiOperation(value = "Consulta todas as lista de tarefas existentes")
    public List<ListaTarefas> obterTodasAsListas(){
         return repository.findAll();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Deleta uma lista de terefas")
    public void deletar(@PathVariable Integer id) {
        repository.
                findById(id).
                map(listaTarefas -> {
                    repository.delete(listaTarefas);
                    return Void.TYPE;
                }).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Altera uma lista de Tarefas")
    public void alterar(@PathVariable Integer id, @RequestBody @Valid ListaTarefas ListaTarefasAtualizada) {
        repository.
                findById(id).
                map(listaTarefas -> {
                    ListaTarefasAtualizada.setId(listaTarefas.getId());
                    return repository.save(ListaTarefasAtualizada);
                }).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
