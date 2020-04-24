import {AfterViewInit, Component, OnInit, ViewChild} from "@angular/core";
import {MagicCardService} from "../services/magiccard.service";
import {MatPaginator} from "@angular/material/paginator";
import {MagicCardsDataSource} from "../datasources/magiccards-data-source";
import {tap} from "rxjs/operators";
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";

@Component({
  selector: "app-magiccards",
  templateUrl: "./magiccards.component.html",
  styleUrls: ["./magiccards.component.scss"]
})
export class MagicCardsComponent implements OnInit, AfterViewInit {

  // player: Player;
  // deck: Deck;
  displayedColumns: string[] = ["image", "type", "name", "id"];
  dataSource: MagicCardsDataSource;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private route: ActivatedRoute,
              private magicCardService: MagicCardService,
              private location: Location) {
  }

  ngOnInit(): void {
    this.dataSource = new MagicCardsDataSource(this.magicCardService);
  }


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


  private loadMagicCardsPage() {
    this.dataSource.loadMagicCards(this.paginator.pageIndex, this.paginator.pageSize);
  }

  private loadMagicCardsMainCollPage(playerId: number) {
    this.dataSource.loadMagicCardsMainColl(this.paginator.pageIndex, this.paginator.pageSize, playerId);
  }

  private loadMagicCardsDeckPage(playerId: number, deckId: number) {
    this.dataSource.loadMagicCardsDeck(this.paginator.pageIndex, this.paginator.pageSize, playerId, deckId);
  }
}
