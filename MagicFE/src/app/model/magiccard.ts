import {BorderColor, Color, Frame, FrameEffect, Game, Language, Layout, Relationship} from './enums';
import {CardFace} from './card-face';
import {Legalities} from './legalities';


export interface MagicCard {
  /////////// Core
  scryfallId: string;
  name: string;
  language: Language;
  oracleId: string;
  scryfallUri: string;
  setId: string;

  /////////// Gameplay
  relatedCards: Map<string, Relationship>;
  cardFaces: CardFace[];
  convManaCost: number;
  colors: Color[];
  colorIdentity: Color[];
  colorIndicator: Color[];
  handModifier: string;
  layout: Layout;
  lifeModifier: string;
  loyalty: string;
  manaCost: string;
  oracleText: string;
  power: string;
  toughness: string;
  typeLine: string;
  legalities: Legalities;

  ////////// Print
  artist: string;
  artistIds: string[];
  borderColor: BorderColor;
  cardBackId: string;
  flavorText: string;
  frame: Frame;
  frameEffects: FrameEffect[];
  games: Game[];
  illustrationId: string;
  smallImageUri: string;
  largeImageUri: string;
}

