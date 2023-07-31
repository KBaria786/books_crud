import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Book } from '../../model/book';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BooksBackendService {

  private baseUrl: string = "http://localhost:8080/books";

  constructor(private http: HttpClient) { }

  //save book
  save(book: Book): Observable<Book> {
    return this.http.post<Book>(this.baseUrl, book);
  }

  //find all books
  findAll(): Observable<Book[]> {
    return this.http.get<Book[]>(this.baseUrl);
  }

  //find all books with pagination
  findAllWithPagination(page : number, limit : number): Observable<HttpResponse<Book[]>> {
    let httpParams : HttpParams = new HttpParams();
    httpParams = httpParams.append("page", page >= 0 ? page : 0);
    httpParams = httpParams.append("limit", limit >= 0 ? limit : 10);
    return this.http.get<Book[]>(`${this.baseUrl}/paginated`, {params : httpParams, observe : 'response'});
  }

  //find book by id
  findById(id: number): Observable<Book> {
    return this.http.get<Book>(`${this.baseUrl}/${id}`);
  }

  //filter books
  filter(title?: string, isbn13?: string, numPages?: number, pubicationDate?: string, publisherId?: number, publisherName?: string, authorName?: string, genreName?: string, titleLike?: string, isbn13Like?: string, publisherNameLike?: string, authorNameLike?: string, genreNameLike?: string, authorsIn?: number[], genresIn?: number[]): Observable<Book[]> {
    let httpParams : HttpParams = new HttpParams();
    httpParams = title ? httpParams.append("title", title) : httpParams;
    httpParams = isbn13 ? httpParams.append("isbn13", isbn13) : httpParams;
    httpParams = numPages ? httpParams.append("num_pages", numPages) : httpParams;
    httpParams = pubicationDate ? httpParams.append("publication_date", pubicationDate) : httpParams;
    httpParams = publisherId ? httpParams.append("publisher_id", publisherId) : httpParams;
    httpParams = publisherName ? httpParams.append("publisher_name", publisherName) : httpParams;
    httpParams = authorName ? httpParams.append("author_name", authorName) : httpParams;
    httpParams = genreName ? httpParams.append("genre_name", genreName) : httpParams;
    httpParams = titleLike ? httpParams.append("title_like", titleLike) : httpParams;
    httpParams = isbn13Like ? httpParams.append("isbn13_like", isbn13Like) : httpParams;
    httpParams = publisherNameLike ? httpParams.append("publisher_name_like", publisherNameLike) : httpParams;
    httpParams = authorNameLike ? httpParams.append("author_name_like", authorNameLike) : httpParams;
    httpParams = genreNameLike ? httpParams.append("genre_name_like", genreNameLike) : httpParams;
    httpParams = authorsIn ? httpParams.append("authors_in", authorsIn.toString()) : httpParams;
    httpParams = genresIn ? httpParams.append("genres_in", genresIn.toString()) : httpParams;

    return this.http.get<Book[]>(`${this.baseUrl}/search`, {params : httpParams});
  }

  //update book by id
  update(id: number, book: Book) {
    return this.http.put<Book>(`${this.baseUrl}/${id}`, book);
  }

  //mark book as deleted
  softDelete(id: number) {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }

  //delete book
  hardDelete(id: number) {
    return this.http.delete<void>(`${this.baseUrl}/delete/${id}`);
  }

}
