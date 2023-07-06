import { Component, OnInit, Output } from '@angular/core';
import { OutputDetailsService } from '../service/output-details.service';

@Component({
  selector: 'app-output-details',
  templateUrl: './output-details.component.html',
  styleUrls: ['./output-details.component.sass']
})
export class OutputDetailsComponent implements OnInit {
  companyList: any = []
  values: any = []

  constructor(private outputService: OutputDetailsService) { }

  ngOnInit(): void {
    this.getOutputDetails()
    this.resetValues()
  }

  getOutputDetails() {
    this.outputService.getAllOutputTable()
      .subscribe({
        next: (result: any) => {
          const outputList: any = result
          outputList.forEach((element: any) => {
            this.companyList.push(element.companyName)
          });
        },
        error: (error: any) => { }
      });
  }

  resetValues() {
    this.values = [{
      element: "Select company first",
      value: "Select company first"
    }]
  }

  onCompanySelected(event: Event) {
    if (this.companyList.indexOf((event.target as HTMLInputElement).value) !== -1) {
      const selectedCompanyName = (event.target as HTMLInputElement).value
      this.outputService.getOutputTable(selectedCompanyName)
        .subscribe((result: any) => {
          this.values = []
          this.values = result.values
        });
      return
    }
    this.resetValues()
  }

  recalculateOutput() {
    this.outputService.calculateOutput()
      .subscribe(() => {
        this.companyList = []
        this.getOutputDetails()
        this.resetValues()
      })
  }

}
