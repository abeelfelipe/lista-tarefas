package com.listaTarefas.service;

import com.listaTarefas.model.entity.ListaTarefas;
import com.listaTarefas.model.entity.Tarefa;
import com.listaTarefas.model.repository.TarefaRepository;
import com.listaTarefas.model.entity.dto.TarefaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TarefasService {
    @Autowired
    public ListaTarefasService listaTarefasService;

    @Autowired
    public TarefaRepository repository;
    public Tarefa salvar(TarefaDTO dto) {
        ListaTarefas listaTarefas = listaTarefasService.findById(dto.getIdLista().toString());

        Tarefa tarefa = new Tarefa();
        tarefa.setNomeTarefa(dto.getNomeTarefa());
        tarefa.setListaTarefas(listaTarefas);
        tarefa.setConcluido(Boolean.valueOf(dto.getConcluido()));


        return repository.save(tarefa);
    }

    public void excluir(Integer id) {
        repository.
                findById(id).
                map(tarefa -> {
                    repository.delete(tarefa);
                    return Void.TYPE;
                }).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void alterar(Integer id, Tarefa tarefaAtualizada) {
        repository.
                findById(id).
                map(tarefa -> {
                    tarefaAtualizada.setId(tarefa.getId());
                    return repository.save(tarefaAtualizada);
                }).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Tarefa findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<Tarefa> findAllByListaTarefas(String id) {
        return repository.findAllByListaTarefas(listaTarefasService.findById(id));
    }
}
