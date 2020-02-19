import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterAccSyscOhodaSharedModule } from 'app/shared/shared.module';
import { HOME_ROUTE } from './home.route';
import { HomeComponent } from './home.component';

@NgModule({
  imports: [JhipsterAccSyscOhodaSharedModule, RouterModule.forChild([HOME_ROUTE])],
  declarations: [HomeComponent]
})
export class JhipsterAccSyscOhodaHomeModule {}
