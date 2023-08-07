import { Component, ViewChild, AfterViewInit } from '@angular/core';
import { BooksFrontendService } from '../service/frontend/books-frontend.service';
import { Book } from '../model/book';
import { Observable, finalize, map, startWith, switchMap } from 'rxjs';
import { MatPaginator, PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css']
})
export class BookListComponent implements AfterViewInit {

  spinnerLoading : boolean = true;

  // dataSource$ : Observable<Book[]> = this.service.books$.pipe(
  //   finalize(() => this.spinnerLoading = false)
  // );
  dataSource : Book[] = [];
  displayedColumns : string[] = ["id", "isbn13", "title", "publicationDate", "authors", "genres", "publisher"];

  resultsLength : number = 0;
  @ViewChild(MatPaginator) paginator !: MatPaginator;

  constructor(private service : BooksFrontendService) { }

  ngAfterViewInit(): void {
    this.paginator.page.pipe(
      startWith({}),
      switchMap(() => this.service.findAllWithPagination(this.paginator.pageIndex, this.paginator.pageSize)),
      map((response) => {
        let totalCount = response.headers.get("X-Total-Count");
        this.resultsLength = totalCount ? +totalCount : 0;
        return response.body ? response.body : [];
      })
    ).subscribe({
      next: (books) => {
        this.dataSource = books;
        this.spinnerLoading = false;
      },
      complete : () => console.log("books fetched")
    });
  }

}
