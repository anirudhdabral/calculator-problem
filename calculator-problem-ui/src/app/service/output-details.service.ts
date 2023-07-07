import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Output } from '../model/Output';
import uri_string from '../model/URI';

@Injectable({
  providedIn: 'root'
})
export class OutputDetailsService {
  uri = uri_string

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
