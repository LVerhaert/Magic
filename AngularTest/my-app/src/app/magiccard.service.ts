import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {MagicCard} from './magiccard';
import {Observable} from 'rxjs';


@Injectable(/*{
//  providedIn: 'root'
}*/)
export class MagicCardService {

  private magiccardsUrl: string;

  constructor(private http: HttpClient) {
    this.magiccardsUrl = 'http://localhost:8080/magiccards';
  }

  public findAll(): Observable<MagicCard[]> {
    return this.http.get<MagicCard[]>(this.magiccardsUrl);
  }

  public save(magiccard: MagicCard) {
    return this.http.post<MagicCard>(this.magiccardsUrl, magiccard);
  }
}
