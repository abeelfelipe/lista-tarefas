package com.listaTarefas.model.repository;

import com.listaTarefas.model.entity.ListaTarefas;
import com.listaTarefas.model.entity.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {

    List<Tarefa> findAllByListaTarefas(ListaTarefas listaTarefas);
}
