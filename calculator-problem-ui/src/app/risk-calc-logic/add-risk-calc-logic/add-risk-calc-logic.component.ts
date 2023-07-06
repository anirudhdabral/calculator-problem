import { Component, OnInit } from '@angular/core';
import { RiskCalcLogic } from 'src/app/model/RiskCalcLogic';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { RiskCalcLogicService } from 'src/app/service/risk-calc-logic.service';
import { OutputDetailsService } from 'src/app/service/output-details.service';

@Component({
  selector: 'app-add-risk-calc-logic',
  templateUrl: './add-risk-calc-logic.component.html',
  styleUrls: ['./add-risk-calc-logic.component.sass']
})
export class AddRiskCalcLogicComponent implements OnInit {
  newRiskCalcLogic = new RiskCalcLogic()
  addRiskCalcLogicForm!: FormGroup;
  isAlreadyPresent: boolean = false;

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

  constructor(private router: Router, private serviceRiskCalcLogic: RiskCalcLogicService, private serviceOutput: OutputDetailsService) { }

  ngOnInit(): void {
    this.createForm();
    this.getAllVariable()
  }

  getAllVariable() {
    this.serviceOutput.getAllVariable().subscribe((result: any) => {
      this.variables = result
    })
  }

  createForm() {
    this.addRiskCalcLogicForm = new FormGroup({
      elementName: new FormControl('', [Validators.required, Validators.maxLength(30)])
    })
  }

  onSubmit() {
    if (!confirm("Are you sure you want to submit this form?")) {
      return
    }
    this.serviceRiskCalcLogic
      .getRiskCalcLogic(this.addRiskCalcLogicForm.value.elementName as string)
      .subscribe(
        (result: any) => {
          if (result === null) {
            let elementName = this.addRiskCalcLogicForm.value.elementName as string
            this.newRiskCalcLogic.elementName =
              elementName.trim()
                .split(" ")
                .filter(item => item != "")
                .join("_")
            this.newRiskCalcLogic.formula = this.formula
            this.serviceRiskCalcLogic.addRiskCalcLogic(this.newRiskCalcLogic)
              .subscribe(() => {
                this.navigateToRiskCalcLogicDetails();
                this.isAlreadyPresent = false
              });
          }
          else {
            this.isAlreadyPresent = true
          }
        });
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

  navigateToRiskCalcLogicDetails() {
    this.router.navigate(['riskCalcLogicDetails'])
  }

  get validElementName() { return this.addRiskCalcLogicForm.get('elementName') }
  get validFormula() { return this.addRiskCalcLogicForm.get('formula') }

}
