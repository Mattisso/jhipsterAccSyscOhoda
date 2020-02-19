import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterAccSyscOhodaSharedModule } from 'app/shared/shared.module';
import { NstBalanceInputComponent } from './nst-balance-input.component';
import { NstBalanceInputDetailComponent } from './nst-balance-input-detail.component';
import { NstBalanceInputUpdateComponent } from './nst-balance-input-update.component';
import { NstBalanceInputDeleteDialogComponent } from './nst-balance-input-delete-dialog.component';
import { nstBalanceInputRoute } from './nst-balance-input.route';

@NgModule({
  imports: [JhipsterAccSyscOhodaSharedModule, RouterModule.forChild(nstBalanceInputRoute)],
  declarations: [
    NstBalanceInputComponent,
    NstBalanceInputDetailComponent,
    NstBalanceInputUpdateComponent,
    NstBalanceInputDeleteDialogComponent
  ],
  entryComponents: [NstBalanceInputDeleteDialogComponent]
})
export class JhipsterAccSyscOhodaNstBalanceInputModule {}
