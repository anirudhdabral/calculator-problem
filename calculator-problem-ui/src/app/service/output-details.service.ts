import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Output } from '../model/Output';

@Injectable({
  providedIn: 'root'
})
export class OutputDetailsService {
  uri = "http://localhost:5055"

  constructor(private http: HttpClient) { }

  getAllOutputTable(): Observable<Output> {
    return this.http.get<Output>(this.uri + "/getAllOutputTable");
  }

  getOutputTable(companyName: string): Observable<Output> {
    return this.http.get<Output>(this.uri + "/getOutputTable/" + companyName);
  }

  getAllVariable(): any {
    return this.http.get(this.uri + "/getAllVariable");
  }

  calculateOutput(): any {
    return this.http.get(this.uri + "/calculateOutputTable");
  }

}
