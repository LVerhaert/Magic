import {Component, Input, OnInit} from '@angular/core';
import {MagicCard} from '../model/magiccard';
import {ActivatedRoute} from '@angular/router';
import {Location} from '@angular/common';
import {MagicCardService} from '../services/magiccard.service';

@Component({
  selector: 'app-magiccard-detail',
  templateUrl: './magiccard-detail.component.html',
  styleUrls: ['./magiccard-detail.component.css']
})
export class MagicCardDetailComponent implements OnInit {

  @Input() magicCard: MagicCard;

  constructor(private route: ActivatedRoute, private magicCardService: MagicCardService, private location: Location) {
  }

  ngOnInit(): void {
    this.getMagicCard();
  }

  getMagicCard(): void {
    const id = this.route.snapshot.paramMap.get('id');
    this.magicCardService.getMagicCard(id).subscribe(magicCard => this.magicCard = magicCard);
  }

  goBack(): void {
    this.location.back();
  }

}
