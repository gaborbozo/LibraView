import { Injectable } from '@angular/core'

export enum LibraConfigKeys {
  'BACKEND_URL' = 'backendURL',
}

@Injectable({ providedIn: 'root' })
export class LibraConfigService {
  private getResource(key: LibraConfigKeys) {
    const value = localStorage.getItem(key)
    if (!value) console.error(`${key} is not declared in local storage!`)
    return value
  }

  private setResource(key: LibraConfigKeys, value: string) {
    localStorage.setItem(key, value)
  }

  getBackendURL() {
    return this.getResource(LibraConfigKeys.BACKEND_URL)
  }

  setBackendURL(value: string) {
    this.setResource(LibraConfigKeys.BACKEND_URL, value)
  }
}
