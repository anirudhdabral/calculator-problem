import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RiskScoreLevelService } from 'src/app/service/risk-score-level.service';

import { FormGroup, FormControl, AbstractControl, Validators } from '@angular/forms';
import { RiskScoreLevel } from 'src/app/model/RiskScoreLevel';

@Component({
  selector: 'app-edit-risk-score-level',
  templateUrl: './edit-risk-score-level.component.html',
  styleUrls: ['./edit-risk-score-level.component.sass']
})
export class EditRiskScoreLevelComponent implements OnInit {
  existingScore: string = ""
  existingLevel: string = ""
  editRiskScoreLevelForm!: FormGroup;
  updatedRiskScoreLevel = new RiskScoreLevel()

  constructor(private router: Router, private serviceRiskScoreLevel: RiskScoreLevelService, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.getRiskScoreLevel()
    this.createForm()
  }

  getRiskScoreLevel() {
    this.serviceRiskScoreLevel.getRiskScoreLevel(this.activatedRoute.snapshot.params['score']).subscribe((result: any) => {
      this.existingScore = result.score
      this.existingLevel = result.level
    })
  }

  createForm() {
    this.editRiskScoreLevelForm = new FormGroup({
      level: new FormControl('', [Validators.required, Validators.maxLength(30)])
    })
  }

  onSubmit() {
    if (!confirm("Are you sure you want to submit this edit?")) {
      return
    }
    this.updatedRiskScoreLevel.score = this.existingScore as string
    let level = this.editRiskScoreLevelForm.value.level as string
    this.updatedRiskScoreLevel.level = level
      .trim()
      .split(" ")
      .filter(item => item != "")
      .join("_")
    this.serviceRiskScoreLevel.updateRiskScoreLevel(this.updatedRiskScoreLevel).subscribe((resut: any) => {
      this.navigateToRiskScoreLevelDetails()
    })
  }

  navigateToRiskScoreLevelDetails() {
    this.router.navigate(['riskScoreLevelDetails'])
  }

  get validLevel() { return this.editRiskScoreLevelForm.get('level') }

}
