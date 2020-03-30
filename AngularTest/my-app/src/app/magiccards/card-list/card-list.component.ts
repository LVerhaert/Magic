import {Component, OnInit} from '@angular/core';
import {MagicCard} from '../magiccard';
import {MagicCardService} from '../magiccard.service';
import {Observable} from 'rxjs';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-card-list',
  templateUrl: './card-list.component.html',
  styleUrls: ['./card-list.component.scss']
})
export class CardListComponent implements OnInit {
  magiccards$: Observable<MagicCard[]>;
  magiccards: MagicCard[];
  selectedId: string;

  constructor(private magicCardService: MagicCardService,
              private route: ActivatedRoute) {
  }

  ngOnInit() {
    // this.magiccards$ = this.route.paramMap.pipe(
    //   switchMap(params => {
    //     this.selectedId = params.get('id');
    //     return this.magicCardService.findAll();
    //   })
    // );
    this.magicCardService.findAll().subscribe(data => {
      this.magiccards = data;
    });
  }

}
