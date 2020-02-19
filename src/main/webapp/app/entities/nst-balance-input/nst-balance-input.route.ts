import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { INstBalanceInput, NstBalanceInput } from 'app/shared/model/nst-balance-input.model';
import { NstBalanceInputService } from './nst-balance-input.service';
import { NstBalanceInputComponent } from './nst-balance-input.component';
import { NstBalanceInputDetailComponent } from './nst-balance-input-detail.component';
import { NstBalanceInputUpdateComponent } from './nst-balance-input-update.component';

@Injectable({ providedIn: 'root' })
export class NstBalanceInputResolve implements Resolve<INstBalanceInput> {
  constructor(private service: NstBalanceInputService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<INstBalanceInput> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((nstBalanceInput: HttpResponse<NstBalanceInput>) => {
          if (nstBalanceInput.body) {
            return of(nstBalanceInput.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new NstBalanceInput());
  }
}

export const nstBalanceInputRoute: Routes = [
  {
    path: '',
    component: NstBalanceInputComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'jhipsterAccSyscOhodaApp.nstBalanceInput.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: NstBalanceInputDetailComponent,
    resolve: {
      nstBalanceInput: NstBalanceInputResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterAccSyscOhodaApp.nstBalanceInput.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: NstBalanceInputUpdateComponent,
    resolve: {
      nstBalanceInput: NstBalanceInputResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterAccSyscOhodaApp.nstBalanceInput.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: NstBalanceInputUpdateComponent,
    resolve: {
      nstBalanceInput: NstBalanceInputResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterAccSyscOhodaApp.nstBalanceInput.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
