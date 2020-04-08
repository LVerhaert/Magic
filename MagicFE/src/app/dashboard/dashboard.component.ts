import {Component, OnInit} from '@angular/core';
import {MagicCard} from '../model/magiccard';
import {MagicCardService} from '../services/magiccard.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  magicCards: MagicCard[] = [];

  constructor(private magicCardService: MagicCardService) {
  }

  ngOnInit(): void {
    this.getMagicCards();
  }

  getMagicCards(): void {
    this.magicCardService.getMagicCards().subscribe(magicCards => this.magicCards = magicCards.slice(1, 5));
  }

}
