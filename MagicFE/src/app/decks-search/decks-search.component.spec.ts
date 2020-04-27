import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {DecksSearchComponent} from './decks-search.component';

describe('DecksSearchComponent', () => {
  let component: DecksSearchComponent;
  let fixture: ComponentFixture<DecksSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [DecksSearchComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DecksSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
