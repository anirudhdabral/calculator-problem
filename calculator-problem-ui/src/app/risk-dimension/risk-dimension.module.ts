import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RiskDimensionDetailsComponent } from './risk-dimension-details/risk-dimension-details.component';
import { RebalanceRiskDimensionComponent } from './rebalance-risk-dimension/rebalance-risk-dimension.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    RiskDimensionDetailsComponent,
    RebalanceRiskDimensionComponent
  ],
  imports: [
    CommonModule,
    FormsModule, ReactiveFormsModule
  ]
})
export class RiskDimensionModule { }
