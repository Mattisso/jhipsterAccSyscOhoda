import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { NstBalanceInputService } from 'app/entities/nst-balance-input/nst-balance-input.service';
import { INstBalanceInput, NstBalanceInput } from 'app/shared/model/nst-balance-input.model';

describe('Service Tests', () => {
  describe('NstBalanceInput Service', () => {
    let injector: TestBed;
    let service: NstBalanceInputService;
    let httpMock: HttpTestingController;
    let elemDefault: INstBalanceInput;
    let expectedResult: INstBalanceInput | INstBalanceInput[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(NstBalanceInputService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new NstBalanceInput('ID', 'AAAAAAA', 'AAAAAAA', 0, 0, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find('123').subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a NstBalanceInput', () => {
        const returnedFromService = Object.assign(
          {
            id: 'ID'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new NstBalanceInput()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a NstBalanceInput', () => {
        const returnedFromService = Object.assign(
          {
            numCompte: 'BBBBBB',
            intitulCompte: 'BBBBBB',
            soldeDebit: 1,
            soldeCredit: 1,
            compteNumber: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of NstBalanceInput', () => {
        const returnedFromService = Object.assign(
          {
            numCompte: 'BBBBBB',
            intitulCompte: 'BBBBBB',
            soldeDebit: 1,
            soldeCredit: 1,
            compteNumber: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a NstBalanceInput', () => {
        service.delete('123').subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
