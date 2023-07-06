import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CompanyRiskScoreDetailsComponent } from './company-risk-score-details/company-risk-score-details.component';
import { EditCompanyRiskScoreComponent } from './edit-company-risk-score/edit-company-risk-score.component';
import { AddCompanyRiskScoreComponent } from './add-company-risk-score/add-company-risk-score.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    CompanyRiskScoreDetailsComponent,
    EditCompanyRiskScoreComponent,
    AddCompanyRiskScoreComponent
  ],
  imports: [
    CommonModule, 
    FormsModule, 
    ReactiveFormsModule
  ]
})
export class CompanyRiskScoreModule { }
