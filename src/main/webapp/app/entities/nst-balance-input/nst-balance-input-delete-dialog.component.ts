import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { INstBalanceInput } from 'app/shared/model/nst-balance-input.model';
import { NstBalanceInputService } from './nst-balance-input.service';

@Component({
  templateUrl: './nst-balance-input-delete-dialog.component.html'
})
export class NstBalanceInputDeleteDialogComponent {
  nstBalanceInput?: INstBalanceInput;

  constructor(
    protected nstBalanceInputService: NstBalanceInputService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.nstBalanceInputService.delete(id).subscribe(() => {
      this.eventManager.broadcast('nstBalanceInputListModification');
      this.activeModal.close();
    });
  }
}
