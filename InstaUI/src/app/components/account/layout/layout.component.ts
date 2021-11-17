﻿import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { AccountService } from 'src/app/service/accountservice';

@Component({ templateUrl: 'layout.component.html' })
export class LayoutComponent {
    constructor(
        private router: Router,
        private accountService: AccountService
    ) {

    }

    ngOnInit() {
        
    }
    ngAfterViewInit(){
        if (this.accountService.userValue) {
            this.router.navigate(['/profile']);
        } else {

            this.router.navigate(['/login']);
        }
    }
}