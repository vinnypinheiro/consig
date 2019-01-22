export class LoginRequest {
  constructor(
    public cpf: string,
    public password: string,
    public entidade: string,
    public unidadeGestora: string,) {
  }
}
