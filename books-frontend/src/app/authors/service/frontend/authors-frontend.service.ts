import { Injectable } from '@angular/core';
import { AuthorsBackendService } from '../backend/authors-backend.service';
import { Observable } from 'rxjs';
import { Author } from '../../model/author';

@Injectable({
  providedIn: 'root'
})
export class AuthorsFrontendService {

  authors$ : Observable<Author[]> = this.service.findAll();

  constructor(private service : AuthorsBackendService) { }

}
