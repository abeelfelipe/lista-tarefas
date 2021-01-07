import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ListaTarefasRoutingModule } from './lista-tarefas-routing.module';
import { ListaTarefasFormComponent } from './lista-tarefas-form/lista-tarefas-form.component';
import { FormsModule } from '@angular/forms';
import { ListaTarefasListaComponent } from './lista-tarefas-lista/lista-tarefas-lista.component';


@NgModule({
  declarations: [ListaTarefasFormComponent, ListaTarefasListaComponent],
  imports: [
    CommonModule,
    ListaTarefasRoutingModule,
    FormsModule
  ],
  exports: [
    ListaTarefasFormComponent,
    ListaTarefasListaComponent
  ]
})
export class ListaTarefasModule { }
