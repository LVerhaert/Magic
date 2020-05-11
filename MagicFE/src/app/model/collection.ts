import {MagicCard} from './magiccard';
import {Player} from "./player";

export interface Collection {
  id: number;
  magicCardIds: string[];
}
