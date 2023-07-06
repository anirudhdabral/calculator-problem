import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RiskDimensionService } from 'src/app/service/risk-dimension.service';

@Component({
  selector: 'app-risk-dimension-details',
  templateUrl: './risk-dimension-details.component.html',
  styleUrls: ['./risk-dimension-details.component.sass']
})
export class RiskDimensionDetailsComponent implements OnInit {
  riskDimensionList: any = []

  constructor(private router: Router, private serviceRiskDimension: RiskDimensionService) { }

  ngOnInit(): void {
    this.getAllRiskDimension()
  }

  getAllRiskDimension() {
    this.serviceRiskDimension.getAllRiskDimension().subscribe((result) => {
      this.riskDimensionList = result;
    });
  }

  navigateToRebalanceRiskDimension() {
    this.router.navigate(['rebalanceRiskDimensions'])
  }

}
