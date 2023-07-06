import { Component, OnInit } from '@angular/core';
import { RiskCalcLogic } from 'src/app/model/RiskCalcLogic';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { RiskCalcLogicService } from 'src/app/service/risk-calc-logic.service';
import { OutputDetailsService } from 'src/app/service/output-details.service';

@Component({
  selector: 'app-edit-risk-calc-logic',
  templateUrl: './edit-risk-calc-logic.component.html',
  styleUrls: ['./edit-risk-calc-logic.component.sass']
})
export class EditRiskCalcLogicComponent implements OnInit {
  updatedRiskCalcLogic = new RiskCalcLogic()
  existingElementName: string = ""
  existingFormula: string = ""

  variables: string[] = []
  operators: string[] = ['+', '-', '*', '/', '%']
  formula: string = ""
  isVariableListActive: boolean = true
  isOperatorListActive: boolean = false
  isPushButtonActive: boolean = false

  isVariableOneSelected: boolean = false
  isVariableTwoSelected: boolean = false
  isMinMaxChoiceSelected: boolean = false

  isMinMaxFieldVisible: boolean = false

  selectedItem!: string
  variableOneItem!: string
  variableTwoItem!: string
  minMaxChoice!: string

  constructor(private router: Router, private serviceRiskCalcLogic: RiskCalcLogicService, 
    private activatedRoute: ActivatedRoute, private serviceOutput: OutputDetailsService) { }

  ngOnInit(): void {
    this.getRiskCalcLogic()
    this.getAllVariable()
  }

  onSubmit() {
    if (!confirm("Are you sure you want to submit this edit?")) {
      return
    }
    this.updatedRiskCalcLogic.elementName = this.existingElementName
    this.updatedRiskCalcLogic.formula = this.formula
    this.serviceRiskCalcLogic.updateRiskCalcLogic(this.updatedRiskCalcLogic)
      .subscribe(() => {
        this.navigateToRiskCalcLogicDetails();
      });
  }

  getRiskCalcLogic() {
    this.serviceRiskCalcLogic.getRiskCalcLogic(this.activatedRoute.snapshot.params['elementName'])
      .subscribe((result: any) => {
        this.existingElementName = result.elementName
        this.existingFormula = result.formula
      })
  }

  navigateToRiskCalcLogicDetails() {
    this.router.navigate(['riskCalcLogicDetails'])
  }

  getAllVariable() {
    this.serviceOutput.getAllVariable().subscribe((result: any) => {
      this.variables = result
    })
  }

  onItemSelected(event: Event) {
    this.selectedItem = (event.target as HTMLInputElement).value
    this.isPushButtonActive = !(this.selectedItem.length == 0)
  }

  onVariableOneSelected(event: Event) {
    this.variableOneItem = (event.target as HTMLInputElement).value
    this.isVariableOneSelected = !(this.variableOneItem.length == 0)
  }

  onVariableTwoSelected(event: Event) {
    this.variableTwoItem = (event.target as HTMLInputElement).value
    this.isVariableTwoSelected = !(this.variableTwoItem.length == 0)
  }

  onMinMaxChoiceSelected(event: Event) {
    this.minMaxChoice = (event.target as HTMLInputElement).value
    this.isMinMaxChoiceSelected = !(this.minMaxChoice.length == 0)
  }

  changeSelectors() {
    this.isVariableListActive = !this.isVariableListActive
    this.isOperatorListActive = !this.isOperatorListActive
    this.isPushButtonActive = false
  }

  handlePushButtonClick() {
    if (this.selectedItem == "min-max") {
      this.isVariableListActive = false
      this.isOperatorListActive = false
      this.isPushButtonActive = false
      this.isMinMaxFieldVisible = true
      return
    }
    this.formula += this.selectedItem
    this.changeSelectors()
  }

  handleAddMinMaxClick() {
    let expression = ""
    if (this.minMaxChoice == "min") {
      expression = "min(" + this.variableOneItem + "," + this.variableTwoItem + ")"
    }
    else {
      expression = "max(" + this.variableOneItem + "," + this.variableTwoItem + ")"
    }
    this.formula += expression
    this.isMinMaxFieldVisible = false
    this.isOperatorListActive = true
  }

}
