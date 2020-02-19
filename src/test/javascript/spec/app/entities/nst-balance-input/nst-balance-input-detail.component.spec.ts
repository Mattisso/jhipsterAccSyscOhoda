import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterAccSyscOhodaTestModule } from '../../../test.module';
import { NstBalanceInputDetailComponent } from 'app/entities/nst-balance-input/nst-balance-input-detail.component';
import { NstBalanceInput } from 'app/shared/model/nst-balance-input.model';

describe('Component Tests', () => {
  describe('NstBalanceInput Management Detail Component', () => {
    let comp: NstBalanceInputDetailComponent;
    let fixture: ComponentFixture<NstBalanceInputDetailComponent>;
    const route = ({ data: of({ nstBalanceInput: new NstBalanceInput('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterAccSyscOhodaTestModule],
        declarations: [NstBalanceInputDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(NstBalanceInputDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(NstBalanceInputDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load nstBalanceInput on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.nstBalanceInput).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
