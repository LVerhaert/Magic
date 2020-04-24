import {TestBed} from "@angular/core/testing";

import {MagicCardService} from "./magiccard.service";

describe("MagicCardService", () => {
  let service: MagicCardService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MagicCardService);
  });

  it("should be created", () => {
    expect(service).toBeTruthy();
  });
});
