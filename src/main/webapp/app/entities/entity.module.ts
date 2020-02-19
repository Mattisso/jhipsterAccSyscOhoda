import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'nst-balance-input',
        loadChildren: () => import('./nst-balance-input/nst-balance-input.module').then(m => m.JhipsterAccSyscOhodaNstBalanceInputModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class JhipsterAccSyscOhodaEntityModule {}
