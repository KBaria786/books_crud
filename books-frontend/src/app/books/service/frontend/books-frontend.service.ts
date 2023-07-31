import { Injectable } from '@angular/core';
import { BooksBackendService } from '../backend/books-backend.service';
import { Observable, of } from 'rxjs';
import { Book } from '../../model/book';
import { HttpResponse } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class BooksFrontendService {

  books$ : Observable<Book[]> = this.service.findAll();

  constructor(private service : BooksBackendService) { }

  findAllWithPagination(page : number, limit : number) : Observable<HttpResponse<Book[]>> {
    return this.service.findAllWithPagination(page, limit)
  }

}
