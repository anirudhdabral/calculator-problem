import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RiskScoreLevelDetailsComponent } from './risk-score-level-details/risk-score-level-details.component';
import { EditRiskScoreLevelComponent } from './edit-risk-score-level/edit-risk-score-level.component';
import { AddRiskScoreLevelComponent } from './add-risk-score-level/add-risk-score-level.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';


@NgModule({
  declarations: [
    RiskScoreLevelDetailsComponent,
    EditRiskScoreLevelComponent,
    AddRiskScoreLevelComponent
  ],
  imports: [
    CommonModule, 
    FormsModule, 
    ReactiveFormsModule, 
    RouterModule
  ]
})
export class RiskScoreLevelModule { }
