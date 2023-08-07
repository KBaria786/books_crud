import { Component, AfterViewInit, ViewChild } from '@angular/core';
import { PublishersFrontendService } from '../service/frontend/publishers-frontend.service';
import { EMPTY, Observable, startWith, finalize, concatMap, map } from 'rxjs';
import { Publisher } from '../model/publisher';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-publisher-list',
  templateUrl: './publisher-list.component.html',
  styleUrls: ['./publisher-list.component.css']
})
export class PublisherListComponent implements AfterViewInit {

  spinnerLoading : boolean = true;

  dataSource$ : Observable<Publisher[]> = EMPTY;
  displayedColumns : string[] = ["id", "publisherName"];

  resultsLength : number = 0;
  @ViewChild(MatPaginator) paginator !: MatPaginator;

  constructor(private service : PublishersFrontendService) { }

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
