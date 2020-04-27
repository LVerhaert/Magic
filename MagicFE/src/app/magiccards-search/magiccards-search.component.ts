import {Component, OnInit} from '@angular/core';
import {Observable, Subject} from "rxjs";
import {MagicCard} from "../model/magiccard";
import {MagicCardService} from "../services/magiccard.service";
import {debounceTime, distinctUntilChanged, switchMap} from "rxjs/operators";
import {ActivatedRoute} from "@angular/router";
import {Player} from "../model/player";
import {Deck} from "../model/deck";
import {PlayerService} from "../services/player.service";

@Component({
  selector: 'app-magiccards-search',
  templateUrl: './magiccards-search.component.html',
  styleUrls: ['./magiccards-search.component.scss']
})
/*
Search the list of Magic cards by entering (part of) its name
 */
export class MagiccardsSearchComponent implements OnInit {

  playerId: string;
  player: Player;
  deckId: string;
  deck: Deck;
  magicCards$: Observable<MagicCard[]>;
  private searchTerms = new Subject<string>();

  constructor(private magicCardService: MagicCardService,
              private route: ActivatedRoute,
              private playerService: PlayerService) {
  }

  /*
  Extract the playerId and deckId from the url and activate the right search action
   */
  ngOnInit(): void {
    this.playerId = this.route.snapshot.paramMap.get('playerid');
    this.deckId = this.route.snapshot.paramMap.get('deckid');
    if (this.deckId != null) { // a deckId means the deck should be searched
      this.searchDeckCards();
    } else if (this.playerId != null) { // a playerId but no deckId means the main collection should be searched
      this.searchMainCollCards();
    } else { // no playerId and no deckId means the complete database of cards should be searched
      this.searchAllCards();
    }
  }

  /*
  Extract the search term from the search field
   */
  search(searchTerm: string): void {
    this.searchTerms.next(searchTerm);
  }

  /*
  Search the specific deck, every 300 ms, only if the search terms have changed
   */
  private searchDeckCards() {
    this.playerService.getDeck(+this.playerId, +this.deckId).subscribe(deck => this.deck = deck);
    this.magicCards$ = this.searchTerms.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      switchMap((searchTerm: string) => this.magicCardService.searchMagicCardsDeck(searchTerm, this.playerId, this.deckId)),
    );
  }

  /*
  Search the player's main collection, every 300 ms, only if the search terms have changed
   */
  private searchMainCollCards() {
    this.playerService.getPlayer(+this.playerId).subscribe(player => this.player = player);
    this.magicCards$ = this.searchTerms.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      switchMap((searchTerm: string) => this.magicCardService.searchMagicCardsMainColl(searchTerm, this.playerId)),
    );
  }

  /*
  Search the full database of cards, every 300 ms, only if the search terms have changed
   */
  private searchAllCards() {
    this.magicCards$ = this.searchTerms.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      switchMap((searchTerm: string) => this.magicCardService.searchMagicCards(searchTerm)),
    );
  }
}
