import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {MagicCardSearchComponent} from './magiccard-search.component';

describe('MagicCardSearchComponent', () => {
  let component: MagicCardSearchComponent;
  let fixture: ComponentFixture<MagicCardSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [MagicCardSearchComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MagicCardSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
