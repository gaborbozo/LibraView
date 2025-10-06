import { Injectable } from '@angular/core'
import { LibraConfigKeys, LibraConfigService } from './libra-config.service'
import { DefaultService as LibraMasterService, LoginRequest } from '../../../generated/api/master'

@Injectable({
  providedIn: 'root',
})
export class LibraAuthenticationService {
  constructor(
    private libraConfig: LibraConfigService,
    private libraMasterService: LibraMasterService,
  ) {}

  login(request: LoginRequest) {
    this.libraMasterService.login(request).subscribe((response) => {
      this.libraConfig.setResource(LibraConfigKeys.LIBRA_TOKEN, `Bearer ${response.token}`)
    })
  }

  logout() {
    this.libraConfig.emptyResource(LibraConfigKeys.LIBRA_TOKEN)
  }

  isLoggedIn(): boolean {
    return !!this.libraConfig.getResource(LibraConfigKeys.LIBRA_TOKEN) // TODO + based on tokens relating timeout, calculate if it expired already
  }
}
