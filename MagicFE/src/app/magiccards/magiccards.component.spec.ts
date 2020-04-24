import {async, ComponentFixture, TestBed} from "@angular/core/testing";

import {MagicCardsComponent} from "./magiccards.component";

describe("CardsComponent", () => {
  let component: MagicCardsComponent;
  let fixture: ComponentFixture<MagicCardsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [MagicCardsComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MagicCardsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
});
