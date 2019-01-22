export class DomainBase {
  id;
}

export enum Operation {
  READ = 'READ',
  CREATE = 'CREATE',
  SELECT = 'SELECT',
  UPDATE = 'UPDATE',
  DELETE = 'DELETE',
  REPORT = 'REPORT',
  FILTER = 'FILTER'
}
export class FieldSearch {

  constructor(_name: string, _label: string, _type:string){
    this.name = _name;
    this.label = _label;
    this.type = _type;
  }
  name: string;
  label: string;
  type: string;

}

export enum DataType{
  ENUM, NUMBER, STRING, INTEGER, BEAN, DATETIME, BOOLEAN, DOUBLE, LONG, DATE
}

export enum MatchType{
  EQUALS,
  NOT_EQUAL,
  GREATER,
  GREATER_EQUAL,
  LESS,
  LESS_EQUAL,
  BETWEEN,
  LIKE,
  LIKE_START,
  LIKE_END,
  IN,
  NOT_IN,
  NOT_LIKE
}
