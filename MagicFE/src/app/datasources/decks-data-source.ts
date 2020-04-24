import {CollectionViewer, DataSource} from "@angular/cdk/collections";
import {BehaviorSubject, Observable, of} from "rxjs";
import {catchError, finalize} from "rxjs/operators";
import {PlayerService} from "../services/player.service";
import {Deck} from "../model/deck";

export class DecksDataSource extends DataSource<Deck> {
  private decksSubject = new BehaviorSubject<Deck[]>([]);

  private countSubject = new BehaviorSubject<number>(0);
  public counter$ = this.countSubject.asObservable();

  private loadingSubject = new BehaviorSubject<boolean>(false);
  public loading$ = this.loadingSubject.asObservable();

  constructor(private playerService: PlayerService) {
    super();
  }

  connect(collectionViewer: CollectionViewer): Observable<Deck[] | ReadonlyArray<Deck>> {
    return this.decksSubject.asObservable();
  }

  disconnect(collectionViewer: CollectionViewer): void {
    this.decksSubject.complete();
    this.loadingSubject.complete();
    this.countSubject.complete();
  }

  loadDecks(pageIndex: number, pageSize: number, playerId: number) {
    this.loadingSubject.next(true);
    this.playerService.getDecksOnePage(pageIndex, pageSize, playerId)
      .pipe(
        catchError(() => of([])),
        finalize(() => this.loadingSubject.next(false))
      ).subscribe((pagingresult: any) => {
      this.decksSubject.next(pagingresult.data);
      this.countSubject.next(pagingresult.total);
    });
  }
}
