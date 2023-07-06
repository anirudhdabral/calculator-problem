import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormArray, AbstractControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CompanyRiskScore } from 'src/app/model/CompanyRiskScore';
import { CompanyRiskScoreService } from 'src/app/service/company-risk-score.service';

@Component({
  selector: 'app-add-company-risk-score',
  templateUrl: './add-company-risk-score.component.html',
  styleUrls: ['./add-company-risk-score.component.sass']
})
export class AddCompanyRiskScoreComponent implements OnInit {
  isAlreadyPresent: boolean = false;
  newCompanyRiskScore = new CompanyRiskScore()
  addCompanyRiskScoreForm!: FormGroup;

  constructor(private router: Router, private serviceCompanyRiskScore: CompanyRiskScoreService) { }

  ngOnInit(): void {
    this.createForm()
  }

  createForm() {
    this.addCompanyRiskScoreForm = new FormGroup({
      companyName: new FormControl('', [Validators.required, Validators.maxLength(30)]),
      dimensions: new FormArray([
        new FormGroup({
          dimensionName: new FormControl('', [Validators.required, Validators.maxLength(50)]),
          dimensionValue: new FormControl('', [Validators.required, Validators.min(0), Validators.max(100)])
        })
      ])
    })
  }

  replaceSpacesWithUnderscore() {
    this.addCompanyRiskScoreForm.value.dimensions.forEach((element: any) => {
      element.dimensionName = element.dimensionName.trim()
        .split(" ")
        .filter((item: string) => item != "")
        .join("_")
    });
  }

  onSubmit() {
    if (!confirm("Are you sure you want to submit this form?")) {
      return
    }
    this.serviceCompanyRiskScore
      .getCompanyRiskScore(this.addCompanyRiskScoreForm.value.companyName as string)
      .subscribe(
        (result: any) => {
          if (result === null) {
            this.replaceSpacesWithUnderscore()
            this.newCompanyRiskScore.companyName = this.addCompanyRiskScoreForm.value.companyName as string
            this.newCompanyRiskScore.dimensions = this.addCompanyRiskScoreForm.value.dimensions
            this.serviceCompanyRiskScore.addCompanyRiskScore(this.newCompanyRiskScore)
              .subscribe((data: any) => {
                this.navigateToCompanyRiskScoreDetails();
                this.isAlreadyPresent = false
              });
          }
          else {
            this.isAlreadyPresent = true
          }
        });
  }

  addDimension() {
    (<FormArray>this.addCompanyRiskScoreForm.get('dimensions')).push(
      new FormGroup({
        dimensionName: new FormControl('', [Validators.required, Validators.maxLength(50)]),
        dimensionValue: new FormControl('', [Validators.required, Validators.min(0), Validators.max(100)])
      })
    )
  }

  navigateToCompanyRiskScoreDetails() {
    this.router.navigate(['companyRiskScoreDetails'])
  }

  removeDimension(index: number) {
    (<FormArray>this.addCompanyRiskScoreForm.get('dimensions')).removeAt(index)
  }

  getQuantities(): AbstractControl[] {
    return (<FormArray>this.addCompanyRiskScoreForm.get('dimensions')).controls
  }

  get validCompanyName() { return this.addCompanyRiskScoreForm.get('companyName') }

}
