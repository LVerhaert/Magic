import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {MagiccardsSearchComponent} from './magiccards-search.component';

describe('MagiccardsSearchComponent', () => {
  let component: MagiccardsSearchComponent;
  let fixture: ComponentFixture<MagiccardsSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [MagiccardsSearchComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MagiccardsSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
