import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Publisher } from '../../model/publisher';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PublishersBackendService {
  
  private baseUrl : string = "http://localhost:8080/publishers";

  constructor(private http : HttpClient) { }

  //save publisher
  save(publisher : Publisher) : Observable<Publisher> {
    return this.http.post<Publisher>(this.baseUrl, publisher);
  }

  //find all publishers
  findAll() : Observable<Publisher[]> {
    return this.http.get<Publisher[]>(this.baseUrl);
  }

  //find all publishers with pagination
  findAllWithPagination(page : number, limit : number) : Observable<HttpResponse<Publisher[]>> {
    let httpParams : HttpParams = new HttpParams();
    httpParams = httpParams.append("page", page >= 0 ? page : 0);
    httpParams = httpParams.append("limit", limit >= 0 ? limit : 10);
    return this.http.get<Publisher[]>(`${this.baseUrl}/paginated`, {params : httpParams, observe : "response"});
  }

  //find publisher by id
  findById(id : number) : Observable<Publisher> {
    return this.http.get<Publisher>(`${this.baseUrl}/${id}`);
  }

  //update publisher by id
  update(id : number, publisher : Publisher) {
    return this.http.put<Publisher>(`${this.baseUrl}/${id}`, publisher);
  }

  //mark publisher as deleted
  softDelete(id : number) {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }

  //delete publisher
  hardDelete(id : number) {
    return this.http.delete<void>(`${this.baseUrl}/delete/${id}`);
  }

}
