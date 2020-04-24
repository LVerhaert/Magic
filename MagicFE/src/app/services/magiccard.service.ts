import {Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {MagicCard} from "../model/magiccard";
import {Observable, of} from "rxjs";
import {MessageService} from "./message.service";
import {catchError, tap} from "rxjs/operators";

@Injectable({
  providedIn: "root"
})
export class MagicCardService {

  private magicCardsUrl = "http://localhost:8080/magiccards";
  private magicCardUrl = "http://localhost:8080/magiccard";
  private magicCardsSearchUrl = "http://localhost:8080/magiccards/search";
  private playerUrl = "http://localhost:8080/player";
  private deckUrl = "deck";
  private mainCollUrl = "maincollection";

  constructor(private messageService: MessageService, private http: HttpClient) {
  }

  getMagicCardsOnePage(pageIndex: number, pageSize: number): Observable<MagicCard[]> {
    return this.http.get<MagicCard[]>(this.magicCardsUrl, {
      params: new HttpParams()
        .set("pageIndex", pageIndex.toString())
        .set("pageSize", pageSize.toString())
    })
      .pipe(
        tap(_ => this.log("fetched magicCards")),
        catchError(this.handleError<MagicCard[]>("getMagicCardsOnePage", [])));
  }

  getMagicCardsMainCollOnePage(pageIndex: number, pageSize: number, playerId: number): Observable<MagicCard[]> {
    const url = `${this.playerUrl}/${playerId}/${this.mainCollUrl}`;
    return this.http.get<MagicCard[]>(this.magicCardsUrl, {
      params: new HttpParams()
        .set("pageIndex", pageIndex.toString())
        .set("pageSize", pageSize.toString())
    })
      .pipe(
        tap(_ => this.log("fetched magicCards")),
        catchError(this.handleError<MagicCard[]>("getMagicCardsOnePage", [])));
  }

  getMagicCardsDeckOnePage(pageIndex: number, pageSize: number, playerId: number, deckId: number) {
    return this.http.get<MagicCard[]>(this.magicCardsUrl, {
      params: new HttpParams()
        .set("pageIndex", pageIndex.toString())
        .set("pageSize", pageSize.toString())
    })
      .pipe(
        tap(_ => this.log("fetched magicCards")),
        catchError(this.handleError<MagicCard[]>("getMagicCardsOnePage", [])));
  }

  getMagicCards(): Observable<MagicCard[]> {
    return this.http.get<MagicCard[]>(this.magicCardsUrl).pipe(
      tap(_ => this.log("fetched magicCards")),
      catchError(this.handleError<MagicCard[]>("getMagicCards", [])));
  }

  getMagicCard(id: string): Observable<MagicCard> {
    const url = `${this.magicCardUrl}/${id}`;
    return this.http.get<MagicCard>(url).pipe(
      tap(_ => this.log(`fetched magicCard id=${id}`)),
      catchError(this.handleError<MagicCard>(`getMagicCard id=${id}`))
    );
  }


  searchMagicCards(term: string): Observable<MagicCard[]> {
    if (!term.trim()) {
      return of([]);
    }
    return this.http.get<MagicCard[]>(this.magicCardsSearchUrl, {
      params: new HttpParams()
        .set("name", term)
    }).pipe(
      tap(x => x.length ?
        this.log(`found magicCards matching "${term}"`) :
        this.log(`no magicCards matching "${term}"`)),
      catchError(this.handleError<MagicCard[]>("searchMagicCards", []))
    );
  }

  private log(message: string) {
    this.messageService.add(`MagicCardService: ${message}`);
  }

  private handleError<T>(operation = "operation", result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      this.log(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }

}
