import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CompanyRiskScore } from '../model/CompanyRiskScore';
import uri_string from '../model/URI';

@Injectable({
  providedIn: 'root'
})
export class CompanyRiskScoreService {
  uri = uri_string

  constructor(private http: HttpClient) { }

  getAllCompanyRiskScore(): Observable<CompanyRiskScore> {
    return this.http.get<CompanyRiskScore>(this.uri + "/getAllCompanyRiskScore");
  }

  getCompanyRiskScore(companyName: string): Observable<CompanyRiskScore> {
    return this.http.get<CompanyRiskScore>(this.uri + "/getCompanyRiskScore/" + companyName);
  }

  addCompanyRiskScore(companyRiskScore: CompanyRiskScore): Observable<CompanyRiskScore> {
    return this.http.post<CompanyRiskScore>(this.uri + "/addCompanyRiskScore", companyRiskScore)
  }

  updateCompanyRiskScore(companyRiskScore: CompanyRiskScore): Observable<CompanyRiskScore> {
    return this.http.put<CompanyRiskScore>(this.uri + "/updateCompanyRiskScore", companyRiskScore)
  }

  deleteCompanyRiskScore(companyName: string): Observable<CompanyRiskScore> {
    return this.http.delete<CompanyRiskScore>(this.uri + "/deleteCompanyRiskScore/" + companyName)
  }

}
