import {Injectable} from '@angular/core';
import {MessageService} from './message.service';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {Player} from '../model/player';
import {catchError, tap} from 'rxjs/operators';
import {Deck} from '../model/deck';

@Injectable({
  providedIn: 'root'
})
/*
Handles all logic concerning the player and collection data
 */
export class PlayerService {

  private playersUrl = 'http://localhost:8080/players'; // url prefix for accessing player data
  private collectionsUrl = 'http://localhost:8080/player'; // url prefix for accessing collection data

  constructor(private messageService: MessageService, private http: HttpClient) {
  }

  /*
  Load players, one page
   */
  getPlayersOnePage(pageIndex: number, pageSize: number): Observable<Player[]> {
    return this.http.get<Player[]>(this.playersUrl, {
      params: new HttpParams()
        .set('pageIndex', pageIndex.toString())
        .set('pageSize', pageSize.toString())
    })
      .pipe(
        tap(_ => this.log(`fetched players (index=${pageIndex}, size=${pageSize})`)),
        catchError(this.handleError<Player[]>('getPlayersOnePage', [])));
  }

  /*
  Load details of player with id playerId
   */
  getPlayer(playerId: number): Observable<Player> {
    const url = `${this.collectionsUrl}/${playerId}`;
    return this.http.get<Player>(url).pipe(
      tap(_ => this.log(`fetched player (playerId=${playerId})`)),
      catchError(this.handleError<Player>(`getPlayer id=${playerId}`))
    );
  }

  /*
  Load decks of player with id playerId, one page
   */
  getDecksOnePage(pageIndex: number, pageSize: number, playerId: number) {
    const url = `${this.collectionsUrl}/${playerId}/decks`;
    return this.http.get<Deck[]>(url, {
      params: new HttpParams()
        .set('pageIndex', pageIndex.toString())
        .set('pageSize', pageSize.toString())
    })
      .pipe(
        tap(_ => this.log(`fetched decks (index=${pageIndex}, size=${pageSize})`)),
        catchError(this.handleError<Deck[]>('getDecksOnePage', [])));
  }

  /*
  Load details of deck with id deckId of player with id playerId
   */
  getDeck(playerId: number, deckId: number): Observable<Deck> {
    const url = `${this.collectionsUrl}/${playerId}/deck/${deckId}`;
    return this.http.get<Deck>(url).pipe(
      tap(_ => this.log(`fetched deck (playerId=${playerId}, deckId=${deckId})`)),
      catchError(this.handleError<Deck>(`getDeck id=${deckId}`))
    );
  }

  /*
  Log results in the message service
   */
  private log(message: string) {
    this.messageService.add(`PlayerService: ${message}`);
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
