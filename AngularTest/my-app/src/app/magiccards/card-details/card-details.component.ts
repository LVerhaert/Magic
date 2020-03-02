import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {MagicCard} from '../magiccard';
import {ActivatedRoute, ParamMap, Router} from '@angular/router';
import {MagicCardService} from '../magiccard.service';
import {switchMap} from 'rxjs/operators';

@Component({
  selector: 'app-card-details',
  templateUrl: './card-details.component.html',
  styleUrls: ['./card-details.component.scss']
})
export class MagicCardDetailsComponent implements OnInit {

  magiccard$: Observable<MagicCard>;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: MagicCardService
  ) {
  }

  ngOnInit(): void {
    this.magiccard$ = this.route.paramMap.pipe(
      switchMap((params: ParamMap) =>
        this.service.find(params.get('id')))
    );
  }

  goToMagicCards(magicCard: MagicCard) {
    let magicCardId = magicCard ? magicCard.id : null;
    this.router.navigate(['/magiccard', {id: magicCardId}]);
  }
}
