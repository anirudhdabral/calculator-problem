import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { OutputDetailsComponent } from './output-details/output-details.component';
import { CompanyRiskScoreDetailsComponent } from './company-risk-score/company-risk-score-details/company-risk-score-details.component';
import { RiskDimensionDetailsComponent } from './risk-dimension/risk-dimension-details/risk-dimension-details.component';
import { RiskCalcLogicDetailsComponent } from './risk-calc-logic/risk-calc-logic-details/risk-calc-logic-details.component';
import { RiskScoreLevelDetailsComponent } from './risk-score-level/risk-score-level-details/risk-score-level-details.component';
import { ScoreCapDetailsComponent } from './score-cap/score-cap-details/score-cap-details.component';
import { AddCompanyRiskScoreComponent } from './company-risk-score/add-company-risk-score/add-company-risk-score.component';
import { AddRiskCalcLogicComponent } from './risk-calc-logic/add-risk-calc-logic/add-risk-calc-logic.component';
import { AddRiskScoreLevelComponent } from './risk-score-level/add-risk-score-level/add-risk-score-level.component';
import { AddScoreCapComponent } from './score-cap/add-score-cap/add-score-cap.component';
import { RebalanceRiskDimensionComponent } from './risk-dimension/rebalance-risk-dimension/rebalance-risk-dimension.component';
import { EditCompanyRiskScoreComponent } from './company-risk-score/edit-company-risk-score/edit-company-risk-score.component';
import { EditRiskScoreLevelComponent } from './risk-score-level/edit-risk-score-level/edit-risk-score-level.component';
import { EditRiskCalcLogicComponent } from './risk-calc-logic/edit-risk-calc-logic/edit-risk-calc-logic.component';
import { EditScoreCapComponent } from './score-cap/edit-score-cap/edit-score-cap.component';
import { JobStatusComponent } from './job-status/job-status.component';

const routes: Routes = [
  {
    component: OutputDetailsComponent,
    path: 'home'
  },
  {
    component: OutputDetailsComponent,
    path: ''
  },
  {
    component: JobStatusComponent,
    path: 'job-status'
  },
  // company risk score
  {
    component: CompanyRiskScoreDetailsComponent,
    path: 'companyRiskScoreDetails'
  },
  {
    component: AddCompanyRiskScoreComponent,
    path: 'addCompanyRiskScore'
  },
  {
    component: EditCompanyRiskScoreComponent,
    path: 'editCompanyRiskScore/:companyName'
  },
  // risk dimension
  {
    component: RiskDimensionDetailsComponent,
    path: 'riskDimensionDetails'
  },
  {
    component: RebalanceRiskDimensionComponent,
    path: 'rebalanceRiskDimensions'
  },
  // risk calculation logic
  {
    component: RiskCalcLogicDetailsComponent,
    path: 'riskCalcLogicDetails'
  },
  {
    component: AddRiskCalcLogicComponent,
    path: 'addRiskCalcLogic'
  },
  {
    component: EditRiskCalcLogicComponent,
    path: 'editRiskCalcLogic/:elementName'
  },
  // risk score level
  {
    component: RiskScoreLevelDetailsComponent,
    path: 'riskScoreLevelDetails'
  },
  {
    component: AddRiskScoreLevelComponent,
    path: 'addRiskScoreLevel'
  },
  {
    component: EditRiskScoreLevelComponent,
    path: 'editRiskScoreLevel/:score'
  },
  // score cap
  {
    component: ScoreCapDetailsComponent,
    path: 'scoreCapDetails'
  },
  {
    component: AddScoreCapComponent,
    path: 'addScoreCap'
  },
  {
    component: EditScoreCapComponent,
    path: 'editScoreCap/:condition'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
