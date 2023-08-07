import { HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Author } from '../../model/author';
import { AuthorsBackendService } from '../backend/authors-backend.service';

@Injectable({
  providedIn: 'root'
})
export class AuthorsFrontendService {

  authors$ : Observable<Author[]> = this.service.findAll();

  constructor(private service : AuthorsBackendService) { }

  findAllWithPagination(page : number, limit : number) : Observable<HttpResponse<Author[]>> {
    return this.service.findAllWithPagination(page, limit);
  }

}
