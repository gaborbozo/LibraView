import { Injectable } from '@angular/core'

export enum LibraConfigKeys {
  'BACKEND_URL' = 'backendURL',
  'TOKEN' = 'token',
}

@Injectable({ providedIn: 'root' })
export class LibraConfigService {
  getResource(key: LibraConfigKeys) {
    if (typeof window === 'undefined') {
      return null
    }

    const value = localStorage.getItem(key)
    if (!value) console.error(`${key} is not declared in local storage!`)
    return value
  }

  setResource(key: LibraConfigKeys, value: string) {
    localStorage.setItem(key, value)
  }

  emptyResource(key: LibraConfigKeys) {
    localStorage.removeItem(key)
  }
}
