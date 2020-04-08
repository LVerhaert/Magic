import {Component, OnInit} from '@angular/core';
import {Observable, Subject} from 'rxjs';

import {debounceTime, distinctUntilChanged, switchMap} from 'rxjs/operators';

import {MagicCard} from '../model/magiccard';
import {MagicCardService} from '../services/magiccard.service';

@Component({
  selector: 'app-magiccard-search',
  templateUrl: './magiccard-search.component.html',
  styleUrls: ['./magiccard-search.component.css']
})
export class MagicCardSearchComponent implements OnInit {
  magicCards$: Observable<MagicCard[]>;
  private searchTerms = new Subject<string>();

  constructor(private magicCardService: MagicCardService) {
  }

  search(term: string): void {
    this.searchTerms.next(term);
  }

  ngOnInit(): void {
    this.magicCards$ = this.searchTerms.pipe(
      debounceTime(300), distinctUntilChanged(),
      switchMap((term: string) => this.magicCardService.searchMagicCards(term))
    );
  }

}
