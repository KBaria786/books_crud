import { Component } from '@angular/core';
import { GenresFrontendService } from '../service/frontend/genres-frontend.service';
import { Observable, finalize } from 'rxjs';
import { Genre } from '../model/genre';

@Component({
  selector: 'app-genre-list',
  templateUrl: './genre-list.component.html',
  styleUrls: ['./genre-list.component.css']
})
export class GenreListComponent {

  spinnerLoading : boolean = true;

  dataSource$ : Observable<Genre[]> = this.service.genres$.pipe(
    finalize(() => this.spinnerLoading = false)
  );
  displayedColumns : string[] = ["id", "genreName"];

  constructor(private service : GenresFrontendService)  { }

}
