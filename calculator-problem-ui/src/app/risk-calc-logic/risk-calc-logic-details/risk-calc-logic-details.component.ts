import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RiskCalcLogicService } from 'src/app/service/risk-calc-logic.service';

@Component({
  selector: 'app-risk-calc-logic-details',
  templateUrl: './risk-calc-logic-details.component.html',
  styleUrls: ['./risk-calc-logic-details.component.sass']
})
export class RiskCalcLogicDetailsComponent implements OnInit {

  riskCalcLogics: any = []

  constructor(private router: Router, private serviceRiskCalcLogic: RiskCalcLogicService) { }

  ngOnInit(): void {
    this.getAllRiskCalcLogic();
  }

  getAllRiskCalcLogic() {
    this.serviceRiskCalcLogic.getAllRiskCalcLogic().subscribe((result) => {
      this.riskCalcLogics = result;
    });
  }

  deleteRiskCalcLogic(elementName: string) {
    if (!confirm("Are you sure you want to delete element: " + elementName + "?")) {
      return
    }
    this.serviceRiskCalcLogic.deleteRiskCalcLogic(elementName).subscribe((result: any) => {
      this.getAllRiskCalcLogic();
    })
  }

  navigateToAddRiskCalcLogic() {
    this.router.navigate(['addRiskCalcLogic'])
  }

  navigateToEditRiskCalcLogic(elementName: string) {
    this.router.navigate(['editRiskCalcLogic/' + elementName])
  }

}
