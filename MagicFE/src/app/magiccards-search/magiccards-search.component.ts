import {Component, OnInit} from '@angular/core';
import {Observable, Subject} from "rxjs";
import {MagicCard} from "../model/magiccard";
import {MagicCardService} from "../services/magiccard.service";
import {debounceTime, distinctUntilChanged, switchMap} from "rxjs/operators";

@Component({
  selector: 'app-magiccards-search',
  templateUrl: './magiccards-search.component.html',
  styleUrls: ['./magiccards-search.component.scss']
})
export class MagiccardsSearchComponent implements OnInit {

  magicCards$: Observable<MagicCard[]>;
  private searchTerms = new Subject<string>();

  constructor(private magicCardService: MagicCardService) {
  }

  ngOnInit(): void {
    this.magicCards$ = this.searchTerms.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      switchMap((searchTerm: string) => this.magicCardService.searchMagicCards(searchTerm))
    );
  }

  search(searchTerm: string): void {
    this.searchTerms.next(searchTerm);
  }
}
