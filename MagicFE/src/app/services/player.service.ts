import {Injectable} from "@angular/core";
import {MessageService} from "./message.service";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {Player} from "../model/player";
import {catchError, tap} from "rxjs/operators";
import {Deck} from "../model/deck";

@Injectable({
  providedIn: "root"
})
export class PlayerService {

  private playersUrl = "http://localhost:8080/players";
  private playerUrl = "http://localhost:8080/player";
  private decksUrl = "decks";
  private deckUrl = "deck";

  constructor(private messageService: MessageService, private http: HttpClient) {
  }

  getPlayersOnePage(pageIndex: number, pageSize: number): Observable<Player[]> {
    return this.http.get<Player[]>(this.playersUrl, {
      params: new HttpParams()
        .set("pageIndex", pageIndex.toString())
        .set("pageSize", pageSize.toString())
    })
      .pipe(
        tap(_ => this.log("fetched players, index=" + pageIndex + ", size=" + pageSize + ", players=" + this.getPlayersOnePage(0, 20))),
        catchError(this.handleError<Player[]>("getPlayersOnePage", [])));
  }

  getPlayer(id: number): Observable<Player> {
    const url = `${this.playerUrl}/${id}`;
    return this.http.get<Player>(url).pipe(
      tap(_ => this.log(`fetched player id=${id}`)),
      catchError(this.handleError<Player>(`getPlayer id=${id}`))
    );
  }

  getDecksOnePage(pageIndex: number, pageSize: number, id: number) {
    const url = `${this.playerUrl}/${id}/${this.decksUrl}`;
    return this.http.get<Deck[]>(url, {
      params: new HttpParams()
        .set("pageIndex", pageIndex.toString())
        .set("pageSize", pageSize.toString())
    })
      .pipe(
        tap(_ => this.log("fetched decks")),
        catchError(this.handleError<Deck[]>("getDecksOnePage", [])));
  }

  getDeck(playerId: number, deckId: number): Observable<Deck> {
    const url = `${this.playerUrl}/${playerId}/${this.deckUrl}/${deckId}`;
    return this.http.get<Deck>(url).pipe(
      tap(_ => this.log(`fetched player id=${playerId} deck id=${deckId}`)),
      catchError(this.handleError<Deck>(`getDeck id=${deckId}`))
    );
  }

  private log(message: string) {
    this.messageService.add(`PlayerService: ${message}`);
  }

  private handleError<T>(operation = "operation", result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      this.log(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }
}
