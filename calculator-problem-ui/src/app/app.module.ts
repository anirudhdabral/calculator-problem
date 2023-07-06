import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { OutputDetailsComponent } from './output-details/output-details.component';
import { CompanyRiskScoreModule } from './company-risk-score/company-risk-score.module';
import { RiskDimensionModule } from './risk-dimension/risk-dimension.module';
import { RiskCalcLogicModule } from './risk-calc-logic/risk-calc-logic.module';
import { RiskScoreLevelModule } from './risk-score-level/risk-score-level.module';
import { HeaderComponent } from './header/header.component';
import { ScoreCapModule } from './score-cap/score-cap.module';
import { JobStatusComponent } from './job-status/job-status.component';

@NgModule({
  declarations: [
    AppComponent,
    OutputDetailsComponent,
    HeaderComponent,
    JobStatusComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    CompanyRiskScoreModule,
    RiskDimensionModule,
    RiskCalcLogicModule,
    RiskScoreLevelModule,
    ScoreCapModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
