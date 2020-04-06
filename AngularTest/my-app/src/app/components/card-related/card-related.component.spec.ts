import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CardRelatedComponent } from './card-related.component';

describe('CardRelatedComponent', () => {
  let component: CardRelatedComponent;
  let fixture: ComponentFixture<CardRelatedComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CardRelatedComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CardRelatedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
