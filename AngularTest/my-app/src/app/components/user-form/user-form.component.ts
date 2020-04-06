import {Component} from '@angular/core';
import {MagicCard} from '../../model/magiccard';
import {ActivatedRoute, Router} from '@angular/router';
import {MagicCardService} from '../../services/magiccard.service';

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.scss']
})
export class UserFormComponent {

  magiccard: MagicCard;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private magiccardService: MagicCardService
  ) {
    this.magiccard = new MagicCard();
  }

  onSubmit() {
    this.magiccardService.save(this.magiccard).subscribe(result => this.gotoMagicCardList());
  }

  gotoMagicCardList() {
    this.router.navigate(['/magiccards']);
  }


}
