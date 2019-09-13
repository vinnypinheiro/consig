export class User {
  constructor(public id: number, public nome: string) {}
}

export interface IUserResponse {
  total: number;
  results: User[];
}
