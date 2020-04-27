import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {MagicCard} from '../model/magiccard';
import {Observable, of} from 'rxjs';
import {MessageService} from './message.service';
import {catchError, tap} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
/*
Handles all logic concerning the Magic card data
 */
export class MagicCardService {

  private magicCardsUrl = 'http://localhost:8080/magiccards'; // url prefix for accessing magiccards data
  private magicCardUrl = 'http://localhost:8080/magiccard'; // url prefix for accessing magiccard-detail data
  private magicCardsSearchUrl = 'http://localhost:8080/magiccards/search'; // url prefix for searching in magiccards
  private collectionsUrl = 'http://localhost:8080/player'; // url prefix for accessing collection data

  constructor(private messageService: MessageService, private http: HttpClient) {
  }

  /*
  Load cards from all cards, one page
   */
  getMagicCardsOnePage(pageIndex: number, pageSize: number): Observable<MagicCard[]> {
    return this.http.get<MagicCard[]>(this.magicCardsUrl, {
      params: new HttpParams()
        .set('pageIndex', pageIndex.toString())
        .set('pageSize', pageSize.toString())
    })
      .pipe(
        tap(_ => this.log('fetched magicCards  getMagicCards')),
        catchError(this.handleError<MagicCard[]>('getMagicCardsOnePage', [])));
  }

  /*
  Load cards from main collection of player with id playerId, one page
   */
  getMagicCardsMainCollOnePage(pageIndex: number, pageSize: number, playerId: number): Observable<MagicCard[]> {
    const url = `${this.collectionsUrl}/${playerId}/maincollection/magiccards`;
    return this.http.get<MagicCard[]>(url, {
      params: new HttpParams()
        .set('pageIndex', pageIndex.toString())
        .set('pageSize', pageSize.toString())
    })
      .pipe(
        tap(_ => this.log('fetched magicCards getMagicCardsMainColl: ' + url)),
        catchError(this.handleError<MagicCard[]>('getMagicCardsMainColl', [])));
  }

  /*
  Load cards from deck with id deckId of player with id playerId, one page
   */
  getMagicCardsDeckOnePage(pageIndex: number, pageSize: number, playerId: number, deckId: number) {
    const url = `${this.collectionsUrl}/${playerId}/deck/${deckId}/magiccards`;
    return this.http.get<MagicCard[]>(url, {
      params: new HttpParams()
        .set('pageIndex', pageIndex.toString())
        .set('pageSize', pageSize.toString())
    })
      .pipe(
        tap(_ => this.log('fetched magicCards getMagicCardsDeck: ' + url)),
        catchError(this.handleError<MagicCard[]>('getMagicCardsOnePage', [])));
  }

  /*
  Load details of card with id cardId
   */
  getMagicCard(cardId: string): Observable<MagicCard> {
    const url = `${this.magicCardUrl}/${cardId}`;
    return this.http.get<MagicCard>(url).pipe(
      tap(_ => this.log(`fetched magicCard id=${cardId}`)),
      catchError(this.handleError<MagicCard>(`getMagicCard id=${cardId}`))
    );
  }

  /*
  Load cards with searchTerm as part of it's name
   */
  searchMagicCards(searchTerm: string): Observable<MagicCard[]> {
    if (!searchTerm.trim()) {
      return of([]);
    }
    return this.http.get<MagicCard[]>(this.magicCardsSearchUrl, {
      params: new HttpParams()
        .set('name', searchTerm)
    }).pipe(
      tap(x => x.length ?
        this.log(`found magicCards matching "${searchTerm}"`) :
        this.log(`no magicCards matching "${searchTerm}"`)),
      catchError(this.handleError<MagicCard[]>('searchMagicCards', []))
    );
  }

  /*
  Log results in the message service
   */
  private log(message: string) {
    this.messageService.add(`MagicCardService: ${message}`);
  }

  /*
  Log errors in the console and in the message service
   */
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      this.log(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }

}
