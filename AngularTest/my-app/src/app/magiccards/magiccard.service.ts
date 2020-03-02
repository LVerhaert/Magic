import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {MagicCard} from './magiccard';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';


@Injectable(/*{
  // providedIn: 'root'
}*/)
export class MagicCardService {

  private magiccardsUrl: string;
  private magiccardUrl: string;

  constructor(private http: HttpClient) {
    this.magiccardsUrl = 'http://localhost:8080/magiccards';
    this.magiccardUrl = 'http://localhost:8080/magiccard/:id';
  }

  public findAll(): Observable<MagicCard[]> {
    return this.http.get<MagicCard[]>(this.magiccardsUrl);
  }

  public find(id: string): Observable<MagicCard> {
    return this.findAll().pipe(
      map((magiccards: MagicCard[]) => magiccards.find(magiccard => magiccard.id === id))
    );
  }

  // public find() {
  //   return this.http.get<MagicCard>(this.magiccardUrl);
  // }

  public save(magiccard: MagicCard) {
    return this.http.post<MagicCard>(this.magiccardsUrl, magiccard);
  }
}
