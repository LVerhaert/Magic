import {Component, OnInit} from '@angular/core';
import {Player} from '../model/player';
import {ActivatedRoute} from '@angular/router';
import {PlayerService} from '../services/player.service';

@Component({
  selector: 'app-player-detail',
  templateUrl: './player-detail.component.html',
  styleUrls: ['./player-detail.component.scss']
})
export class PlayerDetailComponent implements OnInit {

  player: Player;
  mainCollection = [{data: 'Full Collection'}];

  constructor(private route: ActivatedRoute,
              private playerService: PlayerService) {
  }

  /*
  Extract the playerId from the url and find the matching player
   */
  ngOnInit(): void {
    const id = +this.route.snapshot.paramMap.get('playerid');
    this.playerService.getPlayer(id).subscribe(player => this.player = player);
  }

}

/*
Helper class for showing the row content correctly
 */
export interface TableData {
  data: string;
}

