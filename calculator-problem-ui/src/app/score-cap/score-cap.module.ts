import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ScoreCapDetailsComponent } from './score-cap-details/score-cap-details.component';
import { EditScoreCapComponent } from './edit-score-cap/edit-score-cap.component';
import { AddScoreCapComponent } from './add-score-cap/add-score-cap.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';


@NgModule({
  declarations: [
    ScoreCapDetailsComponent,
    EditScoreCapComponent,
    AddScoreCapComponent
  ],
  imports: [
    CommonModule, 
    FormsModule, 
    ReactiveFormsModule, 
    RouterModule
  ]
})
export class ScoreCapModule { }
