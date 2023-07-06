import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RiskCalcLogicDetailsComponent } from './risk-calc-logic-details/risk-calc-logic-details.component';
import { EditRiskCalcLogicComponent } from './edit-risk-calc-logic/edit-risk-calc-logic.component';
import { AddRiskCalcLogicComponent } from './add-risk-calc-logic/add-risk-calc-logic.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';


@NgModule({
  declarations: [
    RiskCalcLogicDetailsComponent,
    EditRiskCalcLogicComponent,
    AddRiskCalcLogicComponent
  ],
  imports: [
    CommonModule, FormsModule,ReactiveFormsModule,RouterModule
  ]
})
export class RiskCalcLogicModule { }
