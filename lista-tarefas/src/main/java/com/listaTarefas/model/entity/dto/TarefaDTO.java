package com.listaTarefas.model.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TarefaDTO {
    private String nomeTarefa;
    private String concluido;
    private String dataUltimaAlteracao;
    private Integer idLista;

}
