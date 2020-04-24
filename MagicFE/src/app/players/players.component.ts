import {AfterViewInit, Component, OnInit, ViewChild} from "@angular/core";
import {MatPaginator} from "@angular/material/paginator";
import {tap} from "rxjs/operators";
import {PlayerService} from "../services/player.service";
import {PlayersDataSource} from "../datasources/players-data-source";

@Component({
  selector: "app-players",
  templateUrl: "./players.component.html",
  styleUrls: ["./players.component.scss"]
})
export class PlayersComponent implements OnInit, AfterViewInit {

  displayedColumns: string[] = ["id", "name"];
  dataSource: PlayersDataSource;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private playerService: PlayerService) {
  }

  ngAfterViewInit(): void {
    this.loadPlayersPage();
    this.paginator.pageSize = 20;
    this.dataSource.counter$.pipe(
      tap((count) => {
        this.paginator.length = count;
      })
    ).subscribe();
    this.paginator.page.pipe(
      tap(() => this.loadPlayersPage())
    ).subscribe();
  }

  ngOnInit(): void {
    this.dataSource = new PlayersDataSource(this.playerService);
  }

  private loadPlayersPage() {
    this.dataSource.loadPlayers(this.paginator.pageIndex, this.paginator.pageSize);
  }
}
