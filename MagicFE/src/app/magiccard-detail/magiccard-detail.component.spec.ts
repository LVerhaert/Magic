import {async, ComponentFixture, TestBed} from "@angular/core/testing";

import {MagicCardDetailComponent} from "./magiccard-detail.component";

describe("MagicCardDetailComponent", () => {
  let component: MagicCardDetailComponent;
  let fixture: ComponentFixture<MagicCardDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [MagicCardDetailComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MagicCardDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
});
