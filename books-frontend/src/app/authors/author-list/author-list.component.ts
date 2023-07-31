import { Component } from '@angular/core';
import { AuthorsFrontendService } from '../service/frontend/authors-frontend.service';
import { Observable, finalize } from 'rxjs';
import { Author } from '../model/author';

@Component({
  selector: 'app-author-list',
  templateUrl: './author-list.component.html',
  styleUrls: ['./author-list.component.css']
})
export class AuthorListComponent {

  spinnerLoading : boolean = true;

  dataSource$ :Observable<Author[]> = this.service.authors$.pipe(
    finalize(() => this.spinnerLoading = false)
  );
  displayedColumns : string[] = ["id", "authorName"];

  constructor(private service : AuthorsFrontendService) { }

}
