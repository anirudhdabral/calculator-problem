import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ScoreCapService } from 'src/app/service/score-cap.service';
import { FormGroup, FormControl, Validators } from '@angular/forms'
import { ScoreCap } from 'src/app/model/ScoreCap';

@Component({
  selector: 'app-edit-score-cap',
  templateUrl: './edit-score-cap.component.html',
  styleUrls: ['./edit-score-cap.component.sass']
})
export class EditScoreCapComponent implements OnInit {
  updatedScoreCap = new ScoreCap()
  editScoreCapForm!: FormGroup;
  existingCondition!: string
  existingTotalRiskCappedScore!: number

  constructor(private router: Router, private serviceScoreCap: ScoreCapService, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.getScoreCap()
    this.createForm()
  }

  getScoreCap() {
    this.serviceScoreCap.getScoreCap(this.activatedRoute.snapshot.params['condition']).subscribe((result: any) => {
      this.existingCondition = result.condition
      this.existingTotalRiskCappedScore = result.totalRiskCappedScore
    })
  }

  createForm() {
    this.editScoreCapForm = new FormGroup({
      totalRiskCappedScore: new FormControl('', [Validators.required, Validators.min(0), Validators.max(100)])
    })
  }

  onSubmit() {
    if (!confirm("Are you sure you want to submit this edit?")) {
      return
    }
    this.updatedScoreCap.condition = this.existingCondition as string
    this.updatedScoreCap.totalRiskCappedScore = this.editScoreCapForm.value.totalRiskCappedScore as number
    this.serviceScoreCap.updateScoreCap(this.updatedScoreCap).subscribe((result: any) => {
      this.navigateToScoreCapDetails()
    })
  }

  navigateToScoreCapDetails() {
    this.router.navigate(['scoreCapDetails'])
  }

  get validTotalRiskCappedScore() { return this.editScoreCapForm.get('totalRiskCappedScore') }

}
