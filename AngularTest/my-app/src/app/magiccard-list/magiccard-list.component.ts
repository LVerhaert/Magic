import {Component, OnInit} from '@angular/core';
import {MagicCard} from '../magiccard';
import {MagicCardService} from '../magiccard.service';

@Component({
  selector: 'app-magiccard-list',
  templateUrl: './magiccard-list.component.html',
  styleUrls: ['./magiccard-list.component.scss']
})
export class MagicCardListComponent implements OnInit {

  magiccards: MagicCard[];

  constructor(private magiccardService: MagicCardService) {
  }

  ngOnInit() {
    this.magiccardService.findAll().subscribe(data => {
      this.magiccards = data;
    });
  }

}
