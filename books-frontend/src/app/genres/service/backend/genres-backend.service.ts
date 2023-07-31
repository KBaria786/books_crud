import { Injectable } from '@angular/core';
import { Genre } from '../../model/genre';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GenresBackendService {

  private baseUrl : string = "http://localhost:8080/genres";

  constructor(private http : HttpClient) { }

  //save genre
  save(genre : Genre) : Observable<Genre> {
    return this.http.post<Genre>(this.baseUrl, genre);
  }

  //find all genres
  findAll() : Observable<Genre[]> {
    return this.http.get<Genre[]>(this.baseUrl);
  }

  //find genre by id
  findById(id : number) : Observable<Genre> {
    return this.http.get<Genre>(`${this.baseUrl}/${id}`);
  }

  //update genre by id
  update(id : number, genre : Genre) {
    return this.http.put<Genre>(`${this.baseUrl}/${id}`, genre);
  }

  //mark genre as deleted
  softDelete(id : number) {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }

  //delete genre
  hardDelete(id : number) {
    return this.http.delete<void>(`${this.baseUrl}/delete/${id}`);
  }
  
}
