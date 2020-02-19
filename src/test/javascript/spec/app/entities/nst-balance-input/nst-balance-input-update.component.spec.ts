import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterAccSyscOhodaTestModule } from '../../../test.module';
import { NstBalanceInputUpdateComponent } from 'app/entities/nst-balance-input/nst-balance-input-update.component';
import { NstBalanceInputService } from 'app/entities/nst-balance-input/nst-balance-input.service';
import { NstBalanceInput } from 'app/shared/model/nst-balance-input.model';

describe('Component Tests', () => {
  describe('NstBalanceInput Management Update Component', () => {
    let comp: NstBalanceInputUpdateComponent;
    let fixture: ComponentFixture<NstBalanceInputUpdateComponent>;
    let service: NstBalanceInputService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterAccSyscOhodaTestModule],
        declarations: [NstBalanceInputUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(NstBalanceInputUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(NstBalanceInputUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(NstBalanceInputService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new NstBalanceInput('123');
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new NstBalanceInput();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
