import { Component, OnInit } from '@angular/core';
import {MagicCard} from "../magiccard";
import {MagicCardService} from '../magiccard.service';
import {Observable} from 'rxjs';
import {ActivatedRoute} from '@angular/router';


@Component({
  selector: 'app-card-related',
  templateUrl: './card-related.component.html',
  styleUrls: ['./card-related.component.scss']
})
export class CardRelatedComponent implements OnInit {
  magiccards$: Observable<MagicCard[]>;
  magiccards: MagicCard[];
  selectedId: string;

  constructor(private magicCardService: MagicCardService,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.magicCardService.findAll().subscribe(data => {
      this.magiccards = data;
    })
  }

}
