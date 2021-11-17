import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from "@angular/router";
import { Observable } from "rxjs";
import { AccountService } from "./accountservice";

@Injectable()
export class AuthGuardService implements CanActivate {
    constructor(private router: Router,
      private accountService: AccountService){

    }
  canActivate(route: ActivatedRouteSnapshot,state: RouterStateSnapshot): boolean|UrlTree {
    const user = this.accountService.userValue;
        if (user) {
            return true;
        }

        this.router.navigate(['/login']);
        return false;
  }
}