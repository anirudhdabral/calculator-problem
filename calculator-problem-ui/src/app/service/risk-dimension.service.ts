import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RiskDimension } from '../model/RiskDimension';

@Injectable({
  providedIn: 'root'
})
export class RiskDimensionService {
  uri = "http://localhost:5055"

  constructor(private http: HttpClient) { }

  getAllRiskDimension(): Observable<RiskDimension> {
    return this.http.get<RiskDimension>(this.uri + "/getAllRiskDimension");
  }

  rebalanceWeight(riskDimensionList: any): Observable<RiskDimension> {
    return this.http.put<RiskDimension>(this.uri + "/rebalanceWeight", riskDimensionList);
  }

}
