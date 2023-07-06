import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CompanyRiskScoreService } from 'src/app/service/company-risk-score.service';

@Component({
  selector: 'app-company-risk-score-details',
  templateUrl: './company-risk-score-details.component.html',
  styleUrls: ['./company-risk-score-details.component.sass']
})
export class CompanyRiskScoreDetailsComponent implements OnInit {
  isEditButtonVisible: boolean = false;
  companyList: any = []
  dimensions: any = []
  selectedCompany!: string

  constructor(private router: Router, private serviceCompanyRiskScore: CompanyRiskScoreService) { }

  ngOnInit(): void {
    this.getAllCompanyRiskScore();
    this.resetValues();
  }

  getAllCompanyRiskScore() {
    this.serviceCompanyRiskScore.getAllCompanyRiskScore().subscribe((result) => {
      const outputList: any = result;
      outputList.forEach((element: any) => {
        this.companyList.push(element.companyName)
      });
    });
  }

  resetValues() {
    this.dimensions = [
      {
        dimensionName: "Select company first",
        dimensionValue: "Select company first"
      }
    ]
    this.isEditButtonVisible = false;
  }

  onCompanySelected(event: Event) {
    if (this.companyList.indexOf((event.target as HTMLInputElement).value) !== -1) {
      const companyName = (event.target as HTMLInputElement).value
      this.serviceCompanyRiskScore.getCompanyRiskScore(companyName).
        subscribe((result: any) => {
          this.dimensions = []
          this.dimensions = result.dimensions
        });
      this.isEditButtonVisible = true;
      this.selectedCompany = companyName
      return
    }
    this.resetValues()
  }

  deleteCompanyRiskScore(companyName: string) {
    if (!confirm("Are you sure you want to delete company: " + companyName + "?")) {
      return
    }
    this.serviceCompanyRiskScore.deleteCompanyRiskScore(companyName).subscribe((result) => {
      window.location.reload()
    })

  }

  editCompany() {
    this.router.navigate(['editCompanyRiskScore/' + this.selectedCompany])
  }

  navigateToAddCompanyRiskScore() {
    this.router.navigate(['addCompanyRiskScore'])
  }

}
