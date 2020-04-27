import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {DecksDataSource} from '../datasources/decks-data-source';
import {MatPaginator} from '@angular/material/paginator';
import {ActivatedRoute} from '@angular/router';
import {PlayerService} from '../services/player.service';
import {tap} from 'rxjs/operators';

@Component({
  selector: 'app-decks',
  templateUrl: './decks.component.html',
  styleUrls: ['./decks.component.scss']
})
/*
List of decks from a selected player
 */
export class DecksComponent implements OnInit, AfterViewInit {

  dataSource: DecksDataSource;
  playerId: number;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private route: ActivatedRoute,
              private playerService: PlayerService) {
  }

  /*
  Extract the playerId from the url and initiate the datasource
   */
  ngOnInit(): void {
    this.playerId = +this.route.snapshot.paramMap.get('playerid');
    this.dataSource = new DecksDataSource(this.playerService);
  }

  /*
  Load a page of decks and paginate
   */
  ngAfterViewInit(): void {
    this.loadPlayerDecksPage();
    this.paginator.pageSize = 10;
    this.dataSource.counter$.pipe(
      tap((count) => {
        this.paginator.length = count;
      })
    ).subscribe();
    this.paginator.page.pipe(
      tap(() => this.loadPlayerDecksPage())
    ).subscribe();
  }

  /*
  Load a page of decks
   */
  private loadPlayerDecksPage() {
    this.dataSource.loadDecks(this.paginator.pageIndex, this.paginator.pageSize, this.playerId);
  }
}
