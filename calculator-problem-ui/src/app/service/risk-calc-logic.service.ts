import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RiskCalcLogic } from '../model/RiskCalcLogic';

@Injectable({
  providedIn: 'root'
})
export class RiskCalcLogicService {
  uri = "http://localhost:5055"

  constructor(private http: HttpClient) { }

  getAllRiskCalcLogic(): Observable<RiskCalcLogic> {
    return this.http.get<RiskCalcLogic>(this.uri + "/getAllRiskCalcLogic");
  }

  getRiskCalcLogic(elementName: string): Observable<RiskCalcLogic> {
    return this.http.get<RiskCalcLogic>(this.uri + "/getRiskCalcLogic/" + elementName);
  }

  addRiskCalcLogic(riskCalcLogic: RiskCalcLogic): Observable<RiskCalcLogic> {
    return this.http.post<RiskCalcLogic>(this.uri + "/addRiskCalcLogic", riskCalcLogic);
  }

  updateRiskCalcLogic(updatedRiskCalcLogic: RiskCalcLogic): Observable<RiskCalcLogic> {
    return this.http.put<RiskCalcLogic>(this.uri + "/updateRiskCalcLogic", updatedRiskCalcLogic);
  }

  deleteRiskCalcLogic(elementName: string): Observable<RiskCalcLogic> {
    return this.http.delete<RiskCalcLogic>(this.uri + "/deleteRiskCalcLogic/" + elementName);
  }

}
