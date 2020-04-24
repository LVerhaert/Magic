import {AfterViewInit, Component, OnInit, ViewChild} from "@angular/core";
import {MatPaginator} from "@angular/material/paginator";
import {Player} from "../model/player";
import {ActivatedRoute} from "@angular/router";
import {PlayerService} from "../services/player.service";
import {Location} from "@angular/common";
import {tap} from "rxjs/operators";
import {DecksDataSource} from "../datasources/decks-data-source";

@Component({
  selector: "app-player-detail",
  templateUrl: "./player-detail.component.html",
  styleUrls: ["./player-detail.component.scss"]
})
export class PlayerDetailComponent implements OnInit, AfterViewInit {

  decksDataSource: DecksDataSource;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  player: Player;

  constructor(private route: ActivatedRoute,
              private playerService: PlayerService,
              private location: Location) {
  }

  ngOnInit(): void {
    this.getPlayer();
    this.decksDataSource = new DecksDataSource(this.playerService);
  }

  ngAfterViewInit(): void {
    this.loadPlayerDecksPage();
    this.paginator.pageSize = 20;
    this.decksDataSource.counter$.pipe(
      tap((count) => {
        this.paginator.length = count;
      })
    ).subscribe();
    this.paginator.page.pipe(
      tap(() => this.loadPlayerDecksPage())
    ).subscribe();
  }

  getPlayer(): void {
    const id = +this.route.snapshot.paramMap.get("playerid");
    this.playerService.getPlayer(id).subscribe(player => this.player = player);
  }

  goBack(): void {
    this.location.back();
  }

  private loadPlayerDecksPage() {
    this.decksDataSource.loadDecks(this.paginator.pageIndex, this.paginator.pageSize, this.player.id);
  }
}
