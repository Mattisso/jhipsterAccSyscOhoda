export interface INstBalanceInput {
  id?: string;
  numCompte?: string;
  intitulCompte?: string;
  soldeDebit?: number;
  soldeCredit?: number;
  compteNumber?: string;
}

export class NstBalanceInput implements INstBalanceInput {
  constructor(
    public id?: string,
    public numCompte?: string,
    public intitulCompte?: string,
    public soldeDebit?: number,
    public soldeCredit?: number,
    public compteNumber?: string
  ) {}
}
