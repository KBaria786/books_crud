import { AfterViewInit, Component, ViewChild } from '@angular/core';
import { AuthorsFrontendService } from '../service/frontend/authors-frontend.service';
import { Observable, concatMap, finalize, map, startWith } from 'rxjs';
import { Author } from '../model/author';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-author-list',
  templateUrl: './author-list.component.html',
  styleUrls: ['./author-list.component.css']
})
export class AuthorListComponent implements AfterViewInit {

  spinnerLoading : boolean = true;

  dataSource$ : Author[] = [];
  displayedColumns : string[] = ["id", "authorName"];

  resultsLength : number = 0;
  @ViewChild(MatPaginator) paginator !: MatPaginator;

  constructor(private service : AuthorsFrontendService) { }

  ngAfterViewInit(): void {
    this.paginator.page.pipe(
      startWith({}),
      concatMap(() => this.service.findAllWithPagination(this.paginator.pageIndex, this.paginator.pageSize)),
      map(response => {
        let totalCount = response.headers.get("X-Total-Count");
        this.resultsLength = totalCount ? +totalCount : 0;
        return response.body ? response.body : []
      })
    ).subscribe({
      next : authors => {
        this.dataSource$ = authors;
        this.spinnerLoading = false;
      },
      complete : () => console.log("authors fetched")
    });
  }

}
