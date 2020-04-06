import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Hero} from "../model/hero";
import {Observable, of} from "rxjs";
import {MessageService} from "./message.service";
import {catchError, tap} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class HeroService {

  private heroesUrl = 'http://localhost:8080/magiccards';
  private heroUrl = 'http://localhost:8080/magiccard';
  private heroesSearchUrl = 'http://localhost:8080/magiccards/search';

  constructor(private messageService: MessageService, private http: HttpClient) {
  }

  getHeroesOnePage(page_index = 1, page_size: number): Observable<Hero[]> {
    return this.http.get<Hero[]>(this.heroesUrl, {
      params: new HttpParams()
        .set('pageIndex', page_index.toString())
        .set('pageSize', page_size.toString())
    })
      .pipe(
        tap(_ => this.log('fetched heroes')),
        catchError(this.handleError<Hero[]>('getHeroesOnePage', [])));
  }

  getHeroes(): Observable<Hero[]> {
    return this.http.get<Hero[]>(this.heroesUrl).pipe(
      tap(_ => this.log('fetched heroes')),
      catchError(this.handleError<Hero[]>('getHeroes', [])));
  }

  getHero(id: string): Observable<Hero> {
    const url = `${this.heroUrl}/${id}`;
    return this.http.get<Hero>(url).pipe(
      tap(_ => this.log(`fetched hero id=${id}`)),
      catchError(this.handleError<Hero>(`getHero id=${id}`))
    );
  }

  // getHeroNo404<Data>(id: string): Observable<Hero> {
  //   // const url = `${this.heroUrl}/?id=${id}`;
  //   return this.http.get<Hero[]>(this.heroesSearchUrl, {
  //     params: new HttpParams()
  //       .set('id', id)
  //   }).pipe(
  //     map(heroes => heroes[0]),
  //     tap(h => {
  //       const outcome = h ? `fetched` : `did not find`;
  //       this.log(`${outcome} hero id=${id}`);
  //     }),
  //     catchError(this.handleError<Hero>(`getHero id=${id}`))
  //   );
  // }

  searchHeroes(term: string): Observable<Hero[]> {
    if (!term.trim()) {
      return of([]);
    }
    return this.http.get<Hero[]>(this.heroesSearchUrl, {
      params: new HttpParams()
        .set('name', term)
    }).pipe(
      tap(x => x.length ?
        this.log(`found heroes matching "${term}"`) :
        this.log(`no heroes matching "${term}"`)),
      catchError(this.handleError<Hero[]>('searchHeroes', []))
    );
  }

  private log(message: string) {
    this.messageService.add(`Hero Service: ${message}`);
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      this.log(`${operation} failed: ${error.message}`);
      return of(result as T);
    }
  }

}
