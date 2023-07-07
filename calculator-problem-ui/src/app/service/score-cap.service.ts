import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ScoreCap } from '../model/ScoreCap';
import uri_string from '../model/URI';

@Injectable({
  providedIn: 'root'
})
export class ScoreCapService {
  uri = uri_string

  constructor(private http: HttpClient) { }

  getAllScoreCap(): Observable<ScoreCap> {
    return this.http.get<ScoreCap>(this.uri + "/getAllScoreCap");
  }

  getScoreCap(condition: string): Observable<ScoreCap> {
    return this.http.get<ScoreCap>(this.uri + "/getScoreCap/" + condition);
  }

  addScoreCap(scoreCap: ScoreCap): Observable<ScoreCap> {
    return this.http.post<ScoreCap>(this.uri + "/addScoreCap", scoreCap);
  }

  updateScoreCap(scoreCap: ScoreCap): Observable<ScoreCap> {
    return this.http.put<ScoreCap>(this.uri + "/updateScoreCap", scoreCap);
  }

  deleteScoreCap(condition: string): Observable<ScoreCap> {
    return this.http.delete<ScoreCap>(this.uri + "/deleteScoreCap/" + condition);
  }

}
