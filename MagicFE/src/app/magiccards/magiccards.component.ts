import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MagicCard} from '../model/magiccard';
import {MagicCardService} from '../services/magiccard.service';
import {MatPaginator} from '@angular/material/paginator';
import {MagicCardsDataSource} from '../model/magiccards-data-source';
import {tap} from 'rxjs/operators';

@Component({
  selector: 'app-magiccards',
  templateUrl: './magiccards.component.html',
  styleUrls: ['./magiccards.component.scss']
})
export class MagicCardsComponent implements OnInit, AfterViewInit {

  magicCard: MagicCard;
  displayedColumns: string[] = ['image', 'type', 'name', 'id'];
  dataSource: MagicCardsDataSource;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private magicCardService: MagicCardService) {
  }

  ngAfterViewInit(): void {
    this.loadMagicCardsPage();
    this.paginator.pageSize = 20;
    this.dataSource.counter$.pipe(
      tap((count) => {
        this.paginator.length = count;
      })
    ).subscribe();
    this.paginator.page.pipe(
      tap(() => this.loadMagicCardsPage())
    ).subscribe();
  }

  ngOnInit(): void {
    this.dataSource = new MagicCardsDataSource(this.magicCardService);
  }


  private loadMagicCardsPage() {
    this.dataSource.loadMagicCards(this.paginator.pageIndex, this.paginator.pageSize);
  }
}
