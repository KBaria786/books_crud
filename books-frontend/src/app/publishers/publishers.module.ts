import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PublishersRoutingModule } from './publishers-routing.module';
import { SharedModule } from '../shared/shared.module';
import { PublisherListComponent } from './publisher-list/publisher-list.component';


@NgModule({
  declarations: [
    PublisherListComponent
  ],
  imports: [
    CommonModule,
    PublishersRoutingModule,
    SharedModule
  ],
  exports: [
    PublisherListComponent
  ]
})
export class PublishersModule { }
