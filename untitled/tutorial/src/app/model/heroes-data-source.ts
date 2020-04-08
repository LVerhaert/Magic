import {CollectionViewer, DataSource} from "@angular/cdk/collections";
import {Hero} from "./hero";
import {BehaviorSubject, Observable, of} from "rxjs";
import {HeroService} from "../services/hero.service";
import {catchError, finalize} from "rxjs/operators";

// export class HeroesDataSource implements DataSource<Hero> {
export class HeroesDataSource extends DataSource<Hero> {
  private heroesSubject = new BehaviorSubject<Hero[]>([]);

  private countSubject = new BehaviorSubject<number>(0);
  public counter$ = this.countSubject.asObservable();

  private loadingSubject = new BehaviorSubject<boolean>(false);
  public loading$ = this.loadingSubject.asObservable();

  constructor(private heroService: HeroService) {
    super();
  }

  connect(collectionViewer: CollectionViewer): Observable<Hero[] | ReadonlyArray<Hero>> {
    return this.heroesSubject.asObservable();
  }

  disconnect(collectionViewer: CollectionViewer): void {
    this.heroesSubject.complete();
    this.loadingSubject.complete();
    this.countSubject.complete();
  }

  loadHeroes(pageIndex: number = 1, pageSize: number) {
    this.loadingSubject.next(true);

    this.heroService.getHeroesOnePage(pageIndex, pageSize)
      .pipe(
        catchError(() => of([])),
        finalize(() => this.loadingSubject.next(false))
      ).subscribe((pagingresult: any) => {
        this.heroesSubject.next(pagingresult.data);
        this.countSubject.next(pagingresult.total);
      }
    );
  }

}
