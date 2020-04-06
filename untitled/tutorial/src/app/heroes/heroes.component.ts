import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {Hero} from '../model/hero';
import {HeroService} from "../services/hero.service";
import {MatPaginator} from "@angular/material/paginator";
import {HeroesDataSource} from "../model/heroes-data-source";
import {tap} from "rxjs/operators";

@Component({
  selector: 'app-heroes',
  templateUrl: './heroes.component.html',
  styleUrls: ['./heroes.component.css']
})
export class HeroesComponent implements OnInit, AfterViewInit {

  hero: Hero;
  displayedColumns: string[] = ['image', 'type', 'name', 'id'];
  dataSource: HeroesDataSource;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private heroService: HeroService) {
  }

  ngAfterViewInit(): void {
    this.dataSource.counter$.pipe(
      tap((count) => {
        this.paginator.length = count;
      })
    ).subscribe();
    this.paginator.page.pipe(
      tap(() => this.loadHeroesPage())
    ).subscribe();
  }

  ngOnInit(): void {
    this.dataSource = new HeroesDataSource(this.heroService);
    this.paginator.pageSize = 20;
    this.loadHeroesPage();
  }


  private loadHeroesPage() {
    this.dataSource.loadHeroes(this.paginator.pageIndex, this.paginator.pageSize);
  }
}
