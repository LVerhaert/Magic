// import {BorderColor, Color, Frame, FrameEffect, Language, Layout, Relationship} from './enums';
import {Game} from './enums';
import {CardFace} from './card-face';
import {Legalities} from './legalities';

export interface MagicCard {

  name: string;
  typeLine: string;
  oracleText: string;
  cardFaces: CardFace[];
  legalities: Legalities;
  games: Game[];
  smallImageUri: string;
  largeImageUri: string;

  // artist: string;
  // flavorText: string;
  // scryfallUri: string;
  // setId: string;
  // scryfallId: string;
  // relatedCards: Map<string, Relationship>;
  // artistIds: string[];
  // illustrationId: string;
  // language: Language;
  // oracleId: string;
  // convManaCost: number;
  // colors: Color[];
  // colorIdentity: Color[];
  // colorIndicator: Color[];
  // handModifier: string;
  // layout: Layout;
  // lifeModifier: string;
  // loyalty: string;
  // manaCost: string;
  // power: string;
  // toughness: string;
  // borderColor: BorderColor;
  // cardBackId: string;
  // frame: Frame;
  // frameEffects: FrameEffect[];

}

