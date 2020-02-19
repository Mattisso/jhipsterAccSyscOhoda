import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption, SearchWithPagination } from 'app/shared/util/request-util';
import { INstBalanceInput } from 'app/shared/model/nst-balance-input.model';

type EntityResponseType = HttpResponse<INstBalanceInput>;
type EntityArrayResponseType = HttpResponse<INstBalanceInput[]>;

@Injectable({ providedIn: 'root' })
export class NstBalanceInputService {
  public resourceUrl = SERVER_API_URL + 'api/nst-balance-inputs';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/nst-balance-inputs';

  constructor(protected http: HttpClient) {}

  create(nstBalanceInput: INstBalanceInput): Observable<EntityResponseType> {
    return this.http.post<INstBalanceInput>(this.resourceUrl, nstBalanceInput, { observe: 'response' });
  }

  update(nstBalanceInput: INstBalanceInput): Observable<EntityResponseType> {
    return this.http.put<INstBalanceInput>(this.resourceUrl, nstBalanceInput, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<INstBalanceInput>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<INstBalanceInput[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: SearchWithPagination): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<INstBalanceInput[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
