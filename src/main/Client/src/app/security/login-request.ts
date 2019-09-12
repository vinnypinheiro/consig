export class LoginRequest {
  constructor(
    public login: string,
    public password: string,
    public entidade: string,
    public unidadeGestora: string,) {
  }
}
