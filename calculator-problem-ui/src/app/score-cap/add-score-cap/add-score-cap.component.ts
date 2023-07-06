import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, ValidationErrors, AbstractControl, ValidatorFn, } from '@angular/forms';
import { Router } from '@angular/router';
import { ScoreCap } from 'src/app/model/ScoreCap';
import { RiskScoreLevelService } from 'src/app/service/risk-score-level.service';
import { ScoreCapService } from 'src/app/service/score-cap.service';

@Component({
  selector: 'app-add-score-cap',
  templateUrl: './add-score-cap.component.html',
  styleUrls: ['./add-score-cap.component.sass']
})
export class AddScoreCapComponent implements OnInit {
  newScoreCap = new ScoreCap()
  addScoreCapForm!: FormGroup;
  noOfRiskList: string[] = ["one", "two", "three"]
  riskLevelList: string[] = []
  isAlreadyPresent: boolean = false

  constructor(private router: Router, private serviceScoreCap: ScoreCapService, private serviceRiskScoreLevel: RiskScoreLevelService) { }

  ngOnInit(): void {
    this.createForm()
    this.getRiskLevel()
  }

  getRiskLevel() {
    this.serviceRiskScoreLevel.getAllRiskScoreLevel().subscribe((result: any) => {
      let list = result;
      list.forEach((element: any) => {
        this.riskLevelList.push(element.level)
      });
    })
  }

  createForm() {
    this.addScoreCapForm = new FormGroup({
      noOfRisk: new FormControl('invalid', this.isValueValid()),
      riskLevel: new FormControl('invalid', this.isValueValid()),
      totalRiskCappedScore: new FormControl('', [Validators.required, Validators.min(0), Validators.max(100)])
    })
  }

  isValueValid(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      const value = control.value as string;
      if (!value) {
        return null;
      }
      let validValue: boolean = false;
      if (value != "invalid") {
        validValue = true
      }
      return !validValue ? { result: true } : null;
    }
  }

  onSubmit() {
    if (!confirm("Are you sure you want to submit this form?")) {
      return
    }
    const condition = (this.addScoreCapForm.value.noOfRisk as string) + "_" + (this.addScoreCapForm.value.riskLevel as string)
    const totalRiskCappedScore = this.addScoreCapForm.value.totalRiskCappedScore as number
    this.newScoreCap.condition = condition.trim()
      .split(" ")
      .filter((item: string) => item != "")
      .join("_")
    this.newScoreCap.totalRiskCappedScore = totalRiskCappedScore
    this.serviceScoreCap.getScoreCap(condition).subscribe((result: any) => {
      if (result == null) {
        this.isAlreadyPresent = false
        this.serviceScoreCap.addScoreCap(this.newScoreCap).subscribe(() => {
          this.navigateToScoreCapDetails()
        })
      }
      else {
        this.isAlreadyPresent = true
      }
    })
  }

  navigateToScoreCapDetails() {
    this.router.navigate(['scoreCapDetails'])
  }

  get validCondition() { return this.addScoreCapForm.get('condition') }
  get validTotalRiskCappedScore() { return this.addScoreCapForm.get('totalRiskCappedScore') }
  
}
