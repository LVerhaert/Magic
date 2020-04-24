import {CollectionViewer, DataSource} from "@angular/cdk/collections";
import {Player} from "../model/player";
import {BehaviorSubject, Observable, of} from "rxjs";
import {catchError, finalize} from "rxjs/operators";
import {PlayerService} from "../services/player.service";

export class PlayersDataSource extends DataSource<Player> {
  private playersSubject = new BehaviorSubject<Player[]>([]);

  private countSubject = new BehaviorSubject<number>(0);
  public counter$ = this.countSubject.asObservable();

  private loadingSubject = new BehaviorSubject<boolean>(false);
  public loading$ = this.loadingSubject.asObservable();

  constructor(private playerService: PlayerService) {
    super();
  }

  connect(collectionViewer: CollectionViewer): Observable<Player[] | ReadonlyArray<Player>> {
    return this.playersSubject.asObservable();
  }

  disconnect(collectionViewer: CollectionViewer): void {
    this.playersSubject.complete();
    this.loadingSubject.complete();
    this.countSubject.complete();
  }

  loadPlayers(pageIndex: number, pageSize: number) {
    this.loadingSubject.next(true);
    this.playerService.getPlayersOnePage(pageIndex, pageSize)
      .pipe(
        catchError(() => of([])),
        finalize(() => this.loadingSubject.next(false))
      ).subscribe((pagingresult: any) => {
      this.playersSubject.next(pagingresult.data);
      this.countSubject.next(pagingresult.total);
    });
  }
}
