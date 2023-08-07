import { HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Genre } from '../../model/genre';
import { GenresBackendService } from '../backend/genres-backend.service';

@Injectable({
  providedIn: 'root'
})
export class GenresFrontendService {

  genres$ : Observable<Genre[]> = this.service.findAll();

  constructor(private service : GenresBackendService) { }

  findAllWithPagination(page : number, limit : number) : Observable<HttpResponse<Genre[]>> {
    return this.service.findAllWithPagination(page, limit);
  }

}
