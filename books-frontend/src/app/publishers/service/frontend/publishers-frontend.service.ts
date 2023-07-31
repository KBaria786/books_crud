import { Injectable } from '@angular/core';
import { PublishersBackendService } from '../backend/publishers-backend.service';
import { Observable } from 'rxjs';
import { Publisher } from '../../model/publisher';

@Injectable({
  providedIn: 'root'
})
export class PublishersFrontendService {

  publishers$ : Observable<Publisher[]> = this.service.findAll();

  constructor(private service : PublishersBackendService) { }

}
