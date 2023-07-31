import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { GenresRoutingModule } from './genres-routing.module';
import { SharedModule } from '../shared/shared.module';
import { GenreListComponent } from './genre-list/genre-list.component';


@NgModule({
  declarations: [
    GenreListComponent
  ],
  imports: [
    CommonModule,
    GenresRoutingModule,
    SharedModule
  ],
  exports: [
    GenreListComponent
  ]
})
export class GenresModule { }
