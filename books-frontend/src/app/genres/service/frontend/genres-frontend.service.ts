import { Injectable } from '@angular/core';
import { GenresBackendService } from '../backend/genres-backend.service';
import { Observable } from 'rxjs';
import { Genre } from '../../model/genre';

@Injectable({
  providedIn: 'root'
})
export class GenresFrontendService {

  genres$ : Observable<Genre[]> = this.service.findAll();

  constructor(private service : GenresBackendService) { }

}
