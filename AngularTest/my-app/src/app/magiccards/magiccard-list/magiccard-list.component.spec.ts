import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {MagicCardListComponent} from './magiccard-list.component';

describe('MagicCardListComponent', () => {
  let component: MagicCardListComponent;
  let fixture: ComponentFixture<MagicCardListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [MagicCardListComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MagicCardListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
