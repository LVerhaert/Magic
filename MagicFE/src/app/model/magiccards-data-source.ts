import {CollectionViewer, DataSource} from '@angular/cdk/collections';
import {MagicCard} from './magiccard';
import {BehaviorSubject, Observable, of} from 'rxjs';
import {MagicCardService} from '../services/magiccard.service';
import {catchError, finalize} from 'rxjs/operators';

// export class CardsDataSource implements DataSource<MagicCard> {
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

  loadMagicCards(pageIndex: number = 1, pageSize: number) {
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

}
