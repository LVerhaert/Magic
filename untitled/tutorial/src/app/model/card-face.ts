import {Color} from "./enums";

export interface CardFace {
  id: number;
  artist: string;
  artistId: string;
  colorIndicator: Color[];
  colors: Color[];
  flavorText: string;
  illustrationId: string;
  smallImageUri: string;
  largeImageUri: string;
  loyalty: string;
  manaCost: string;
  name: string;
  oracleText: string;
  power: string;
  toughness: string;
  typeLine: string;
}
