import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { NstBalanceInputComponentsPage, NstBalanceInputDeleteDialog, NstBalanceInputUpdatePage } from './nst-balance-input.page-object';

const expect = chai.expect;

describe('NstBalanceInput e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let nstBalanceInputComponentsPage: NstBalanceInputComponentsPage;
  let nstBalanceInputUpdatePage: NstBalanceInputUpdatePage;
  let nstBalanceInputDeleteDialog: NstBalanceInputDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load NstBalanceInputs', async () => {
    await navBarPage.goToEntity('nst-balance-input');
    nstBalanceInputComponentsPage = new NstBalanceInputComponentsPage();
    await browser.wait(ec.visibilityOf(nstBalanceInputComponentsPage.title), 5000);
    expect(await nstBalanceInputComponentsPage.getTitle()).to.eq('jhipsterAccSyscOhodaApp.nstBalanceInput.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(nstBalanceInputComponentsPage.entities), ec.visibilityOf(nstBalanceInputComponentsPage.noResult)),
      1000
    );
  });

  it('should load create NstBalanceInput page', async () => {
    await nstBalanceInputComponentsPage.clickOnCreateButton();
    nstBalanceInputUpdatePage = new NstBalanceInputUpdatePage();
    expect(await nstBalanceInputUpdatePage.getPageTitle()).to.eq('jhipsterAccSyscOhodaApp.nstBalanceInput.home.createOrEditLabel');
    await nstBalanceInputUpdatePage.cancel();
  });

  it('should create and save NstBalanceInputs', async () => {
    const nbButtonsBeforeCreate = await nstBalanceInputComponentsPage.countDeleteButtons();

    await nstBalanceInputComponentsPage.clickOnCreateButton();

    await promise.all([
      nstBalanceInputUpdatePage.setNumCompteInput('numCompte'),
      nstBalanceInputUpdatePage.setIntitulCompteInput('intitulCompte'),
      nstBalanceInputUpdatePage.setSoldeDebitInput('5'),
      nstBalanceInputUpdatePage.setSoldeCreditInput('5'),
      nstBalanceInputUpdatePage.setCompteNumberInput('compteNumber')
    ]);

    expect(await nstBalanceInputUpdatePage.getNumCompteInput()).to.eq('numCompte', 'Expected NumCompte value to be equals to numCompte');
    expect(await nstBalanceInputUpdatePage.getIntitulCompteInput()).to.eq(
      'intitulCompte',
      'Expected IntitulCompte value to be equals to intitulCompte'
    );
    expect(await nstBalanceInputUpdatePage.getSoldeDebitInput()).to.eq('5', 'Expected soldeDebit value to be equals to 5');
    expect(await nstBalanceInputUpdatePage.getSoldeCreditInput()).to.eq('5', 'Expected soldeCredit value to be equals to 5');
    expect(await nstBalanceInputUpdatePage.getCompteNumberInput()).to.eq(
      'compteNumber',
      'Expected CompteNumber value to be equals to compteNumber'
    );

    await nstBalanceInputUpdatePage.save();
    expect(await nstBalanceInputUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await nstBalanceInputComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last NstBalanceInput', async () => {
    const nbButtonsBeforeDelete = await nstBalanceInputComponentsPage.countDeleteButtons();
    await nstBalanceInputComponentsPage.clickOnLastDeleteButton();

    nstBalanceInputDeleteDialog = new NstBalanceInputDeleteDialog();
    expect(await nstBalanceInputDeleteDialog.getDialogTitle()).to.eq('jhipsterAccSyscOhodaApp.nstBalanceInput.delete.question');
    await nstBalanceInputDeleteDialog.clickOnConfirmButton();

    expect(await nstBalanceInputComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
