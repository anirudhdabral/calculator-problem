import { Component, OnInit } from '@angular/core';
import { JobStatusService } from '../service/job-status.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-job-status',
  templateUrl: './job-status.component.html',
  styleUrls: ['./job-status.component.sass']
})
export class JobStatusComponent implements OnInit {
  jobStatusList: any = []

  constructor(private router: Router, private serviceJobStatus: JobStatusService) { }

  ngOnInit(): void {
    this.getAllJobStatus()
  }

  getAllJobStatus() {
    this.serviceJobStatus.getAllJobStatus().subscribe((result: any) => {
      this.jobStatusList = result
    })
  }

  reset() {
    this.serviceJobStatus.resetDatabase().subscribe(() => {
      this.router.navigate(['home'])
    });
  }

}
