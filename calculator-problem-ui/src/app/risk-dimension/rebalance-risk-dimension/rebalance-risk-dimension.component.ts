import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RiskDimensionService } from 'src/app/service/risk-dimension.service';
import { FormGroup, FormControl, AbstractControl, Validators, FormArray } from '@angular/forms';
import { RiskDimension } from 'src/app/model/RiskDimension';


@Component({
  selector: 'app-rebalance-risk-dimension',
  templateUrl: './rebalance-risk-dimension.component.html',
  styleUrls: ['./rebalance-risk-dimension.component.sass']
})
export class RebalanceRiskDimensionComponent implements OnInit {
  riskDimensionList: any = []
  riskDimensionForm = new FormGroup({
    riskDimensions: new FormArray([])
  });

  constructor(private router: Router, private serviceRiskDimension: RiskDimensionService) { }

  ngOnInit(): void {
    this.getAllRiskDimension()
  }

  getAllRiskDimension() {
    this.serviceRiskDimension.getAllRiskDimension().subscribe((result) => {
      this.riskDimensionList = result;
      this.riskDimensionList.forEach((element: any) => {
        this.addDimension(element)
      });
    });
  }

  addDimension(element: any) {
    (<FormArray>this.riskDimensionForm.get('riskDimensions')).push(
      new FormGroup({
        dimension: new FormControl(element.dimension, [Validators.required, Validators.maxLength(50),]),
        weight: new FormControl(element.weight, [Validators.required, Validators.min(0), Validators.max(100)])
      })
    )
  }

  getQuantities(): AbstractControl[] {
    return (<FormArray>this.riskDimensionForm.get('riskDimensions')).controls
  }

  onSubmit() {
    const balancedWeightList = <Array<RiskDimension>>this.riskDimensionForm.value.riskDimensions
    this.serviceRiskDimension.rebalanceWeight(balancedWeightList).subscribe(() => {
      this.navigateToRiskDimensionDetails()
    });
  }

  navigateToRiskDimensionDetails() {
    this.router.navigate(['riskDimensionDetails'])
  }

  onWeightKeyUp() {
    const arr = <Array<RiskDimension>>this.riskDimensionForm.value.riskDimensions
    let weightSum: number = 0
    arr.forEach(element => {
      weightSum += (element.weight as number)
    });
    const maxTotalWeight: number = 100
    if (weightSum > maxTotalWeight) {
      this.riskDimensionForm.setErrors({ 'invalid': true });
    }
  }

}
