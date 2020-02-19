import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INstBalanceInput } from 'app/shared/model/nst-balance-input.model';

@Component({
  selector: 'jhi-nst-balance-input-detail',
  templateUrl: './nst-balance-input-detail.component.html'
})
export class NstBalanceInputDetailComponent implements OnInit {
  nstBalanceInput: INstBalanceInput | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ nstBalanceInput }) => (this.nstBalanceInput = nstBalanceInput));
  }

  previousState(): void {
    window.history.back();
  }
}
