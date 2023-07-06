import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, FormControl, FormArray, AbstractControl, Validators } from '@angular/forms';
import { CompanyRiskScore } from 'src/app/model/CompanyRiskScore';
import { CompanyRiskScoreService } from 'src/app/service/company-risk-score.service';

@Component({
  selector: 'app-edit-company-risk-score',
  templateUrl: './edit-company-risk-score.component.html',
  styleUrls: ['./edit-company-risk-score.component.sass']
})
export class EditCompanyRiskScoreComponent implements OnInit {

  isAlreadyPresent: boolean = false;
  updatedCompanyRiskScore = new CompanyRiskScore()
  companyName!: string

  editCompanyRiskScoreForm = new FormGroup({
    companyName: new FormControl(this.companyName),
    dimensions: new FormArray([])
  })

  constructor(private activatedRouter: ActivatedRoute, private router: Router, private serviceCompanyRiskScore: CompanyRiskScoreService) { }

  ngOnInit(): void {
    this.createForm()
  }

  createForm() {
    this.serviceCompanyRiskScore
      .getCompanyRiskScore(this.activatedRouter.snapshot.params['companyName'])
      .subscribe((result: any) => {
        this.companyName = result.companyName
        const dimensions = result.dimensions
        dimensions.forEach((element: any) => {
          (<FormArray>this.editCompanyRiskScoreForm.get('dimensions')).push(
            new FormGroup({
              dimensionName: new FormControl(element.dimensionName, [Validators.required, Validators.maxLength(50)]),
              dimensionValue: new FormControl(element.dimensionValue, [Validators.required, Validators.min(0), Validators.max(100)])
            })
          )
        });
      });
  }

  replaceSpacesWithUnderscore() {
    this.updatedCompanyRiskScore.dimensions.forEach((element: any) => {
      element.dimensionName = element.dimensionName.trim()
        .split(" ")
        .filter((item: string) => item != "")
        .join("_")
    });
  }

  onSubmit() {
    if (!confirm("Are you sure you want to submit this edit?")) {
      return
    }
    this.updatedCompanyRiskScore.companyName = this.companyName
    this.updatedCompanyRiskScore.dimensions = this.editCompanyRiskScoreForm.value.dimensions
    this.replaceSpacesWithUnderscore()
    this.serviceCompanyRiskScore.updateCompanyRiskScore(this.updatedCompanyRiskScore)
      .subscribe((data: any) => {
        this.navigateToCompanyRiskScoreDetails();
      });
  }

  addDimension() {
    (<FormArray>this.editCompanyRiskScoreForm.get('dimensions')).push(
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
    (<FormArray>this.editCompanyRiskScoreForm.get('dimensions')).removeAt(index)
  }

  getQuantities(): AbstractControl[] {
    return (<FormArray>this.editCompanyRiskScoreForm.get('dimensions')).controls
  }

}
