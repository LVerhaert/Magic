export enum Language {
  en,
  es,
  fr,
  de,
  it,
  pt,
  ja,
  ko,
  ru,
  zhs,
  zht,
  he,
  la,
  grc,
  ar,
  sa,
  px
}

export enum Relationship {
  token,
  meld_part,
  meld_result,
  combo_piece
}

export enum Color {
  blue,
  green,
  white,
  red,
  black

}

export enum Layout {
  normal,
  split,
  flip,
  transform,
  meld,
  leveler,
  saga,
  adventure,
  planar,
  scheme,
  vanguard,
  token,
  double_faced_token,
  emblem,
  augment,
  host,
  art_series,
  double_sided
}

export enum BorderColor {
  black,
  borderless,
  gold,
  silver,
  white

}

export enum Frame {
  y1993,
  y1997,
  y2003,
  y2015,
  future
}

export enum FrameEffect {
  legendary,
  miracle,
  nyxtouched,
  nyxborn,
  draft,
  devoid,
  tombstone,
  colorshifted,
  inverted,
  sunmoondfc,
  compasslanddfc,
  originpwdfc,
  mooneldrazidfc,
  showcase,
  waxingandwaningmoondfc,
  extendedart
}

export enum Game {
  paper,
  arena,
  mtgo
}

export enum Legality {
  legal = 'legal',
  not_legal = 'not legal',
  restricted = 'restricted',
  banned = 'banned'
}
