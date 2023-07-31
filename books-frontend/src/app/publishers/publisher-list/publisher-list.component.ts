import { Component } from '@angular/core';
import { PublishersFrontendService } from '../service/frontend/publishers-frontend.service';
import { Observable, finalize } from 'rxjs';
import { Publisher } from '../model/publisher';

@Component({
  selector: 'app-publisher-list',
  templateUrl: './publisher-list.component.html',
  styleUrls: ['./publisher-list.component.css']
})
export class PublisherListComponent {

  spinnerLoading : boolean = true;

  dataSource$ : Observable<Publisher[]> = this.service.publishers$.pipe(
    finalize(() => this.spinnerLoading = false)
  );
  displayedColumns : string[] = ["id", "publisherName"];

  constructor(private service : PublishersFrontendService) { }

}
