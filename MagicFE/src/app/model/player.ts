import {MainCollection} from "./main-collection";
import {Deck} from "./deck";

export interface Player {
  id: number;
  name: string;
  mainCollection: MainCollection;
  decks: Deck[];

}
