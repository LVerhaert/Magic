import {CollectionViewer, DataSource} from "@angular/cdk/collections";
import {MagicCard} from "../model/magiccard";
import {BehaviorSubject, Observable, of} from "rxjs";
import {MagicCardService} from "../services/magiccard.service";
import {catchError, finalize} from "rxjs/operators";

export class MagicCardsDataSource extends DataSource<MagicCard> {
  private magicCardsSubject = new BehaviorSubject<MagicCard[]>([]);

  private countSubject = new BehaviorSubject<number>(0);
  public counter$ = this.countSubject.asObservable();

  private loadingSubject = new BehaviorSubject<boolean>(false);
  public loading$ = this.loadingSubject.asObservable();

  constructor(private magicCardService: MagicCardService) {
    super();
  }

  connect(collectionViewer: CollectionViewer): Observable<MagicCard[] | ReadonlyArray<MagicCard>> {
    return this.magicCardsSubject.asObservable();
  }

  disconnect(collectionViewer: CollectionViewer): void {
    this.magicCardsSubject.complete();
    this.loadingSubject.complete();
    this.countSubject.complete();
  }

  loadMagicCards(pageIndex: number, pageSize: number) {
    this.loadingSubject.next(true);

    this.magicCardService.getMagicCardsOnePage(pageIndex, pageSize)
      .pipe(
        catchError(() => of([])),
        finalize(() => this.loadingSubject.next(false))
      ).subscribe((pagingresult: any) => {
        this.magicCardsSubject.next(pagingresult.data);
        this.countSubject.next(pagingresult.total);
      }
    );
  }

  loadMagicCardsMainColl(pageIndex: number, pageSize: number, playerId: number) {
    this.loadingSubject.next(true);

    this.magicCardService.getMagicCardsMainCollOnePage(pageIndex, pageSize, playerId)
      .pipe(
        catchError(() => of([])),
        finalize(() => this.loadingSubject.next(false))
      ).subscribe((pagingresult: any) => {
        this.magicCardsSubject.next(pagingresult.data);
        this.countSubject.next(pagingresult.total);
      }
    );
  }

  loadMagicCardsDeck(pageIndex: number, pageSize: number, playerId: number, deckId: number) {
    this.loadingSubject.next(true);

    this.magicCardService.getMagicCardsDeckOnePage(pageIndex, pageSize, playerId, deckId)
      .pipe(
        catchError(() => of([])),
        finalize(() => this.loadingSubject.next(false))
      ).subscribe((pagingresult: any) => {
        this.magicCardsSubject.next(pagingresult.data);
        this.countSubject.next(pagingresult.total);
      }
    );

  }
}
