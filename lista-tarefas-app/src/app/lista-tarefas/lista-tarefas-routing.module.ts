import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ListaTarefasFormComponent } from './lista-tarefas-form/lista-tarefas-form.component';
import { ListaTarefasListaComponent } from './lista-tarefas-lista/lista-tarefas-lista.component';

const routes: Routes = [
  {path:'lista-tarefas-form', component:ListaTarefasFormComponent},
  {path:'lista-tarefas-form/:id', component:ListaTarefasFormComponent},
  {path:'lista-tarefas-lista', component:ListaTarefasListaComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ListaTarefasRoutingModule { }
