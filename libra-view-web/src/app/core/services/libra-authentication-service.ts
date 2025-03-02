import { HttpClient } from '@angular/common/http'
import { Injectable } from '@angular/core'
import { LoginRequest } from '../../shared/data-model/authentication/login.request'
import { LoginResponse } from '../../shared/data-model/authentication/login.response'
import { LibraConfigKeys, LibraConfigService } from './libra-config.service'

@Injectable({
  providedIn: 'root',
})
export class LibraAuthenticationService {
  private baseURL = '/auth'

  constructor(
    private libraConfig: LibraConfigService,
    private http: HttpClient,
  ) {}

  login(request: LoginRequest) {
    return this.http.post<LoginResponse>(`${this.baseURL}/login`, request).subscribe((response) => {
      this.libraConfig.setResource(LibraConfigKeys.TOKEN, response.token)
    })
  }

  logout() {
    this.libraConfig.emptyResource(LibraConfigKeys.TOKEN)
  }

  isLoggedIn(): boolean {
    return !!this.libraConfig.getResource(LibraConfigKeys.TOKEN)
  }
}
