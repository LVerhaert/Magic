import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MagicCardService} from '../services/magiccard.service';
import {MatPaginator} from '@angular/material/paginator';
import {MagicCardsDataSource} from '../datasources/magiccards-data-source';
import {tap} from 'rxjs/operators';
import {ActivatedRoute} from '@angular/router';
import {PlayerService} from '../services/player.service';
import {Deck} from '../model/deck';
import {Player} from '../model/player';

@Component({
  selector: 'app-magiccards',
  templateUrl: './magiccards.component.html',
  styleUrls: ['./magiccards.component.scss']
})
/*
List of cards out of all cards, a player's full collection, or a specific deck
 */
export class MagicCardsComponent implements OnInit, AfterViewInit {

  playerId: string;
  player: Player;
  deckId: string;
  deck: Deck;
  displayedColumns: string[] = ['image', 'name', 'type'];
  dataSource: MagicCardsDataSource;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private route: ActivatedRoute,
              private magicCardService: MagicCardService,
              private playerService: PlayerService) {
  }

  /*
  Extract the playerId and deckId from the url and initiate the datasource. If there is a playerId and/or deckId, find
  the matching player and/or deck
   */
  ngOnInit(): void {
    this.playerId = this.route.snapshot.paramMap.get('playerid');
    this.deckId = this.route.snapshot.paramMap.get('deckid');
    if (this.deckId != null) {
      this.playerService.getDeck(+this.playerId, +this.deckId).subscribe(deck => this.deck = deck);
    }
    if (this.playerId != null) {
      this.playerService.getPlayer(+this.playerId).subscribe(player => this.player = player);
    }
    this.dataSource = new MagicCardsDataSource(this.magicCardService);
  }

  /*
  Load a page of cards and paginate
   */
  ngAfterViewInit(): void {
    this.loadMagicCardsPage();
    this.paginator.pageSize = 20;
    this.dataSource.counter$.pipe(
      tap((count) => {
        this.paginator.length = count;
      })
    ).subscribe();
    this.paginator.page.pipe(
      tap(() => this.loadMagicCardsPage())
    ).subscribe();
  }

  /*
  Load a page of cards
   */
  private loadMagicCardsPage() {
    if (this.playerId == null) { // cards from all cards
      this.dataSource.loadMagicCards(this.paginator.pageIndex, this.paginator.pageSize);
    } else if (this.deckId == null) { // cards from the main collection
      this.dataSource.loadMagicCardsMainColl(this.paginator.pageIndex, this.paginator.pageSize, +this.playerId);
    } else { // cards from the deck
      this.dataSource.loadMagicCardsDeck(this.paginator.pageIndex, this.paginator.pageSize, +this.playerId, +this.deckId);
    }
  }

}
