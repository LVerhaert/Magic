import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {MagicCard} from './magiccard';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';


@Injectable(/*{
  // providedIn: 'root'
}*/)
export class MagicCardService {

  private cardListUrl: string;
  private cardDetailsUrl: string;
  private cardRelatedUrl: string;

  constructor(private http: HttpClient) {
    this.cardListUrl = 'http://localhost:8080/magiccards';
    this.cardDetailsUrl = 'http://localhost:8080/magiccard/{id}';
    this.cardRelatedUrl = 'http://localhost:8080/magiccard/{id}/related';
  }

  public findAll(): Observable<MagicCard[]> {
    return this.http.get<MagicCard[]>(this.cardListUrl);
  }

  public find(id: string): Observable<MagicCard> {
    return this.findAll().pipe(
      map((magiccards: MagicCard[]) => magiccards.find(magiccard => magiccard.scryfallId === id))
    );
  }

  // public find() {
  //   return this.http.get<MagicCard>(this.magiccardUrl);
  // }

  public save(magiccard: MagicCard) {
    return this.http.post<MagicCard>(this.cardListUrl, magiccard);
  }
}
