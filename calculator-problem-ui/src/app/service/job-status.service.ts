import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { JobStatus } from '../model/JobStatus';
import uri_string from '../model/URI';

@Injectable({
  providedIn: 'root'
})
export class JobStatusService {
  uri = uri_string

  constructor(private http: HttpClient) { }

  getAllJobStatus(): Observable<JobStatus> {
    return this.http.get<JobStatus>(this.uri + "/getAllJobStatus");
  }

  resetDatabase(): any {
    return this.http.get(this.uri + "/resetDatabase");
  }

}
