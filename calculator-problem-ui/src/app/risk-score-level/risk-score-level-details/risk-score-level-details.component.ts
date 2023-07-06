import { Component, OnInit } from '@angular/core';
import { Route, Router } from '@angular/router';
import { RiskScoreLevelService } from 'src/app/service/risk-score-level.service';

@Component({
  selector: 'app-risk-score-level-details',
  templateUrl: './risk-score-level-details.component.html',
  styleUrls: ['./risk-score-level-details.component.sass']
})

export class RiskScoreLevelDetailsComponent implements OnInit {
  riskScoreLevelList: any = []

  constructor(private router: Router, private serviceRiskScoreLevel: RiskScoreLevelService) { }

  ngOnInit(): void {
    this.getAllRiskScoreLevel()
  }

  getAllRiskScoreLevel() {
    this.serviceRiskScoreLevel.getAllRiskScoreLevel().subscribe((result) => {
      this.riskScoreLevelList = result;
    });
  }

  deleteRiskScoreLevel(score: string) {
    if (!confirm("Are you sure you want to delete score: " + score + "?")) {
      return
    }
    this.serviceRiskScoreLevel.deleteRiskScoreLevel(score).subscribe((result) => {
      this.getAllRiskScoreLevel()
    })
  }

  navigateToEditRiskScoreLevel(score: string) {
    this.router.navigate(['editRiskScoreLevel/' + score])
  }

  navigateToAddRiskScoreLevel() {
    this.router.navigate(['addRiskScoreLevel'])
  }

}
