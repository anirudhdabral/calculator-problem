import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RiskScoreLevel } from '../model/RiskScoreLevel';

@Injectable({
  providedIn: 'root'
})
export class RiskScoreLevelService {
  uri = "http://localhost:5055"

  constructor(private http: HttpClient) { }

  getAllRiskScoreLevel(): Observable<RiskScoreLevel> {
    return this.http.get<RiskScoreLevel>(this.uri + "/getAllRiskScoreLevel");
  }

  getRiskScoreLevel(score: string): Observable<RiskScoreLevel> {
    return this.http.get<RiskScoreLevel>(this.uri + "/getRiskScoreLevel/" + score);
  }

  addRiskScoreLevel(riskScoreLevel: RiskScoreLevel): Observable<RiskScoreLevel> {
    return this.http.post<RiskScoreLevel>(this.uri + "/addRiskScoreLevel", riskScoreLevel);
  }

  updateRiskScoreLevel(riskScoreLevel: RiskScoreLevel): Observable<RiskScoreLevel> {
    return this.http.put<RiskScoreLevel>(this.uri + "/updateRiskScoreLevel", riskScoreLevel);
  }

  deleteRiskScoreLevel(score: string): Observable<RiskScoreLevel> {
    return this.http.delete<RiskScoreLevel>(this.uri + "/deleteRiskScoreLevel/" + score);
  }

}
