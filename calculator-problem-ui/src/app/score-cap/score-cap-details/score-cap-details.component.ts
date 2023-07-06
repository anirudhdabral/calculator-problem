import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ScoreCapService } from 'src/app/service/score-cap.service';

@Component({
  selector: 'app-score-cap-details',
  templateUrl: './score-cap-details.component.html',
  styleUrls: ['./score-cap-details.component.sass']
})
export class ScoreCapDetailsComponent implements OnInit {
  scoreCapList: any = []

  constructor(private router: Router, private serviceScoreCap: ScoreCapService) { }

  ngOnInit(): void {
    this.getAllScoreCap()
  }

  getAllScoreCap() {
    this.serviceScoreCap.getAllScoreCap().subscribe((result) => {
      this.scoreCapList = result;
    });
  }

  deleteScoreCap(score: string) {
    if (!confirm("Are you sure you want to delete score: " + score + "?")) {
      return
    }
    this.serviceScoreCap.deleteScoreCap(score).subscribe((result) => {
      this.getAllScoreCap();
    })
  }

  navigateToEditScoreCap(score: string) {
    this.router.navigate(['editScoreCap/' + score])
  }

  navigateToAddScoreCap() {
    this.router.navigate(['addScoreCap'])
  }

}
