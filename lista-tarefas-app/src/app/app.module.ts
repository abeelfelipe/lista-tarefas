import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { TemplateModule } from './template/template.module';
import { HomeComponent } from './home/home.component'
import { ListaTarefasModule } from './lista-tarefas/lista-tarefas.module';
import { ListaTarefasService } from './lista-tarefas.service';
import { TarefaService } from './tarefa.service';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    TemplateModule,
    ListaTarefasModule,
    HttpClientModule
  ],
  providers: [
    ListaTarefasService,
    TarefaService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
