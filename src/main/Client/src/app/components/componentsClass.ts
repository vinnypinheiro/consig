export class StateButons {
  first = false;
  prior = false;
  next = false;
  last = false;
  locate = false;
  open = false;
  print = false;
  new = false;
  copy = false;
  delete = false;
  close = true;
  filter = false;
}

export enum Key {
  Tab = 9,
  Enter = 13,
  Escape = 27,
  Space = 32,
  PageUp = 33,
  PageDown = 34,
  End = 35,
  Home = 36,
  ArrowLeft = 37,
  ArrowUp = 38,
  ArrowRight = 39,
  ArrowDown = 40
}

export class LookupFieldValue {
  id: string;
  nome: string;
  private _out: string;
  private codigo: string;

  constructor(id: string, codigo: string, nome: string) {
    this.id = id;
    this.codigo = codigo;
    this.nome = nome;
  }

  get out(): string {
    return `${this.codigo} - ${this.nome}`;
  }

  set out(value: string) {
    this._out = value;
  }
}


export function toString(value: any): string {
  return (value !== undefined && value !== null) ? `${value}` : '';
}

export function getValueInRange(value: number, max: number, min = 0): number {
  return Math.max(Math.min(value, max), min);
}


