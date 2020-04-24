import {BorderColor, Color, Frame, FrameEffect, Game, Language, Layout, Relationship} from "./enums";
import {CardFace} from "./card-face";
import {Legalities} from "./legalities";

export interface MagicCard {

  // Show
  name: string;
  typeLine: string;

  oracleText: string;
  artist: string;
  flavorText: string;

  cardFaces: CardFace[];
  legalities: Legalities;
  games: Game[];

  // Link
  scryfallUri: string;
  setId: string;
  scryfallId: string;
  relatedCards: Map<string, Relationship>;
  artistIds: string[];
  illustrationId: string;
  smallImageUri: string;
  largeImageUri: string;

  // Search
  language: Language;
  oracleId: string;
  convManaCost: number;
  colors: Color[];
  colorIdentity: Color[];
  colorIndicator: Color[];
  handModifier: string;
  layout: Layout;
  lifeModifier: string;
  loyalty: string;
  manaCost: string;
  power: string;
  toughness: string;
  borderColor: BorderColor;
  cardBackId: string;
  frame: Frame;
  frameEffects: FrameEffect[];

}

