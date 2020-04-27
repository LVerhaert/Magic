import {AfterContentChecked, Component, OnInit} from '@angular/core';
import {MagicCard} from '../model/magiccard';
import {ActivatedRoute} from '@angular/router';
import {MagicCardService} from '../services/magiccard.service';
import {Legality} from '../model/enums';


@Component({
  selector: 'app-magiccard-detail',
  templateUrl: './magiccard-detail.component.html',
  styleUrls: ['./magiccard-detail.component.scss']
})
/*
Details of a selected card
 */
export class MagicCardDetailComponent implements OnInit, AfterContentChecked {

  magicCard: MagicCard;
  legalities: ParsedLegality[];

  constructor(private route: ActivatedRoute,
              private magicCardService: MagicCardService) {
  }

  /*
  Extract the cardId from the url and find the card
   */
  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    this.magicCardService.getMagicCard(id).subscribe(magicCard => this.magicCard = magicCard);
  }

  /*
  Parse the legalities so they can be put in a list
   */
  ngAfterContentChecked(): void {
    this.legalities = [{name: 'Brawl', value: Legality[this.magicCard.legalities.brawl]},
      {name: 'Commander', value: Legality[this.magicCard.legalities.commander]},
      {name: 'Duel', value: Legality[this.magicCard.legalities.duel]},
      {name: 'Future', value: Legality[this.magicCard.legalities.future]},
      {name: 'Historic', value: Legality[this.magicCard.legalities.historic]},
      {name: 'Legacy', value: Legality[this.magicCard.legalities.legacy]},
      {name: 'Modern', value: Legality[this.magicCard.legalities.modern]},
      {name: 'Oldschool', value: Legality[this.magicCard.legalities.oldschool]},
      {name: 'Pauper', value: Legality[this.magicCard.legalities.pauper]},
      {name: 'Penny', value: Legality[this.magicCard.legalities.penny]},
      {name: 'Pioneer', value: Legality[this.magicCard.legalities.pioneer]},
      {name: 'Standard', value: Legality[this.magicCard.legalities.standard]},
      {name: 'Vintage', value: Legality[this.magicCard.legalities.vintage]},
    ];
  }

}

/*
A helper class for parsing the legalities
 */
interface ParsedLegality {
  name: string;
  value: string;
}

