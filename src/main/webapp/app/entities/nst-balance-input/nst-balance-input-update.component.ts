import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { INstBalanceInput, NstBalanceInput } from 'app/shared/model/nst-balance-input.model';
import { NstBalanceInputService } from './nst-balance-input.service';

@Component({
  selector: 'jhi-nst-balance-input-update',
  templateUrl: './nst-balance-input-update.component.html'
})
export class NstBalanceInputUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    numCompte: [null, [Validators.required]],
    intitulCompte: [null, [Validators.required]],
    soldeDebit: [],
    soldeCredit: [],
    compteNumber: []
  });

  constructor(
    protected nstBalanceInputService: NstBalanceInputService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ nstBalanceInput }) => {
      this.updateForm(nstBalanceInput);
    });
  }

  updateForm(nstBalanceInput: INstBalanceInput): void {
    this.editForm.patchValue({
      id: nstBalanceInput.id,
      numCompte: nstBalanceInput.numCompte,
      intitulCompte: nstBalanceInput.intitulCompte,
      soldeDebit: nstBalanceInput.soldeDebit,
      soldeCredit: nstBalanceInput.soldeCredit,
      compteNumber: nstBalanceInput.compteNumber
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const nstBalanceInput = this.createFromForm();
    if (nstBalanceInput.id !== undefined) {
      this.subscribeToSaveResponse(this.nstBalanceInputService.update(nstBalanceInput));
    } else {
      this.subscribeToSaveResponse(this.nstBalanceInputService.create(nstBalanceInput));
    }
  }

  private createFromForm(): INstBalanceInput {
    return {
      ...new NstBalanceInput(),
      id: this.editForm.get(['id'])!.value,
      numCompte: this.editForm.get(['numCompte'])!.value,
      intitulCompte: this.editForm.get(['intitulCompte'])!.value,
      soldeDebit: this.editForm.get(['soldeDebit'])!.value,
      soldeCredit: this.editForm.get(['soldeCredit'])!.value,
      compteNumber: this.editForm.get(['compteNumber'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<INstBalanceInput>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
