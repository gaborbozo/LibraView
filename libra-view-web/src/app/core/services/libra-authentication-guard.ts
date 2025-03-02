import { Injectable } from '@angular/core'
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router'
import { LibraAuthenticationService } from './libra-authentication-service'

@Injectable({
  providedIn: 'root',
})
export class LibraAuthenticationGuard implements CanActivate {
  constructor(
    private authenticationService: LibraAuthenticationService,
    private router: Router,
  ) {}

  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    if (this.authenticationService.isLoggedIn()) {
      return true
    }
    this.router.navigate(['/login'])
    return false
  }
}
