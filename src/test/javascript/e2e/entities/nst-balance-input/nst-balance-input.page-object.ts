import { element, by, ElementFinder } from 'protractor';

export class NstBalanceInputComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-nst-balance-input div table .btn-danger'));
  title = element.all(by.css('jhi-nst-balance-input div h2#page-heading span')).first();
  noResult = element(by.id('no-result'));
  entities = element(by.id('entities'));

  async clickOnCreateButton(): Promise<void> {
    await this.createButton.click();
  }

  async clickOnLastDeleteButton(): Promise<void> {
    await this.deleteButtons.last().click();
  }

  async countDeleteButtons(): Promise<number> {
    return this.deleteButtons.count();
  }

  async getTitle(): Promise<string> {
    return this.title.getAttribute('jhiTranslate');
  }
}

export class NstBalanceInputUpdatePage {
  pageTitle = element(by.id('jhi-nst-balance-input-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  numCompteInput = element(by.id('field_numCompte'));
  intitulCompteInput = element(by.id('field_intitulCompte'));
  soldeDebitInput = element(by.id('field_soldeDebit'));
  soldeCreditInput = element(by.id('field_soldeCredit'));
  compteNumberInput = element(by.id('field_compteNumber'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setNumCompteInput(numCompte: string): Promise<void> {
    await this.numCompteInput.sendKeys(numCompte);
  }

  async getNumCompteInput(): Promise<string> {
    return await this.numCompteInput.getAttribute('value');
  }

  async setIntitulCompteInput(intitulCompte: string): Promise<void> {
    await this.intitulCompteInput.sendKeys(intitulCompte);
  }

  async getIntitulCompteInput(): Promise<string> {
    return await this.intitulCompteInput.getAttribute('value');
  }

  async setSoldeDebitInput(soldeDebit: string): Promise<void> {
    await this.soldeDebitInput.sendKeys(soldeDebit);
  }

  async getSoldeDebitInput(): Promise<string> {
    return await this.soldeDebitInput.getAttribute('value');
  }

  async setSoldeCreditInput(soldeCredit: string): Promise<void> {
    await this.soldeCreditInput.sendKeys(soldeCredit);
  }

  async getSoldeCreditInput(): Promise<string> {
    return await this.soldeCreditInput.getAttribute('value');
  }

  async setCompteNumberInput(compteNumber: string): Promise<void> {
    await this.compteNumberInput.sendKeys(compteNumber);
  }

  async getCompteNumberInput(): Promise<string> {
    return await this.compteNumberInput.getAttribute('value');
  }

  async save(): Promise<void> {
    await this.saveButton.click();
  }

  async cancel(): Promise<void> {
    await this.cancelButton.click();
  }

  getSaveButton(): ElementFinder {
    return this.saveButton;
  }
}

export class NstBalanceInputDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-nstBalanceInput-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-nstBalanceInput'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
