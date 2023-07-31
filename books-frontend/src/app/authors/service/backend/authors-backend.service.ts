import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Author } from '../../model/author';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthorsBackendService {

  private baseUrl : string = "http://localhost:8080/authors";

  constructor(private http : HttpClient) { }

  //save author
  save(author : Author) : Observable<Author> {
    return this.http.post<Author>(this.baseUrl, author);
  }

  //find all authors
  findAll() : Observable<Author[]> {
    return this.http.get<Author[]>(this.baseUrl);
  }

  //find author by id
  findById(id : number) : Observable<Author> {
    return this.http.get<Author>(`${this.baseUrl}/${id}`);
  }

  //update author by id
  update(id : number, author : Author) {
    return this.http.put<Author>(`${this.baseUrl}/${id}`, author);
  }

  //mark author as deleted
  softDelete(id : number) {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }

  //delete author
  hardDelete(id : number) {
    return this.http.delete<void>(`${this.baseUrl}/delete/${id}`);
  }

}
