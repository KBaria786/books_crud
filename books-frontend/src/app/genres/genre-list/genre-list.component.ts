import { AfterViewInit, Component, ViewChild } from '@angular/core';
import { GenresFrontendService } from '../service/frontend/genres-frontend.service';
import { EMPTY, Observable, concatMap, finalize, map, startWith } from 'rxjs';
import { Genre } from '../model/genre';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-genre-list',
  templateUrl: './genre-list.component.html',
  styleUrls: ['./genre-list.component.css']
})
export class GenreListComponent implements AfterViewInit {

  spinnerLoading : boolean = true;

  // dataSource$ : Observable<Genre[]> = this.service.genres$.pipe(
  //   finalize(() => this.spinnerLoading = false)
  // );
  dataSource$ : Observable<Genre[]> = EMPTY;
  displayedColumns : string[] = ["id", "genreName"];

  resultsLength : number = 0;
  @ViewChild(MatPaginator) paginator !: MatPaginator;

  constructor(private service : GenresFrontendService)  { }

  ngAfterViewInit(): void {
    this.dataSource$ = this.paginator.page.pipe(
      startWith({}),
      concatMap(() => this.service.findAllWithPagination(this.paginator.pageIndex, this.paginator.pageSize)),
      map(response => {
        let totalCount = response.headers.get("X-Total-Count");
        this.resultsLength = totalCount ? +totalCount : 0;
        this.spinnerLoading = false;
        return response.body ? response.body : [];
      })
    );
  }

}
