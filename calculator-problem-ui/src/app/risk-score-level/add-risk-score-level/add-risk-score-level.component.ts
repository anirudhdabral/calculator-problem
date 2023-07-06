import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { FormGroup, FormControl, ValidationErrors, AbstractControl, ValidatorFn, Validators } from '@angular/forms';
import { RiskScoreLevelService } from 'src/app/service/risk-score-level.service';
import { RiskScoreLevel } from 'src/app/model/RiskScoreLevel';

@Component({
  selector: 'app-add-risk-score-level',
  templateUrl: './add-risk-score-level.component.html',
  styleUrls: ['./add-risk-score-level.component.sass']
})

export class AddRiskScoreLevelComponent implements OnInit {
  newRiskScoreLevel = new RiskScoreLevel()
  addRiskScoreLevelForm!: FormGroup;
  isAlreadyPresent: boolean = false

  constructor(private router: Router, private serviceRiskScoreLevel: RiskScoreLevelService) { }

  ngOnInit(): void {
    this.createForm()
  }

  createForm() {
    this.addRiskScoreLevelForm = new FormGroup({
      minScore: new FormControl('', [Validators.required, Validators.min(0), Validators.max(100)]),
      maxScore: new FormControl('', [Validators.required, Validators.min(0), Validators.max(100), this.isValueMoreThanMinScore()]),
      level: new FormControl('', [Validators.required, Validators.maxLength(30)])
    })
  }

  onSubmit() {
    if (!confirm("Are you sure you want to submit this form?")) {
      return
    }
    const minScore = this.addRiskScoreLevelForm.value.minScore as string
    const maxScore = this.addRiskScoreLevelForm.value.maxScore as string
    let level = this.addRiskScoreLevelForm.value.level as string
    this.newRiskScoreLevel.score = minScore + "-" + maxScore
    this.newRiskScoreLevel.level = level
      .trim()
      .split(" ")
      .filter(item => item != "")
      .join("_")
    this.serviceRiskScoreLevel.getRiskScoreLevel(this.newRiskScoreLevel.score).subscribe((result: any) => {
      if (result == null) {
        this.isAlreadyPresent = false
        this.serviceRiskScoreLevel.addRiskScoreLevel(this.newRiskScoreLevel).subscribe(() => {
          this.navigateToRiskScoreLevelDetails()
        })
      }
      else {
        this.isAlreadyPresent = true
      }
    })
  }

  isValueMoreThanMinScore(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      const value = control.value as number;
      if (!value) {
        return null;
      }
      const minScore = this.addRiskScoreLevelForm.value.minScore as number
      const validRange = value > minScore
      return !validRange ? { result: true } : null;
    }
  }

  navigateToRiskScoreLevelDetails() {
    this.router.navigate(['riskScoreLevelDetails'])
  }

  get validMinScore() { return this.addRiskScoreLevelForm.get('minScore') }
  get validMaxScore() { return this.addRiskScoreLevelForm.get('maxScore') }
  get validLevel() { return this.addRiskScoreLevelForm.get('level') }



}
