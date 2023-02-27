import { Component, OnInit } from '@angular/core';
import { ListaTarefasService } from 'src/app/lista-tarefas.service';
import { ListaTarefas } from '../../model/ListaTarefas';
import { Router } from '@angular/router';
import { Tarefa } from '../Tarefa';
import { TarefaService } from 'src/app/tarefa.service';

@Component({
  selector: 'app-lista-tarefas-lista',
  templateUrl: './lista-tarefas-lista.component.html',
  styleUrls: ['./lista-tarefas-lista.component.css']
})
export class ListaTarefasListaComponent implements OnInit {

  listasTarefa: ListaTarefas[] = [];
  listaSelecionada: ListaTarefas;
  tarefas: Tarefa[] = [];

  constructor(private service: ListaTarefasService, private router: Router, private serviceTarefa: TarefaService) { 
    this.listaSelecionada = new ListaTarefas;
  }

  ngOnInit(): void {
    this.service.getListaTarefas()
    .subscribe( resposta => this.listasTarefa = resposta);
  }

  novoCadastro(){
    this.router.navigate(['/lista-tarefas-form']);
  }
}
